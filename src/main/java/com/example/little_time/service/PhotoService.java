package com.example.little_time.service;

import com.example.little_time.bean.Photo;
import com.example.little_time.mapper.PhotoMapper;
import com.example.little_time.utils.PicUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class PhotoService {

    @Autowired
    PhotoMapper photoMapper;

    public Boolean uploadPhoto(Integer pmid, Integer uid, Integer num, MultipartFile file) throws Exception {
        //define file path to save
        String localPath = "D:/image/" + uid.toString();
        //get file name
        String fileName = file.getOriginalFilename();
        Map map = PicUtils.upload(file, localPath, fileName);
        if (map.containsKey("err"))
            return false;
        Photo photo = new Photo();
        photo.setNum(num);
        photo.setPmid(pmid);
        photo.setUid(uid);
        photo.setPhotoName(map.get("fileName").toString());
        photo.setPhotoPath(map.get("path").toString());
        if (photoMapper.uploadPhoto(photo))
            return true;
        return false;
    }

}
