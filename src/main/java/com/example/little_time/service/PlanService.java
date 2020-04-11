package com.example.little_time.service;

import com.example.little_time.mapper.PlanMapper;
import com.example.little_time.mapper.UserMapper;
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
        if(!planMapper.addShortPlan(uid,category,privilege,content)){
            return -2;//失败}
        }
        return 1;
    }

    public List<Map> getShortPlan(Integer uid)throws Exception{
        List<Map>list = new ArrayList<>();
        if (!userMapper.findUserByUid(uid)){
            Map map = new HashMap();
            map.put("err","-1");
            list.add(map);
            return list;
        }
        list = planMapper.getShortPlan(uid);
        return list;
    }

    public List<Map> getLongPlan(Integer uid)throws Exception{
        List<Map>list = new ArrayList<>();
        if (!userMapper.findUserByUid(uid)){
            Map map = new HashMap();
            map.put("err","-1");
            list.add(map);
            return list;
        }
        list = planMapper.getLongPlan(uid);
        return list;
    }

    public List<Map> getFinishedPlan(Integer uid)throws Exception{
        List<Map>list = new ArrayList<>();
        if (!userMapper.findUserByUid(uid)){
            Map map = new HashMap();
            map.put("err","-1");
            list.add(map);
            return list;
        }
        list = planMapper.getFinishedPlan(uid);
        return list;
    }
}
