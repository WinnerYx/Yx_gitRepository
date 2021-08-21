package com.ssm.controller;

import com.ssm.entity.TeacherCount;
import com.ssm.service.TeacherCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacherCount")
public class TeacherCountController {

    @Autowired
    TeacherCountService teacherCountService;



}
