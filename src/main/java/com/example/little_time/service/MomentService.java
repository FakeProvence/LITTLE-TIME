package com.example.little_time.service;

import com.alibaba.fastjson.JSONObject;
import com.example.little_time.bean.PlanMoment;
import com.example.little_time.mapper.MomentMapper;
import com.example.little_time.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MomentService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    MomentMapper momentMapper;

    public Map getPraiseNumber(Integer uid_i, Integer uid_f, Date date) throws Exception {
        Map map = new HashMap();
        if ((!userMapper.findUserByUid(uid_i)) || (!userMapper.findUserByUid(uid_f))) {
            map = new HashMap();
            map.put("err", "-1");
            return map;
        }
        map = momentMapper.getPraiseNubmer(uid_i, uid_f, date);
        return map;
    }

    public Map GetMomentMsgDetail(String pmid, Integer uid) throws Exception {
        Map map = new HashMap();
        if ((!userMapper.findUserByUid(uid)) || (!momentMapper.findMomentByPmid(pmid))) {
            map = new HashMap();
            map.put("err", "-1");
            return map;
        }
        map = momentMapper.getMomentMsgDetail(pmid, uid);
        return map;
    }

    public int addPlanMoment(JSONObject jsonObject) throws Exception {
        if (!jsonObject.containsKey("uid") || !jsonObject.containsKey("privilege"))
            return -1;
        PlanMoment planMoment = new PlanMoment();
        planMoment.setUid(jsonObject.getInteger("uid"));
        planMoment.setPrivilege(jsonObject.getInteger("privilege"));
        if (jsonObject.containsKey("photo_number"))
            planMoment.setPhoto_number(jsonObject.getInteger("photo_number"));
        else {
            planMoment.setPhoto_number(0);
        }
        if (jsonObject.containsKey("loc"))
            planMoment.setLoc(jsonObject.getString("loc"));
        else {
            planMoment.setLoc("");
        }
        if (jsonObject.containsKey("content"))
            planMoment.setContent(jsonObject.getString("content"));
        else {
            planMoment.setContent("");
        }
        Date time = new Date();
        planMoment.setTime(time);
        momentMapper.addPlanMoment(planMoment);
        return planMoment.getPmid();
    }
}
