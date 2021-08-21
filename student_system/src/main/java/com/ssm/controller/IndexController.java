package com.ssm.controller;

import com.ssm.entity.P_Count;
import com.ssm.entity.*;
import com.ssm.service.*;
import com.ssm.utils.MD5Utils;
import com.ssm.utils.MapControll;
import com.ssm.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    UserService userService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;
    @Autowired
    RequestService requestService;
    @Autowired
    StudentActionService studentActionService;
    @Autowired
    StudyStatusService studyStatusService;
    @Autowired
    SectionService sectionService;
    @Autowired
    AnnouncementService announcementService;
    @Autowired
    ClazzService clazzService;

    @Autowired
    CountService countService;

    @GetMapping("/index")
    public String login(){
        return "index";
    }

    @GetMapping("/main")
    public String main(ModelMap modelMap, HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute("user");
        return null;
    }

    @GetMapping("/info")
    public String info(){
        return "info";
    }

    /**
     * 首页数字统计
     * @return
     */
    @PostMapping("/sum")
    @ResponseBody
    public Map<String,Object> sum(HttpSession session) {
        Person person = null;
        String type = session.getAttribute("type").toString();
        Integer t = Integer.parseInt(type);
        if (t.equals(1)) {
            person = (User) session.getAttribute("user");
        } else if (t.equals(2)) {
            person = (Teacher) session.getAttribute("user");
        } else if (t.equals(3)) {
            person = (Student) session.getAttribute("user");
        }

        List<Announcement> announcements = announcementService.queryByResult1("通过");

        P_Count p_count = countService.queryById(person.getId(), t);
        Map<String, Object> map = MapParameter.getInstance()
                .add("requestCnt", p_count.getRequestCount())
                .add("studentActionCnt", p_count.getStudentActionCount())
                .add("studyStatusCnt", p_count.getStudyStatusCount())
                .add("announcementCount", p_count.getAnnouncementCount())
                .add("jobCnt", p_count.getJobCount())
                .add("announcements", announcements)
                .getMap();
        List<Clazz> clazzes = clazzService.query(null);
        List<String> clazzNames = new ArrayList<>();
        Student studentWork = null;
        List<String> studentPoorCounts = new ArrayList<>();
        List<Student> studentWorkCount = new ArrayList<>();
        List<String> studentWorkCounts = new ArrayList<>();
        List<Student> students = studentService.query(null);
        List<Student> studentsPoors = null;
        for (Clazz c : clazzes) {
            clazzNames.add(c.getClazzName());
            studentsPoors = studentService.queryByClazzIdAndPoor(c.getId(), "是");
            studentPoorCounts.add(String.valueOf(studentsPoors.size()));
            for (Student s : students) {
                studentWork = studentService.queryByClazzIdAndWork(c.getId(), s.getId());
                if(studentWork!=null){
                    studentWorkCount.add(studentWork);
                }

            }
            studentWorkCounts.add(String.valueOf(studentWorkCount.size()));
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++"+studentWorkCount.size());
            studentWorkCount.clear();
        }

        map.put("clazzName", clazzNames);
        map.put("studentPoorCounts", studentPoorCounts);
        map.put("studentWorkCounts", studentWorkCounts);
        return MapControll.getInstance().success().add("data", map).getMap();
    }

    @GetMapping("/pwd")
    public String pwd(){
        return "pwd";
    }
    @PostMapping("/pwd")
    @ResponseBody
    public Map<String,Object> pwd(Integer id,String type,String sourcePwd,String newPwd){
        if("1".equals(type)){
            User user = userService.detail(id);
            if(user.getUserPwd().equals(MD5Utils.getMD5(sourcePwd))){
                User entity = new User();
                entity.setId(id);
                entity.setUserPwd(MD5Utils.getMD5(newPwd));
                int update = userService.update(entity);
                if(update>0){
                    return MapControll.getInstance().success().getMap();
                }else{
                    return MapControll.getInstance().error().getMap();
                }
            }else{
                return MapControll.getInstance().error("原密码错误").getMap();
            }

        }
        if("2".equals(type)){
            Teacher teacher = teacherService.detail(id);
            if(teacher.getTeacherPwd().equals(MD5Utils.getMD5(sourcePwd))){
                Teacher entity = new Teacher();
                entity.setId(id);
                entity.setTeacherPwd(MD5Utils.getMD5(newPwd));
                int update = teacherService.update(entity);
                if(update>0){
                    return MapControll.getInstance().success().getMap();
                }else{
                    return MapControll.getInstance().error().getMap();
                }
            }else{
                return MapControll.getInstance().error("原密码错误").getMap();
            }
        }
        if("3".equals(type)){
            Student student = studentService.detail(id);
            if(student.getStuPwd().equals(MD5Utils.getMD5(sourcePwd))){
                Student entity = new Student();
                entity.setId(id);
                entity.setStuPwd(MD5Utils.getMD5(newPwd));
                int update = studentService.update(entity);
                if(update>0){
                    return MapControll.getInstance().success().getMap();
                }else{
                    return MapControll.getInstance().error().getMap();
                }
            }else{
                return MapControll.getInstance().error("原密码错误").getMap();
            }

        }
        return MapControll.getInstance().error().getMap();
    }
}
