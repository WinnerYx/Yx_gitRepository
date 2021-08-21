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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/studentAction")
public class StudentActionController {



    private static final String LIST = "studentAction/list";
    private static final String STULIST = "studentAction/stu_list";
    private static final String ADD = "studentAction/stu_add";
    private static final String UPDATE = "studentAction/stu_update";
    private static final String AUDIT = "studentAction/audit";


    @Autowired
    private StudentActionService studentActionService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherCountService teacherCountService;

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentCountService studentCountService;

    @GetMapping("/add")
    public String create(ModelMap modelMap){
        return ADD;
    }

    @PostMapping("/update_queryById")
    @ResponseBody
    public Map<String,Object> updateQueryById(Integer id){
        StudentAction studentAction = studentActionService.updateQueryById(id);
        return MapControll.getInstance().success().add("data",studentAction).getMap();
    }

    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(@RequestBody StudentAction studentAction, HttpSession session){
        Student student = (Student) session.getAttribute("user");
        studentAction.setStuId(student.getId());
        TeacherCount teacherCount = null;
        teacherCount = teacherCountService.queryById(student.getTeacherId());
        StudentCount studentCount = null;
        studentCount = studentCountService.queryById(student.getId());
        if(studentCount==null){
            studentCount = new StudentCount();
            studentCount.setStudentId(student.getId());
            studentCountService.createCount(studentCount);
        }
        studentCountService.increaseStudentActionCount(student.getId());
        if(teacherCount==null){
            teacherCount = new TeacherCount();
            teacherCount.setTeacherId(student.getTeacherId());
            teacherCountService.createCount(teacherCount);
        }
        teacherCountService.increaseStudentActionCount(student.getTeacherId());
        int result = studentActionService.create(studentAction);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String,Object> delete(@PathVariable("id") Integer id){
        int result = studentActionService.delete(id);
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
        student = studentService.queryById(student.getId());
        for (String str : ids.split(",")) {
            if(teacherCountService.queryById(teacher.getId()).getStudentActionCount()==0){

            }else {
                if(studentActionService.queryById(Integer.parseInt(str)).getStatus().equals("暂未审批")){
                    teacherCountService.decreaseStudentActionCount(teacher.getId());
                }else{

                }

            }
            if(studentCountService.queryById(student.getId()).getStudentActionCount()==0){

            }else{
                if(studentActionService.queryById(Integer.parseInt(str)).getStatus().equals("暂未审批")){
                    studentCountService.decreaseStudentActionCount(student.getId());
                }else{

                }
            }
        }
        int result = studentActionService.delete(ids);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String,Object> update(@RequestBody StudentAction studentAction,HttpSession httpSession){
        int result = studentActionService.update1(studentAction);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }

        return MapControll.getInstance().success().getMap();
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap){
        StudentAction studentAction = studentActionService.detail(id);
        modelMap.addAttribute("studentAction",studentAction);
        return UPDATE;
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(@RequestBody StudentAction studyAction){
        List<StudentAction> list = studentActionService.query(studyAction);
        Integer count = studentActionService.count(studyAction);
        return MapControll.getInstance().success().page(list,count).getMap();
    }

    @PostMapping("/stu_query")
    @ResponseBody
    public Map<String,Object> stu_query(@RequestBody StudentAction studyAction,HttpSession session){
        Student student = (Student) session.getAttribute("user");
        List<StudentAction> studentActions = new ArrayList<StudentAction>();
        List<Student> students = studentService.queryListById(student.getId());
        List<StudentAction> list = studentActionService.query(studyAction);
        list.forEach(entity->{
            students.forEach(student1 -> {
                if (entity.getStuId()==student1.getId()){
                    entity.setStudent(student1);
                    studentActions.add(entity);
                }
            });
        });
        Integer count = studentActionService.count(studyAction);
        return MapControll.getInstance().success().page(studentActions,count).getMap();
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
    public Map<String,Object> audit(@RequestBody StudentAction studyAction, HttpSession session){
        Teacher o =(Teacher) session.getAttribute("user");
        List<StudentAction> studyActions = studentActionService.query(studyAction);
        List<Student> students = studentService.queryStudentByTeacher1(o.getId(),null);
        if(students.isEmpty()){
            return MapControll.getInstance().nodata().getMap();
        }
        studyActions.forEach(entity ->{
           students.forEach(student -> {
               if (entity.getStuId().equals(student.getId())){
                   entity.setStudent(student);
               }
           });
        });

        Integer count = studentActionService.count(studyAction);
        return MapControll.getInstance().success().page(studyActions,count).getMap();
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

            StudentAction studyAction = new StudentAction();
            studyAction.setId(id);
            studyAction.setStatus(status);
            studyAction.setResult1(result);
            studentActionService.update(studyAction);
            return MapControll.getInstance().success().getMap();
        }
        if("1".equals(type)){ //users...
            StudentAction studyAction = new StudentAction();
            studyAction.setId(id);
            studyAction.setStatus(status);
            studentActionService.update(studyAction);
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
            student = studentService.queryById(studentActionService.queryById(Integer.parseInt(str)).getStuId());
            if(teacherCountService.queryById(teacher.getId()).getStudentActionCount()==0){

            }else {
                if(studentActionService.queryById(Integer.parseInt(str)).getStatus().equals("暂未审批")){
                    teacherCountService.decreaseStudentActionCount(teacher.getId());
                    if(studentCountService.queryById(student.getId()).getStudentActionCount()==0){

                    }else{
                        studentCountService.decreaseStudentActionCount(student.getId());
                    }

                }

            }

        }
        int result = studentActionService.update(ids);
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
            student = studentService.queryById(studentActionService.queryById(Integer.parseInt(str)).getStuId());
            if(teacherCountService.queryById(teacher.getId()).getStudentActionCount()==0){

            }else {
                if(studentActionService.queryById(Integer.parseInt(str)).getStatus().equals("暂未审批")){
                    teacherCountService.decreaseStudentActionCount(teacher.getId());
                    if(studentCountService.queryById(student.getId()).getStudentActionCount()==0){

                    }else{
                        studentCountService.decreaseStudentActionCount(student.getId());
                    }

                }

            }

        }
        int result = studentActionService.refuse(ids);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }

        return MapControll.getInstance().success().getMap();
    }


}
