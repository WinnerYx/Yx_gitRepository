package com.ssm.controller;

import com.ssm.entity.*;
import com.ssm.service.*;
import com.ssm.utils.MapControll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/job")
public class JobController {

    private static final String LIST = "job/list";
    private static final String STULIST = "job/stu_list";
    private static final String REQUESTJOB = "job/requestJob";
    private static final String AUDIT = "job/audit";

    @Autowired
    private JobService jobService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    TeacherCountService teacherCountService;

    @Autowired
    private StudentCountService studentCountService;

    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(@RequestBody Job job,HttpSession session){
        Student student =(Student)session.getAttribute("user");
        job.setStuId(student.getId());
        job.setName(jobService.queryJobById(Integer.parseInt(job.getName())).getName());
        Teacher teacher = teacherService.queryByTeacherId(student.getTeacherId());
        TeacherCount teacherCount= null;
        StudentCount studentCount = null;
        teacherCount = teacherCountService.queryById(teacher.getId());
        studentCount = studentCountService.queryById(student.getId());
        if(studentCount==null){
            studentCount = new StudentCount();
            studentCount.setStudentId(student.getId());
            studentCountService.createCount(studentCount);
        }
        studentCountService.increaseJobCount(student.getId());
        if(teacherCount == null){
            teacherCount = new TeacherCount();
            teacherCount.setTeacherId(teacher.getId());
            teacherCountService.createCount(teacherCount);
        }
        teacherCountService.increaseJobCount(teacher.getId());
        int result = jobService.create(job);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/createJob")
    @ResponseBody
    public Map<String,Object> createJob(Job job) {
        int result = jobService.createJob(job);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }
    @PostMapping("/list")
    @ResponseBody
    public Map<String,Object> list(@RequestBody Job job){
        List<Job> jobs = jobService.queryListJobStatus(job);
        Integer count = jobService.count(job);
        return MapControll.getInstance().success().page(jobs,count).getMap();
    }

    /*@PostMapping("/listJob")
    @ResponseBody
    public Map<String,Object> listJob(@RequestBody Job job){
        List<Job> jobs = jobService.queryJob(job);
        Integer count = jobService.count(job);
        return MapControll.getInstance().success().page(jobs,count).getMap();
    }*/

    @GetMapping("/list")
    public String list(){
        return LIST;
    }

    @GetMapping("/stu_list")
    public String stuList(){
        return STULIST;
    }

    @PostMapping("/stu_list")
    @ResponseBody
    public Map<String,Object> stuList(@RequestBody Job job, HttpSession session){
        Student student = (Student) session.getAttribute("user");
        List<Job> jobs = new ArrayList<>();
        List<Student> students = studentService.queryListById(student.getId());
        List<Job> list = jobService.query(job);
        list.forEach(entity -> {
            students.forEach(student1 -> {
                if (entity.getStuId().equals(student1.getId())) {
                    entity.setStudent(student);
                    jobs.add(entity);
                }
            });
        });
        Integer count = jobService.count(job);
        return MapControll.getInstance().success().page(jobs, count).getMap();

    }

    @GetMapping("/add")
    public String add(ModelMap modelMap){
        /*List<Job> jobs = jobService.query(null);
        if(jobs.size()==0){
            return "job/addJob";
        }else{
            modelMap.addAttribute("jobs",jobs);
            return ADD;
        }*/

        return "job/addJob";
    }

    @GetMapping("/requestJob")
    public String requestJob(ModelMap modelMap){
        List<Job> jobs = jobService.queryJob(null);
        modelMap.addAttribute("jobs",jobs);
        return REQUESTJOB;



    }

    @GetMapping("/audit")
    public String audit(){
        return AUDIT;
    }

    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String ids,HttpSession httpSession){
        Student student = null;
        if(2==(Integer)httpSession.getAttribute("type")){

        }else{
            student = (Student)httpSession.getAttribute("user");;
        }
        if(student!=null){
            Teacher teacher = teacherService.queryByTeacherId(student.getTeacherId());
            for (String str : ids.split(",")) {
                if(studentCountService.queryById(student.getId()).getJobCount()==0){

                }else {
                    if("暂未审批".equals(jobService.queryJobById(Integer.parseInt(str)).getJobStatus())){
                        teacherCountService.decreaseJobCount(teacher.getId());
                        studentCountService.decreaseJobCount(student.getId());
                    }else{

                    }

                }
            }
        }
        int result = jobService.delete(ids);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String,Object> update(Job job){
        int result = jobService.updateJob(job);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap){
        Job job = jobService.detailByStudent(id);
        modelMap.addAttribute("id",id);
        if(job == null){
            return "job/add";
        }else{
            modelMap.addAttribute("job",job);
            return "job/update";
        }
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(Job job){
        List<Job> list = jobService.query(job);
        return MapControll.getInstance().success().put("data",list).getMap();
    }

    @PostMapping("/queryJobById")
    @ResponseBody
    public Map<String, Object> queryJobById(@RequestBody Job job){
        Integer jobId = job.getId();
        job = jobService.queryJobById(jobId);
        return MapControll.getInstance().success().add("data", job).getMap();
    }

    @PostMapping("/audit")
    @ResponseBody
    public Map<String,Object> audit(@RequestBody Job job, HttpSession session) {
        Teacher o = (Teacher) session.getAttribute("user");
        List<Job> jobs = jobService.queryRequestJob(job);
        List<Student> students = studentService.queryStudentByTeacher1(o.getId(),null);
        if (students.isEmpty()) {
            return MapControll.getInstance().nodata().getMap();
        }
        jobs.forEach(entity -> {
            students.forEach(student -> {
                if (entity.getStuId().equals(student.getId())) {
                    entity.setStudent(student);
                }
            });
        });
        Integer count = jobService.count(job);
        return MapControll.getInstance().success().page(jobs, count).getMap();
    }

    @PostMapping("/audit_agree")
    @ResponseBody
    public Map<String,Object> audit_agree(String ids,HttpSession httpSession){

        Teacher teacher = (Teacher) httpSession.getAttribute("user");
        Student student = null;
        for (String str : ids.split(",")) {
            student = studentService.queryById(jobService.queryJobById(Integer.parseInt(str)).getStuId());
            if(teacherCountService.queryById(teacher.getId()).getJobCount()==0){

            }else {
                if(jobService.queryJobById(Integer.parseInt(str)).getJobStatus().equals("暂未审批")){

                    if(teacherCountService.queryById(teacher.getId()).getJobCount()==0){

                    }else{
                        teacherCountService.decreaseJobCount(teacher.getId());
                        studentCountService.decreaseJobCount(student.getId());
                    }

                }
            }

        }

        int result = jobService.update(ids);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }

        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/refuse")
    @ResponseBody
    public Map<String,Object> refuse(String ids,HttpSession httpSession){
        Teacher teacher = (Teacher) httpSession.getAttribute("user");
        Student student = null;
        for (String str : ids.split(",")) {
            student = studentService.queryById(jobService.queryJobById(Integer.parseInt(str)).getStuId());
            if(teacherCountService.queryById(teacher.getId()).getJobCount()==0){

            }else {
                if(jobService.queryJobById(Integer.parseInt(str)).getJobStatus().equals("暂未审批")){

                    if(teacherCountService.queryById(teacher.getId()).getJobCount()==0){

                    }else{
                        teacherCountService.decreaseJobCount(teacher.getId());
                        studentCountService.decreaseJobCount(student.getId());
                    }

                }

            }

        }
        int result = jobService.refuse(ids);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }


        return MapControll.getInstance().success().getMap();
    }
}
