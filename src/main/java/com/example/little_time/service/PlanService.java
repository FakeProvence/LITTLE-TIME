package com.example.little_time.service;

import com.alibaba.fastjson.JSONObject;
import com.example.little_time.bean.PlanTotal;
import com.example.little_time.mapper.PlanMapper;
import com.example.little_time.mapper.UserMapper;
import com.example.little_time.Util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlanService {
    @Autowired
    PlanMapper planMapper;

    @Autowired
    UserMapper userMapper;

    public int postShortPlan(int uid, int category, int privilege, String content) throws Exception {
        if (!userMapper.findUserByUid(uid))
            return -1;//用户不存在
        if (!planMapper.addShortPlan(uid, category, privilege, content)) {
            return -2;//失败}
        }
        return 1;
    }

    public List<Map> getShortPlan(Integer uid) throws Exception {
        List<Map> list = new ArrayList<>();
        if (!userMapper.findUserByUid(uid)) {
            Map map = new HashMap();
            map.put("err", "-1");
            list.add(map);
            return list;
        }
        list = planMapper.getShortPlan(uid);
        return list;
    }

    public List<Map> getLongPlan(Integer uid) throws Exception {
        List<Map> list = new ArrayList<>();
        if (!userMapper.findUserByUid(uid)) {
            Map map = new HashMap();
            map.put("err", "-1");
            list.add(map);
            return list;
        }
        list = planMapper.getLongPlan(uid);
        return list;
    }

    public List<Map> getFinishedPlan(Integer uid) throws Exception {
        List<Map> list = new ArrayList<>();
        if (!userMapper.findUserByUid(uid)) {
            Map map = new HashMap();
            map.put("err", "-1");
            list.add(map);
            return list;
        }
        list = planMapper.getFinishedPlan(uid);
        return list;
    }

    public int postPlan(JSONObject jsonObject, int is_long) throws Exception {
        PlanTotal planTotal = new PlanTotal();
        planTotal.setUid(jsonObject.getInteger("uid"));
        planTotal.setCategory(jsonObject.getInteger("category"));
        planTotal.setPrivilege(jsonObject.getInteger("privilege"));
        planTotal.setIs_long(is_long);
        planTotal.setContent(jsonObject.getString("content"));
        planMapper.postPlan(planTotal);
        return planTotal.getPid();
    }

    public boolean postToDoPlan(JSONObject jsonObject) throws Exception {
        int pid = jsonObject.getInteger("pid");
        String startTime = jsonObject.getString("startTime"), endTime = jsonObject.getString("endTime");
        boolean suc = planMapper.postToDoPlan(pid, startTime, endTime);
        if (!suc)
            return false;
        if (startTime == null || TimeUtils.is_today(startTime)) {
            if (planMapper.postTodayPlan(pid, startTime, endTime, 0))
                return true;
        } else {
            return true;
        }
        return false;
    }

    public Boolean postLongTime(JSONObject jsonObject) throws Exception {
        boolean suc = planMapper.postLongTime(jsonObject.getInteger("pid"), jsonObject.getDate("deadline"), 0);
        if (!suc)
            return false;
        if (TimeUtils.todayIsNow(jsonObject.getDate("deadline")) || TimeUtils.todayIsBefore(jsonObject.getDate("deadline"))) {
            if (planMapper.postTodayPlan(jsonObject.getInteger("pid"), null, null, 0))
                return true;
        } else {
            return true;
        }
        return false;
    }
}
