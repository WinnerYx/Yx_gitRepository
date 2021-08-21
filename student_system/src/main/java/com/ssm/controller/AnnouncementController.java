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
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/announcement")
public class AnnouncementController {



    private static final String LIST = "announcement/list";
    private static final String ADD = "announcement/add";
    private static final String UPDATE = "announcement/update";
    private static final String AUDIT = "announcement/audit";
    private static final String USERLIST = "announcement/user_list";


    @Autowired
    private AnnouncementService announcementService;
    @Autowired
    private TeacherService teacherService;

    @Autowired
    UserCountService userCountService;

    @Autowired
    UserService userService;

    @Autowired
    TeacherCountService teacherCountService;

    @GetMapping("/add")
    public String create(ModelMap modelMap){
        return ADD;
    }

    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(@RequestBody Announcement announcement, HttpSession session){
        User user = userService.queryByUserName("admin");
        /*TeacherCount teacherCount =null;*/
        UserCount userCount= null;
        /*teacherCount = teacherCountService.queryById(teacher.getId());*/
        userCount = userCountService.queryById(user.getId());
        if(userCount == null){
            userCount = new UserCount();
            userCount.setUserId(user.getId());
            userCountService.createCount(userCount);

        }
        Teacher teacher = (Teacher) session.getAttribute("user");
        announcement.setTeacherId(teacher.getId());
        int result = announcementService.create(announcement);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        userCountService.increaseAnnouncementCount(user.getId());
        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String,Object> delete(@PathVariable("id") Integer id){
        int result = announcementService.delete(id);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/update_queryById")
    @ResponseBody
    public Map<String,Object> updateQueryById(Integer id){
        Announcement announcement = announcementService.updateQueryById(id);
        return MapControll.getInstance().success().add("data",announcement).getMap();
    }

    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String ids,HttpSession httpSession){

        User user = userService.queryByUserName("admin");

        for (String str : ids.split(",")) {
            if(userCountService.queryById(user.getId()).getAnnouncementCount()==0){

            }else{
                if(announcementService.queryById(Integer.parseInt(str)).getStatus().equals("暂未审批")){
                    userCountService.decreaseAnnouncementCount(user.getId());
                }
            }
        }
        int result = announcementService.delete(ids);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }

        return MapControll.getInstance().success().getMap();
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String,Object> update(String ids,HttpSession httpSession){
        User user = userService.queryByUserName("admin");
        for (String str : ids.split(",")) {
            if(userCountService.queryById(user.getId()).getAnnouncementCount()==0){
                return MapControll.getInstance().success().getMap();
            }else{
                if(announcementService.queryById(Integer.parseInt(str)).getStatus().equals("暂未审批")){
                    userCountService.decreaseAnnouncementCount(user.getId());
                }
            }

        }
        int result = announcementService.update(ids);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }

        return MapControll.getInstance().success().getMap();
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap){
        Announcement announcement = announcementService.detail(id);
        modelMap.addAttribute("announcement",announcement);
        return UPDATE;
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(@RequestBody Announcement announcement){
        List<Announcement> list = announcementService.query(announcement);
        Integer count = announcementService.count(announcement);
        return MapControll.getInstance().success().page(list,count).getMap();
    }

    @GetMapping("/list")
    public String list(){
        return LIST;
    }

    @GetMapping("/user_list")
    public String userList(){
        return USERLIST;
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
    public Map<String,Object> audit(@RequestBody Announcement announcement,HttpSession session){
        Teacher t =(Teacher) session.getAttribute("user");
        List<Announcement> announcements = announcementService.query(announcement);
        List<Teacher> teachers = teacherService.query(t);
        if(teachers.isEmpty()){
            return MapControll.getInstance().nodata().getMap();
        }
        announcements.forEach(entity ->{
            teachers.forEach(teacher -> {
               if (entity.getTeacherId().equals(teacher.getId())){
                   entity.setTeacher(teacher);
               }
           });
        });

        Integer count = announcementService.count(announcement);
        return MapControll.getInstance().success().page(announcements,count).getMap();
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

            Announcement announcement = new Announcement();
            announcement.setId(id);
            announcement.setStatus(status);
            announcement.setResult1(result);
            announcementService.update(announcement);
            return MapControll.getInstance().success().getMap();
        }
        if("1".equals(type)){ //users...
            Announcement announcement = new Announcement();
            announcement.setId(id);
            announcement.setStatus(status);

            announcementService.update(announcement);
            return MapControll.getInstance().success().getMap();
        }
        return MapControll.getInstance().error().getMap();
    }

    @PostMapping("/audit_agree")
    @ResponseBody
    public Map<String,Object> audit_agree(String ids){
        int result = announcementService.update(ids);
        if(result<=0){
            return MapControll.getInstance().error().getMap();
        }
        return MapControll.getInstance().success().getMap();
    }



}
