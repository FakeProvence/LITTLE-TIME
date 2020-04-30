package com.example.little_time.service;

import com.example.little_time.bean.Photo;
import com.example.little_time.mapper.FriendMapper;
import com.example.little_time.mapper.MomentMapper;
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

    @Autowired
    MomentMapper momentMapper;

    @Autowired
    FriendMapper friendMapper;

    public Boolean uploadPhoto(Integer pmid, Integer uid, Integer num, MultipartFile file) throws Exception {
        //define file path to save
//        String localPath = "D:/image/" + uid.toString();
        String localPath = "../../../../../../home/Provence/image/" + uid.toString();
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
        Integer pmid_uid = momentMapper.getUidInPlanMoment(pmid);
        Integer pri = pmid_uid == uid ? 1 : momentMapper.getPrivilegeInPlanMoment(pmid);
        if (pri == 2) {
            pri = friendMapper.ifIsFriend(pmid_uid, uid);
        } else if (pri == 3) {
            Boolean ppri = momentMapper.ifCanSeeByPri(pmid, uid, 3);
            if (ppri) pri = 1;
            else pri = 0;
        } else if (pri == 4) {
            Boolean ppri = momentMapper.ifCanSeeByPri(pmid, uid, 4);
            if (!ppri) {
                pri = friendMapper.ifIsFriend(pmid_uid, uid);
            } else {
                pri = 0;
            }
        }
        if (pri == 0 || !photoMapper.findPhoto(pmid, num)) {
            map.put("err", "-1");
            return map;
        }
        map.put("filePath", photoMapper.loadingPhoto(pmid, num));
        return map;
    }

}
