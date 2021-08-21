package com.ssm.controller;

import com.ssm.entity.*;
import com.ssm.service.*;
import com.ssm.utils.MapControll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/study")
public class StudyStatusController {



    private static final String LIST = "studyStatus/list";
    private static final String STULIST = "studyStatus/stu_list";
    private static final String STUADD = "studyStatus/stuAdd";
    private static final String STUUPDATE = "studyStatus/stu_update";
    private static final String AUDIT = "studyStatus/audit";


    @Autowired
    private StudyStatusService studyStatusService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherCountService teacherCountService;
    @Autowired
    private StudentCountService studentCountService;


    @GetMapping("/stuAdd")
    public String create(ModelMap modelMap){
        return STUADD;
    }

    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(@RequestBody StudyStatus studyStatus, HttpSession session){
        Student student = (Student) session.getAttribute("user");
        studyStatus.setStuId(student.getId());
        Teacher teacher = teacherService.queryByTeacherId(student.getTeacherId());
        TeacherCount teacherCount= null;
        teacherCount = teacherCountService.queryById(teacher.getId());
        StudentCount studentCount = null;
        studentCount = studentCountService.queryById(student.getId());
        if(studentCount==null){
            studentCount = new StudentCount();
            studentCount.setStudentId(student.getId());
            studentCountService.createCount(studentCount);
        }
        studentCountService.increaseStudyStatusCount(student.getId());
        if(teacherCount == null){
            teacherCount = new TeacherCount();
            teacherCount.setTeacherId(teacher.getId());
            teacherCountService.createCount(teacherCount);
        }
        teacherCountService.increaseStudyStatusCount(teacher.getId());
        int result = studyStatusService.create(studyStatus);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String,Object> delete(@PathVariable("id") Integer id){
        int result = studyStatusService.delete(id);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String ids,HttpSession httpSession){
        Student student = (Student) httpSession.getAttribute("user");
        Teacher teacher = teacherService.queryByTeacherId(student.getTeacherId());
        for (String str : ids.split(",")) {
            if(teacherCountService.queryById(teacher.getId()).getStudyStatusCount()==0){

            }else {
                    if (studyStatusService.queryById(Integer.parseInt(str)).getStatus() == "暂未审批") {
                        teacherCountService.decreaseStudyStatusCount(teacher.getId());
                    }
                }
            if(studentCountService.queryById(student.getId()).getStudyStatusCount()==0){

            }else{
                if (studyStatusService.queryById(Integer.parseInt(str)).getStatus() == "暂未审批") {
                    studentCountService.decreaseStudyStatusCount(student.getId());
                }
            }
            }


        int result = studyStatusService.delete(ids);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/stu_update")
    @ResponseBody
    public Map<String,Object> update(@RequestBody StudyStatus studyStatus,HttpSession httpSession){
        int result = studyStatusService.update1(studyStatus);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }

        return MapControll.getInstance().success().getMap();
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap){
        StudyStatus studyStatus = studyStatusService.detail(id);
        modelMap.addAttribute("studyStatus",studyStatus);
        return STUUPDATE;
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(@RequestBody StudyStatus studyStatus){
        List<StudyStatus> list = studyStatusService.query(studyStatus);
        Integer count = studyStatusService.count(studyStatus);
        return MapControll.getInstance().success().page(list,count).getMap();
    }

    @PostMapping("/stu_query")
    @ResponseBody
    public Map<String,Object> studentQuery(@RequestBody StudyStatus studyStatus,HttpSession session){
        Student student = (Student) session.getAttribute("user");
        List<StudyStatus> studyStatuses = new ArrayList<StudyStatus>();
        List<Student> students = studentService.queryListById(student.getId());
        List<StudyStatus> list = studyStatusService.query(studyStatus);
        Integer count = studyStatusService.count(studyStatus);
        list.forEach(entity->{
            students.forEach(student1 -> {
                if (entity.getStuId()==student1.getId()){
                    entity.setStudent(student1);
                    studyStatuses.add(entity);
                }
            });
        });
        return MapControll.getInstance().success().page(studyStatuses,count).getMap();
    }

    @PostMapping("/update_queryById")
    @ResponseBody
    public Map<String,Object> updateQueryById(Integer id){
        StudyStatus studyStatus = studyStatusService.updateQueryById(id);
        return MapControll.getInstance().success().add("data",studyStatus).getMap();
    }

    @GetMapping("/list")
    public String list(){
        return LIST;
    }

    @GetMapping("/stu_list")
    public String stuList(){
        return STULIST;
    }


    @PostMapping("/upload")
    @ResponseBody
    public Map<String,Object> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        String base = request.getServletContext().getRealPath("/upload");
        try {
            String fileName = file.getOriginalFilename();
            String ext = fileName.substring(fileName.lastIndexOf("."),fileName.length());
            String path= UUID.randomUUID().toString()+ext;
            System.out.println(base+"/"+path);
            File newFile=new File(base+"/"+path);
            file.transferTo(newFile);
            return  MapControll.getInstance().success().add("data",path).getMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  MapControll.getInstance().error().getMap();
    }



    @PostMapping("/audit")
    @ResponseBody
    public Map<String,Object> audit(@RequestBody StudyStatus studyStatus,HttpSession session){
        Teacher o =(Teacher) session.getAttribute("user");
        List<Student> students = studentService.queryStudentByTeacher1(o.getId(),null);
        List<StudyStatus> studyStatuses = studyStatusService.query(studyStatus);

        if(students.isEmpty()){
            return MapControll.getInstance().nodata().getMap();
        }
        studyStatuses.forEach(entity ->{
           students.forEach(student -> {
               if (entity.getStuId().equals(student.getId())){

                   entity.setStudent(student);
               }
           });
        });

        Integer count = studyStatusService.count(studyStatus);
        return MapControll.getInstance().success().page(studyStatuses,count).getMap();
    }

    @GetMapping("/audit")
    public String audit(){
        return AUDIT;
    }

    @GetMapping("/audit_add")
    public String audit_add(Integer id,ModelMap modelMap){
        modelMap.put("id",id);
        return "request/audit_add";
    }

    @PostMapping("/audit_add")
    @ResponseBody
    public Map<String,Object> audit_add(Integer id, String status, String type, String result){
        if("2".equals(type)){//teacher do...

            StudyStatus studyStatus = new StudyStatus();
            studyStatus.setId(id);
            studyStatus.setStatus(status);
            studyStatus.setResult1(result);
            studyStatusService.update(studyStatus);
            return MapControll.getInstance().success().getMap();
        }
        if("1".equals(type)){ //users...
            StudyStatus studyStatus = new StudyStatus();
            studyStatus.setId(id);
            studyStatus.setStatus(status);

            studyStatusService.update(studyStatus);
            return MapControll.getInstance().success().getMap();
        }
        return MapControll.getInstance().error().getMap();
    }

    @PostMapping("/audit_agree")
    @ResponseBody
    public Map<String,Object> audit_agree(String ids,HttpSession httpSession){
        Teacher teacher = (Teacher) httpSession.getAttribute("user");
        Student student = null;
        for (String str : ids.split(",")) {

            student = studentService.queryById(studyStatusService.queryById(Integer.parseInt(str)).getStuId());
            if(teacherCountService.queryById(teacher.getId()).getStudyStatusCount()==0){

            }else {
                if(studyStatusService.queryById(Integer.parseInt(str)).getStatus().equals("暂未审批")){
                    teacherCountService.decreaseStudyStatusCount(teacher.getId());
                    if(studentCountService.queryById(student.getId()).getStudyStatusCount()==0){

                    }else{
                        studentCountService.decreaseStudyStatusCount(student.getId());
                    }

                }

            }

        }
        int result = studyStatusService.update(ids);
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

            student = studentService.queryById(studyStatusService.queryById(Integer.parseInt(str)).getStuId());
            if(teacherCountService.queryById(teacher.getId()).getStudyStatusCount()==0){

            }else {
                if(studyStatusService.queryById(Integer.parseInt(str)).getStatus().equals("暂未审批")){
                    teacherCountService.decreaseStudyStatusCount(teacher.getId());
                    if(studentCountService.queryById(student.getId()).getStudentActionCount()==0){

                    }else{
                        studentCountService.decreaseStudyStatusCount(student.getId());
                    }

                }

            }

        }
        int result = studyStatusService.refuse(ids);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }


        return MapControll.getInstance().success().getMap();
    }



}
