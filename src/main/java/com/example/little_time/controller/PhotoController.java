package com.example.little_time.controller;

import com.example.little_time.bean.ResponseMessage;
import com.example.little_time.enums.ResultEnum;
import com.example.little_time.service.PhotoService;
import com.example.little_time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PhotoController {
    private ResourceLoader resourceLoader;

    @Autowired
    public void FileUploadController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    private final String path = "D:/image/0/";

    @Autowired
    PhotoService photoService;

    @PostMapping("/photo/upload")
    public ResponseMessage uploadPhoto(@RequestParam(value = "pmid", required = true) Integer pmid, @RequestParam(value = "uid", required = true) Integer uid, @RequestParam(value = "num", required = true) Integer num, @RequestParam("file") MultipartFile file) throws Exception {
        photoService.uploadPhoto(pmid, uid, num, file);
        return ResultUtil.GetResponseMessage(ResultEnum.OK);
    }

    @GetMapping("/photo/loading_pic")
    public ResponseEntity loadingPhoto(Integer pmid, Integer uid, Integer num) throws Exception {
        System.out.println(1 + "hhhhhh");
        Map map = new HashMap();
        map = photoService.loadingPhoto(pmid, uid, num);
        if (map.containsKey("err"))
            return ResponseEntity.ok(ResultUtil.GetResponseMessage(ResultEnum.LOGIC_ERROR));
        if (!map.containsKey("filePath"))
            return ResponseEntity.ok(ResultUtil.GetResponseMessage(ResultEnum.UNKOWN_ERROR));
        try {
            String filePath = map.get("filePath").toString();
            return ResponseEntity.ok(resourceLoader.getResource("file:" + filePath));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @RequestMapping("show")
    public ResponseEntity  show(String fileName){
        try {
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            return ResponseEntity.ok(resourceLoader.getResource("file:" + path + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
