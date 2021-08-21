package com.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/main")
public class ToMainJspController {

    public static final String STUMAINJSP = "stuMainJsp";
    public static final String TEAMAINJSP = "teaMainJsp";
    public static final String ADMINIMAINJSP = "adminiMainJsp";

    @RequestMapping("/toMainJsp")
    public String toMainJsp( HttpSession session) {
        int type = (int) session.getAttribute("type");
        if (type == 1) {
            return ADMINIMAINJSP;
        } else if (type == 2) {
            return TEAMAINJSP;
        } else {
            return STUMAINJSP;
        }
    }

}
