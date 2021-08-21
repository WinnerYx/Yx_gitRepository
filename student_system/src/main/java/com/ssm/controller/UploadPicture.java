package com.ssm.controller;

import com.ssm.entity.Image;
import com.ssm.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/uploadFile")
public class UploadPicture {

    @Autowired
    private ImageService imageService;
    @RequestMapping(value = "/uploadFilePicture", method = RequestMethod.POST)
    @ResponseBody
    public String fileUpload(MultipartFile file, Image image, ModelMap modelMap)throws IOException {
        String filePath = "E:\\Image";
        String originalFilename = file.getOriginalFilename();
        String newFileName = UUID.randomUUID()+originalFilename;

        File targetFile = new File(filePath,newFileName);
        file.transferTo(targetFile);
        image.setName(newFileName);


        return "index2";
    }


}
