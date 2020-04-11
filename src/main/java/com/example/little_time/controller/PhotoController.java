package com.example.little_time.controller;

import com.example.little_time.bean.ResponseMessage;
import com.example.little_time.enums.ResultEnum;
import com.example.little_time.service.PhotoService;
import com.example.little_time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @PostMapping("/photo/upload")
    public ResponseMessage uploadPhoto(@RequestParam(value = "pmid", required = true) Integer pmid, @RequestParam(value = "uid", required = true) Integer uid, @RequestParam(value = "num", required = true) Integer num, @RequestParam("file") MultipartFile file) throws Exception {
        photoService.uploadPhoto(pmid, uid, num, file);
        return ResultUtil.GetResponseMessage(ResultEnum.OK);
    }

    @GetMapping("/photo/loading_pic")
    public ResponseEntity show(String fileName) {


//        try {
//            return ResponseEntity.ok(resourceLoader.getResource("file:" + path + fileName));
//        } catch (Exception e) {
            return ResponseEntity.notFound().build();
//        }

    }
}
