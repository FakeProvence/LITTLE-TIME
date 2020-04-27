package com.example.little_time.service;

import com.example.little_time.bean.Photo;
import com.example.little_time.mapper.PhotoMapper;
import com.example.little_time.Util.PicUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
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

    public Map loadingPhoto(Integer pmid, Integer uid, Integer num) throws Exception {
        Map map = new HashMap();
        //权限内好友或者自己才可查看,先空着后面在写个服务
        //或者没有照片
        if (false||!photoMapper.findPhoto(pmid,num)) {
            map.put("err", "-1");
            return map;
        }

        map.put("filePath", photoMapper.loadingPhoto(pmid, num));
        return map;
    }

}
