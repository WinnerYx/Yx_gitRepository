package com.ssm.controller;


import com.ssm.entity.*;
import com.ssm.service.StudentService;
import com.ssm.service.WorkStatusService;
import com.ssm.utils.MapControll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/workStatus")
public class WorkStatusController {


    @Autowired
    private WorkStatusService workStatusService;
    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(WorkStatus workStatus){

        int result = workStatusService.create(workStatus);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String,Object> delete(@PathVariable("id") Integer id){
        int result = workStatusService.delete(id);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String ids){
        int result = workStatusService.delete(ids);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String,Object> update(WorkStatus workStatus){
        int result = workStatusService.update(workStatus);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap){

        WorkStatus workStatus = workStatusService.detailByStudent(id);
        modelMap.addAttribute("stuId",id);
        if(workStatus == null){
            return "workStatus/add";
        }else{
            modelMap.addAttribute("workStatus",workStatus);
            return "workStatus/update";
        }

    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(WorkStatus workStatus){
        List<WorkStatus> list = workStatusService.query(workStatus);
        return MapControll.getInstance().success().put("data",list).getMap();
    }

    @GetMapping("/list")
    public String studetntWorkStatusByStuId(HttpSession session, ModelMap modelMap){
        Student student = (Student) session.getAttribute("user");
        modelMap.addAttribute("id",student.getId());
        return "workStatus/stu_list";
    }

    @PostMapping("/stu_list")
    @ResponseBody
    public Map<String,Object> studetnttWorkStatusByStuId(Integer stuId){
        List<Student> students = studentService.queryListById(stuId);
        List<WorkStatus> workStatuses = workStatusService.queryByStuId(stuId);
        if(workStatuses.isEmpty()){
            return MapControll.getInstance().nodata().getMap();
        }
        students.forEach(entity->{
            workStatuses.forEach(workStatuse -> {
                if(entity.getId().equals(workStatuse.getStuId())){
                    entity.setWorkStatus(workStatuse);
                }
            });
        });
        Integer count = workStatuses.size();
        return MapControll.getInstance().success().page(students,count).getMap();
    }



    @GetMapping("/studentWorkStatus")
    public String studetntWorkStatus(HttpSession session, ModelMap modelMap){
        Teacher teacher = (Teacher) session.getAttribute("user");
        modelMap.addAttribute("teacherId",teacher.getId());
        return "workStatus/list";
    }
    @PostMapping("/studentWorkStatus")
    @ResponseBody
    public Map<String,Object> studetntWorkStatus(Integer teacherId,String stuName){
        List<Student> lists = new ArrayList<Student>();
        List<Student> students = studentService.queryStudentByTeacher1(teacherId,stuName);
        List<WorkStatus> workStatuses = workStatusService.query(null);
        if(students.isEmpty()){
            return MapControll.getInstance().nodata().getMap();
        }

        //设置关联关系
        students.forEach(entity->{
            workStatuses.forEach(workStatuse -> {
                if(entity.getId().equals(workStatuse.getStuId())){
                    entity.setWorkStatus(workStatuse);
                    lists.add(entity);
                }
            });
        });

        Integer count = lists.size();
        return MapControll.getInstance().success().page(lists,count).getMap();
    }



}
