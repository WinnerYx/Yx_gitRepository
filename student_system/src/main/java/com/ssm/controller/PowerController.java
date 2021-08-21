package com.ssm.controller;

import com.ssm.entity.Power;
import com.ssm.entity.Teacher;
import com.ssm.service.PowerService;
import com.ssm.service.TeacherService;
import com.ssm.utils.MapControll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/power")
public class PowerController {

    @Autowired
    PowerService powerService;

    @Autowired
    TeacherService teacherService;

    @GetMapping
    @RequestMapping("/needPower")
    public String needPower(HttpSession httpSession){
        Teacher teacher = (Teacher) httpSession.getAttribute("user");
        Power power = null;
        power = powerService.queryByTeacherId(teacher.getId());
        if(power==null){
            power = new Power();
            power.setTeacherId(teacher.getId());
            power.setValue(0);
            powerService.create(power);
        }
        powerService.needPower(teacher.getId());
        return "student/check";
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(@RequestBody Teacher teacher){
        List<Teacher> list = teacherService.query(teacher);
        Power power = null;

        for(Teacher t:list){
            power = powerService.queryByTeacherId(t.getId());
            if(power.getValue()==1){
                t.setNeedPower("是");
            }else{
                t.setNeedPower("否");
            }
            if(Integer.parseInt(t.getPower())==0){
                t.setPower("否");
            }else{
                t.setPower("是");
            }
        }
        Integer count = teacherService.count(teacher);
        return MapControll.getInstance().success().page(list,count).getMap();
    }

    @GetMapping
    @RequestMapping("/queryByTeacherId")
    public Map<String,Object> queryByTeacherId(HttpSession httpSession){
        Map<String,Object> map = new HashMap<>();
        Teacher teacher = (Teacher) httpSession.getAttribute("user");
        Power power = null;
        power = powerService.queryByTeacherId(teacher.getId());
        map.put("power",power);
        return map;
    }

    @GetMapping
    @RequestMapping("/list")
    public String list(){
        return "power/list";
    }
}
