package com.study.highig_springmvc4.web.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class UploadController {
    @RequestMapping(value = "/uploaddemo",method = RequestMethod.POST)
    public @ResponseBody String upload(MultipartFile file){
        try {
            FileUtils.writeByteArrayToFile(new File(
                    "h:/upload"+file.getOriginalFilename()),file.getBytes());
            return "success";
        }catch (IOException ex){
            ex.printStackTrace();
            return "fail";
        }
    }
}
