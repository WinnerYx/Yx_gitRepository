package com.ssm.controller;

import com.ssm.entity.*;
import com.ssm.service.*;
import com.ssm.utils.MapControll;
import com.ssm.utils.MapParameter;
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
@RequestMapping("/request")
public class RequestController {



    private static final String LIST = "request/list";
    private static final String ADD = "request/stuRequestAdd";
    private static final String STUUPDATE = "request/stuRequestUpdate";
    private static final String AUDIT = "request/audit";
    private static final String STULIST = "request/stu_list";

    @Autowired
    private RequestService requestService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherCountService teacherCountService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserCountService userCountService;
    @Autowired
    private StudentCountService studentCountService;
    @Autowired
    private TeacherService teacherService;


    @GetMapping("/user_studentRequest_list")
    public String user_studentRequest_list(){
        return "request/user_studentRequest_list";
    }

    @GetMapping("/stuRequestAdd")
    public String create(ModelMap modelMap){
        return ADD;
    }

    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(@RequestBody Request request, HttpSession session){
        int days = request.getDays();
        Student student = (Student) session.getAttribute("user");
        request.setStuId(student.getId());
        StudentCount studentCount = null;
        studentCount = studentCountService.queryById(student.getId());
        if(studentCount==null){
            studentCount = new StudentCount();
            studentCount.setStudentId(student.getId());
            studentCountService.createCount(studentCount);
        }
        studentCountService.increaseRequestCount(student.getId());
        if(days<=3){
            student = studentService.queryById(student.getId());
            TeacherCount teacherCount = null;
            teacherCount = teacherCountService.queryById(student.getTeacherId());
            if(teacherCount==null){
                teacherCount = new TeacherCount();
                teacherCount.setTeacherId(student.getTeacherId());
                teacherCountService.createCount(teacherCount);
            }
            teacherCountService.increaseRequestCount(student.getTeacherId());
        }else{
            User user = userService.queryByUserName("admin");
            UserCount userCount= null;
            userCount = userCountService.queryById(user.getId());
            if(userCount == null){
                userCount = new UserCount();
                userCount.setUserId(user.getId());
                userCountService.createCount(userCount);
            }
            userCountService.increaseRequestCount(user.getId());
        }
        int result = requestService.create(request);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String,Object> delete(@PathVariable("id") Integer id){
        int result = requestService.delete(id);
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
        User user = userService.queryByUserName("admin");
        student = studentService.queryById(student.getId());
        for (String str : ids.split(",")) {
            Request request = requestService.queryById(Integer.parseInt(str));
            if(request.getDays()>3){
                if(userCountService.queryById(user.getId()).getRequestCount()==0){

                }else {
                    if(requestService.queryById(Integer.parseInt(str)).getStatus().equals("暂未审批")){
                        userCountService.decreaseRequestCount(user.getId());
                        studentCountService.decreaseRequestCount(student.getId());
                    }
                }
            }else{
                if(teacherCountService.queryById(teacher.getId()).getRequestCount()==0){

                }else{
                    if(requestService.queryById(Integer.parseInt(str)).getStatus().equals("暂未审批")){
                        teacherCountService.decreaseRequestCount(teacher.getId());
                        studentCountService.decreaseRequestCount(student.getId());
                    }

                }
            }

        }
        int result = requestService.delete(ids);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String,Object> update(String ids,HttpSession httpSession){
        Student student = (Student) httpSession.getAttribute("user");
        Teacher teacher = teacherService.queryByTeacherId(student.getTeacherId());
        User user = userService.queryByUserName("admin");
        for (String str : ids.split(",")) {
            if(requestService.queryById(Integer.parseInt(str)).getDays()>3){
                if(userCountService.queryById(user.getId()).getRequestCount()==0){

                }else {
                    if(requestService.queryById(Integer.parseInt(str)).getStatus().equals("暂未审批")){
                        userCountService.decreaseRequestCount(user.getId());
                        if(studentCountService.queryById(student.getId()).getStudentActionCount()==0){

                        }else{
                            studentCountService.decreaseRequestCount(student.getId());
                        }

                    }

                }
            }else{
                if(teacherCountService.queryById(teacher.getId()).getRequestCount()==0){

                }else{
                    if(requestService.queryById(Integer.parseInt(str)).getStatus().equals("暂未审批")){
                        teacherCountService.decreaseRequestCount(teacher.getId());
                        if(studentCountService.queryById(student.getId()).getStudentActionCount()==0){

                        }else{
                            studentCountService.decreaseRequestCount(student.getId());
                        }
                    }

                }

            }
        }
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = requestService.update(str);
        }
        int result = flag;
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }

        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/stuUpdate")
    @ResponseBody
    public Map<String,Object> stuUpdate(@RequestBody Request request){
        int result = requestService.update1(request);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap){
        Request request = requestService.detail(id);
        String result = null;
        modelMap.addAttribute("request",request);
        return STUUPDATE;


    }

    /*@PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(@RequestBody Request request){
        List<Request> list = requestService.query(request);
        list.forEach(entity ->{

        });

        Integer count = requestService.count(request);
        return MapControll.getInstance().success().page(list,count).getMap();
    }*/

    @GetMapping("/list")
    public String list(){
        return LIST;
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
    public Map<String,Object> audit(@RequestBody Request request,HttpSession session){
        Teacher o =(Teacher) session.getAttribute("user");
        List<Request> requests = requestService.query(request);
        List<Student> students = studentService.queryStudentByTeacher1(o.getId(),null);
        requests.forEach(entity ->{
           students.forEach(student -> {
               if (entity.getStuId().equals(student.getId())){
                   entity.setStudent(student);
               }
           });
        });
        Integer count = requestService.count(request);
        return MapControll.getInstance().success().page(requests,count).getMap();
    }

    @PostMapping("/user_audit")
    @ResponseBody
    public Map<String,Object> user_audit(@RequestBody Request request){
        List<Request> requests = requestService.userQuery(request);
        List<Student> students = studentService.query(null);
        if(students.isEmpty()){
            return MapControll.getInstance().nodata().getMap();
        }
        requests.forEach(entity ->{
            students.forEach(student -> {
                if (entity.getStuId().equals(student.getId())){
                    entity.setStudent(student);
                }

            });
        });
        Integer count = requests.size();
        return MapControll.getInstance().success().page(requests,count).getMap();
    }

    @GetMapping("/audit")
    public String audit(){
        return AUDIT;
    }

    @GetMapping("/stu_list")
    public String stuAudit(){
        return STULIST;
    }

    @PostMapping("/stu_list")
    @ResponseBody
    public Map<String,Object> stuAudit(@RequestBody Request request,HttpSession session){
        Student student =(Student) session.getAttribute("user");
        List<Request> list = new ArrayList<Request>();
        List<Request> requests = requestService.studentQuery(request);
        List<Student> students = studentService.queryListById(student.getId());
        if(students.isEmpty()){
            return MapControll.getInstance().nodata().getMap();
        }
        requests.forEach(entity ->{
            students.forEach(student1 -> {
                if (entity.getStuId().equals(student1.getId())){
                    entity.setStudent(student1);
                    list.add(entity);
                }
            });
        });

        Integer count = requestService.count(request);
        return MapControll.getInstance().success().page(list,count).getMap();
    }

    @GetMapping("/audit_add")
    public String audit_add(Integer id,ModelMap modelMap){
        modelMap.put("id",id);
        return "request/audit_add";
    }

    @PostMapping("/update_queryById")
    @ResponseBody
    public Map<String,Object> updateQueryById(Integer id){
        Request request = requestService.updateQueryById(id);
        return MapControll.getInstance().success().add("data",request).getMap();
    }

    /*@PostMapping("/audit_add")
    @ResponseBody
    public Map<String,Object> audit_add(Integer id, String status, String type, String result){
        if("2".equals(type)){//teacher do...

            Request request = new Request();
            request.setId(id);
            request.setStatus(status);
            request.setResult1(result);
            requestService.update(request);
            return MapControll.getInstance().success().getMap();
        }
        if("1".equals(type)){ //users...
            Request request = new Request();
            request.setId(id);
            request.setStatus(status);
            requestService.update(request);
            return MapControll.getInstance().success().getMap();
        }
        return MapControll.getInstance().error().getMap();
    }*/

    @PostMapping("/audit_agree")
    @ResponseBody
    public Map<String,Object> audit_agree(String ids,HttpSession httpSession){
        User user = userService.queryByUserName("admin");
        Student student = null;
        Teacher teacher = null;
        for (String str : ids.split(",")) {
            if(requestService.queryById(Integer.parseInt(str)).getDays()>3){
                student = studentService.queryById(requestService.queryById(Integer.parseInt(str)).getStuId());
                if(userCountService.queryById(user.getId()).getRequestCount()==0){

                }else {
                    if(requestService.queryById(Integer.parseInt(str)).getStatus().equals("暂未审批")){
                        userCountService.decreaseRequestCount(user.getId());
                        studentCountService.decreaseRequestCount(student.getId());
                    }

                }
            }else{
                student = studentService.queryById(requestService.queryById(Integer.parseInt(str)).getStuId());
                teacher = teacherService.queryByTeacherId(student.getTeacherId());
                if(teacherCountService.queryById(teacher.getId()).getRequestCount()==0){

                }else{
                    if(requestService.queryById(Integer.parseInt(str)).getStatus().equals("暂未审批")){
                        teacherCountService.decreaseRequestCount(teacher.getId());
                        studentCountService.decreaseRequestCount(student.getId());
                    }

                }

            }

        }
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = requestService.update(str);
        }
        int result = flag;
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }



        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/refuse")
    @ResponseBody
    public Map<String,Object> refuse(String ids,HttpSession httpSession){
        User user = userService.queryByUserName("admin");
        Student student = null;
        Teacher teacher = null;
        for (String str : ids.split(",")) {
            if(requestService.queryById(Integer.parseInt(str)).getDays()>3){
                student = studentService.queryById(requestService.queryById(Integer.parseInt(str)).getStuId());
                if(userCountService.queryById(user.getId()).getRequestCount()==0){

                }else {
                    if(requestService.queryById(Integer.parseInt(str)).getStatus().equals("暂未审批")){
                        userCountService.decreaseRequestCount(user.getId());
                        studentCountService.decreaseRequestCount(student.getId());
                    }

                }
            }else{
                student = studentService.queryById(requestService.queryById(Integer.parseInt(str)).getStuId());
                teacher = teacherService.queryByTeacherId(student.getTeacherId());
                if(teacherCountService.queryById(teacher.getId()).getRequestCount()==0){

                }else{
                    if(requestService.queryById(Integer.parseInt(str)).getStatus().equals("暂未审批")){
                        teacherCountService.decreaseRequestCount(teacher.getId());
                        studentCountService.decreaseRequestCount(student.getId());
                    }

                }

            }

        }
        int result = requestService.refuse(ids);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }

        return MapControll.getInstance().success().getMap();
    }



}
