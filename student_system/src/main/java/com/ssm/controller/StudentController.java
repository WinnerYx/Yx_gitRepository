package com.ssm.controller;

import com.ssm.entity.*;
import com.ssm.entity.Student;
import com.ssm.service.*;
import com.ssm.utils.MD5Utils;
import com.ssm.utils.MapControll;
import com.ssm.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {


    private static final String LIST = "student/list";
    private static final String ADD = "student/add";
    private static final String CHECK = "student/check";

    @Autowired
    private StudentService studentService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ClazzService clazzService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private RequestService requestService;
    @Autowired
    private StudentActionService studentActionService;
    @Autowired
    private StudyStatusService studyStatusService;
    @Autowired
    private JobService jobService;

    @GetMapping("/add")
    public String create(HttpSession session,ModelMap modelMap){
        Teacher teacher = (Teacher) session.getAttribute("user");
        List<Subject> subjects = subjectService.query(null);
        modelMap.addAttribute("subjects",subjects);
        modelMap.addAttribute("teacherId",teacher.getId());
        return ADD;
    }

    @GetMapping("/userAdd")
    public String userCreate(ModelMap modelMap){
        List<Teacher> teachers = teacherService.query(null);
        List<Subject> subjects = subjectService.query(null);
        modelMap.addAttribute("subjects",subjects);
        modelMap.addAttribute("teachers",teachers);
        return "student/userAdd";
    }

    @GetMapping("/teacher_list")
    public String teacherStudentList(ModelMap modelMap){
        return "student/teacher_student";
    }

    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(@RequestBody Student student){
        student.setStatus(Student.StatusType.type_1);
        int result = studentService.create(student);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String,Object> delete(@PathVariable("id") Integer id){
        int result = studentService.delete(id);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String ids){
        Integer  stuId = null;
        for (String str : ids.split(",")) {
            stuId = studentService.queryById(Integer.parseInt(str)).getId();
            if(requestService.queryByStuId(stuId)!=null){
                requestService.delete(requestService.queryByStuId(stuId).getId());
            }
            if(studentActionService.queryByStuId(stuId)!=null){
                studentActionService.delete(studentActionService.queryByStuId(stuId).getId());
            }
            if(studyStatusService.queryByStuId(stuId)!=null){
                studyStatusService.delete(studyStatusService.queryByStuId(stuId).getId());
            }
            if(jobService.queryByStuId(stuId)!=null){
                jobService.delete(String.valueOf(jobService.queryByStuId(stuId).getId()));
            }
            if(imageService.queryByStuId(stuId)!=null){
                imageService.delete(imageService.queryByStuId(stuId).getId());
            }


        }
        int result = studentService.delete(ids);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String,Object> update(@RequestBody Student student){
        if(student.getStuPwd()!=null){
            if(student.getStuPwd().equals(studentService.queryById(student.getId()).getStuPwd())){

            }else{
                student.setStuPwd(MD5Utils.getMD5(student.getStuPwd()));
            }
        }
        int result = studentService.update(student);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap,HttpSession httpSession){
        Student student = studentService.detail(id);
        List<Subject> subjects = subjectService.query(null);
        List<Teacher> teachers = teacherService.query(null);
        Subject subject = subjectService.queryById(student.getSubjectId());
        Clazz clazz = clazzService.queryById(student.getClazzId());
        Integer type = (Integer) httpSession.getAttribute("type");
        System.out.println(type+"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        if(1==(Integer) httpSession.getAttribute("type")){
            User user = (User) httpSession.getAttribute("user");
            modelMap.addAttribute("user",user);
            modelMap.addAttribute("subjects",subjects);
            modelMap.addAttribute("student",student);
            modelMap.addAttribute("subject",subject);
            modelMap.addAttribute("teachers",teachers);
            modelMap.addAttribute("clazz",clazz);
            return "student/user_check";
        }else if(2==(Integer)httpSession.getAttribute("type")){
            Teacher teacher = (Teacher) httpSession.getAttribute("user");
            modelMap.addAttribute("teacher",teacher);
            modelMap.addAttribute("subjects",subjects);
            modelMap.addAttribute("student",student);
            modelMap.addAttribute("subject",subject);
            modelMap.addAttribute("clazz",clazz);
            return CHECK;
        }else {
            student = (Student) httpSession.getAttribute("user");
            modelMap.addAttribute("teacher",student);
            modelMap.addAttribute("subjects",subjects);
            modelMap.addAttribute("student",student);
            modelMap.addAttribute("subject",subject);
            modelMap.addAttribute("clazz",clazz);
            return CHECK;
        }

    }

    @PostMapping("/queryById")
    @ResponseBody
    public Map<String,Object> queryById(@RequestBody Student student,HttpSession session){
        student =(Student) session.getAttribute("user");
        List<Student> students = studentService.queryListById(student.getId());
        Teacher teacher = teacherService.queryByTeacherId(students.get(0).getTeacherId());
        List<Subject> subjects = subjectService.query(null);
        List<Clazz> clazzes = clazzService.query(null);
        //设置关联关系
        students.forEach(entity->{
            entity.setTeacher(teacher);
            subjects.forEach(subject -> {
                if(subject.getId() == entity.getSubjectId()){
                    entity.setSubject(subject);
                }
            });
            clazzes.forEach(clazz -> {
                if(clazz.getId() == entity.getClazzId()){
                    entity.setClazz(clazz);
                }
            });
        });

        Integer count = studentService.count(student);
        return MapControll.getInstance().success().page(students,count).getMap();
    }

    @GetMapping("/list")
    public String list(){
        return LIST;
    }


    @GetMapping("/info")
    public String info(HttpSession session,ModelMap modelMap){
        //获取Student
        Student param = (Student) session.getAttribute("user");
        Student student = studentService.detail(param.getId());
        modelMap.put("student",student);
        return "student/info";
    }

    @GetMapping("/teacher_student")
    public String teacher_student(HttpSession session,ModelMap modelMap){
        Teacher teacher = (Teacher) session.getAttribute("user");
        List<Clazz> clazzes = clazzService.query(null);
        List<Subject> subjects = subjectService.query(null);
        modelMap.addAttribute("clazzes",clazzes);
        modelMap.addAttribute("subjects",subjects);
        modelMap.addAttribute("teacherId",teacher.getId());
        return "student/teacher_student";
    }

    @PostMapping("/teacher_student")
    @ResponseBody
    public Map<String,Object> teacher_student(@RequestBody Student student){

        List<Student> students = studentService.query(student);
        List<Subject> subjects = subjectService.query(null);
        List<Clazz> clazzes = clazzService.query(null);
        //设置关联关系
        students.forEach(entity->{
            subjects.forEach(subject -> {
                if(subject.getId().equals(entity.getSubjectId())){
                    entity.setSubject(subject);
                }
            });
            clazzes.forEach(clazz -> {
                if(clazz.getId().equals(entity.getClazzId())){
                    entity.setClazz(clazz);
                }
            });
        });

        Integer count = studentService.count(student);
        return MapControll.getInstance().success().page(students,count).getMap();
    }

    @GetMapping("/user_student")
    public String usesr_student(HttpSession session,ModelMap modelMap){
        return "student/user_student";
    }

    @PostMapping("/user_student")
    @ResponseBody
    public Map<String,Object> user_student(@RequestBody Student student){

        List<Student> students = studentService.query(student);
        List<Subject> subjects = subjectService.query(null);
        List<Clazz> clazzes = clazzService.query(null);
        //设置关联关系
        students.forEach(entity->{
            subjects.forEach(subject -> {
                if(subject.getId().equals(entity.getSubjectId())){
                    entity.setSubject(subject);
                }
            });
            clazzes.forEach(clazz -> {
                if(clazz.getId().equals(entity.getClazzId())){
                    entity.setClazz(clazz);
                }
            });
        });
        Integer count = studentService.count(student);
        return MapControll.getInstance().success().page(students,count).getMap();
    }



}
