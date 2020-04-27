package com.example.little_time.Util;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
public class PicUtils {


    /**
     * @param file     文件
     * @param path     文件存放路径
     * @param fileName 原文件名
     * @return
     */
    public static Map upload(MultipartFile file, String path, String fileName) {

        String newFileName = RandomUtils.getRandomId() + RandomUtils.generateCode(6) + fileName;
        String realPath = path + "/" + newFileName;
        File dest = new File(realPath);
        Map map = new HashMap();
        //Determine the parent directory
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            //save file
            file.transferTo(dest);
            map.put("path", realPath);
            map.put("fileName", newFileName);
            return map;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            map.put("err", -1);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            map.put("err", -1);
            return map;
        }
    }
}


