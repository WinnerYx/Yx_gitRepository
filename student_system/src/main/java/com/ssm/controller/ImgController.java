package com.ssm.controller;

import com.ssm.entity.Image;
import com.ssm.entity.Student;
import com.ssm.entity.WorkStatus;
import com.ssm.service.*;
import com.ssm.utils.MapControll;
import com.ssm.utils.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class ImgController {

    @Autowired
    ImageService imageService;

    @Autowired
    WorkStatusService workStatusService;

    @Autowired
    StudentService studentService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    ClazzService clazzService;

    @Autowired
    TeacherService teacherService;

    @RequestMapping("/upImage")
    @ResponseBody
    public Map up(MultipartFile file, HttpServletRequest request) {
        Map map = new HashMap();
        Map data = new HashMap();
        System.out.println("进入上传");
        //设置图片保存的本地路径  前缀路径
        //之前设置Tomcat的imgages路径
        String filePath = request.getServletContext().getRealPath("/static/images");
        System.out.println(filePath);
        // 获取原始图片的扩展名
        String originalFilename = file.getOriginalFilename();
        // 使用uuid生成文件新的名字
        String newFileName = UUID.randomUUID() + originalFilename;
        //.replace("-", "");去掉UUID的
        newFileName = newFileName.replace("-", "");
        // 封装上传文件位置的全路径就是前缀加上文件名称
        File targetFile = new File(filePath, newFileName);
        try {
            //保存图片，这里会抛一个异常
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", data);
        data.put("src", "http://localhost:8080/images/" + newFileName);
        return map;
    }
    //添加
    @RequestMapping(value = "/saveImage",produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String addImg(String imgurl,String stuId){
        System.out.println(stuId+"++++++++++++++++++++++++++++++++++");
        Integer sId = Integer.parseInt(stuId);
        if(imageService.list(sId)!=null){
            imageService.update(imgurl,sId);
        }else{
            imageService.save(imgurl,sId);
        }
        return "添加成功";
    }

    @RequestMapping("/queryImg")
    @ResponseBody
    public Map queryImg(String stuId){
        Map data = new HashMap();
        Image image = imageService.list(Integer.parseInt(stuId));
        String src = image.getName();
        if(src.equals("")||src==null){
            return data;
        }
        data.put("src",src);
        return data;
    }
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap){

        System.out.println(id+"++++++++++++++++++++++++++++++++");
        WorkStatus workStatus = workStatusService.detailByStudent(id);
        System.out.println(workStatus.getStuId()+"++++++++++++++++++++++++++++++++");
        modelMap.addAttribute("stuId",id);
        modelMap.addAttribute("workStatus",workStatus);
        return "student/check1";

    }

    @GetMapping("/upload")
    public String upload(){
        return "excel/upload";
    }

    @RequestMapping("/uploadExcel")
    @ResponseBody
    public Map<String, Object> upload(@RequestParam("file") MultipartFile excelFile) {
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("+++++++++++++++++++++++++++++++++++++++="+excelFile);
        try {
            // 使用POI解析表格数据
            List<String[]> list = POIUtils.readExcel(excelFile);

            for (String[] strings : list) {
                String stuNo = strings[0];
                String stuName = strings[1];
                String stuPwd = strings[2];
                String cardNo = strings[3];
                String gender = strings[4];
                String birthday = strings[5];
                String phone = strings[6];
                String pname = strings[7];
                String telephone = strings[8];
                String addr = strings[9];
                String status = strings[10];
                String joinDate = strings[11];
                Integer clazzId = clazzService.queryByName(strings[12]).getId();
                Integer subjectId = subjectService.queryByName(strings[13]).getId() ;
                Integer teacherId = teacherService.queryByTeacherName(strings[14]).getId();
                String flag = strings[15];
                Student student = new Student(stuNo,stuName,stuPwd,cardNo,gender,new Date(birthday)
                        ,phone,pname,telephone,addr,new Date(joinDate),status,clazzId,subjectId,teacherId,flag);

                student.setStatus(Student.StatusType.type_1);
                System.out.println(student);
                studentService.create(student);
            }
            return MapControll.getInstance().success().getMap();
        } catch (Exception e) {
            e.printStackTrace();
            // 文件解析失败
            return MapControll.getInstance().error().getMap();
        }
    }

}
