package com.ssm.controller;

import com.ssm.entity.Power;
import com.ssm.entity.Teacher;
import com.ssm.service.ClazzService;
import com.ssm.service.PowerService;
import com.ssm.service.TeacherService;
import com.ssm.service.SubjectService;
import com.ssm.utils.MD5Utils;
import com.ssm.utils.MapControll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teacher")
public class TeacherController {


    private static final String LIST = "teacher/list";
    private static final String ADD = "teacher/add";
    private static final String UPDATE = "teacher/update";

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private PowerService powerService;

    @GetMapping("/add")
    public String create(ModelMap modelMap){
        return ADD;
    }

    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(@RequestBody Teacher teacher){
        Power power = new Power();
        teacher.setNeedPower("0");
        teacher.setPower("0");
        int result = teacherService.create(teacher);
        power.setTeacherId(teacher.getId());
        powerService.create(power);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String,Object> delete(@PathVariable("id") Integer id){
        int result = teacherService.delete(id);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String ids){
        int result = teacherService.delete(ids);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/updateAll")
    @ResponseBody
    public Map<String,Object> updateAll(String ids){
        int result = teacherService.updateAll(ids);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/updateAll1")
    @ResponseBody
    public Map<String,Object> updateAll1(String ids){
        int result = teacherService.updateAll1(ids);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String,Object> update(@RequestBody Teacher teacher){
        if(teacher.getTeacherPwd().equals(teacherService.queryByTeacherId(teacher.getId()).getTeacherPwd())){

        }else{
            teacher.setTeacherPwd(MD5Utils.getMD5(teacher.getTeacherPwd()));
        }

        int result = teacherService.update(teacher);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap){
        Teacher teacher = teacherService.detail(id);
        modelMap.addAttribute("teacher",teacher);
        return UPDATE;
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(@RequestBody Teacher teacher){
        List<Teacher> list = teacherService.query(teacher);
        Integer count = teacherService.count(teacher);
        return MapControll.getInstance().success().page(list,count).getMap();
    }

    @PostMapping("/queryByTeacherId")
    @ResponseBody
    public Map<String,Object> queryByTeacherId(HttpSession httpSession){
        Teacher teacher = (Teacher) httpSession.getAttribute("user");
        teacher = teacherService.queryByTeacherId(teacher.getId());
        return MapControll.getInstance().success().add("data",teacher).getMap();
    }

    @GetMapping("/list")
    public String list(){
        return LIST;
    }



}
