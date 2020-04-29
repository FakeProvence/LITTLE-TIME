package com.example.little_time.service;

import com.alibaba.fastjson.JSONObject;
import com.example.little_time.Util.TimeUtils;
import com.example.little_time.bean.*;
import com.example.little_time.mapper.PlanMapper;
import com.example.little_time.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public void postPrivilege(PostPrivilege postPrivilege) throws Exception {
        List<Integer> uid = postPrivilege.getUid();
        Integer pri = postPrivilege.getPrivilege(), pid = postPrivilege.getPid();
        for (int i = 0; i < uid.size(); i++) {
            planMapper.postPrivilege(pri, pid, uid.get(i));
        }
    }

    public Map postPlanFinished(JSONObject jsonObject) throws Exception {
        Map map = new HashMap();
        Map today;
        Integer uid = jsonObject.getInteger("uid"), prid = jsonObject.getInteger("prid"), timeLength = jsonObject.getInteger("timeLength"), timeModel = jsonObject.getInteger("timeModel");
        Date startTime = jsonObject.getDate("startTime"), endTime = jsonObject.getDate("endTime");
        today = planMapper.getTodayByPrid(prid);
        Integer pid = (Integer) today.get("pid"), todayid = (Integer) today.get("todayid");
        planMapper.updateFinishedByTodayid(todayid, 1);
        planMapper.insertFinishedPlan(pid, timeModel, startTime, endTime, timeLength);
        if (planMapper.getPlanTodoExist(pid)) {
            planMapper.deletePlanTodoBy(pid);
        }
        return map;
    }

    public List<Map> getPlanToday(Integer uid) throws Exception {
        List<Map> list = new ArrayList<>();
        List<PlanTotal> list1 = planMapper.getTotalByUid(uid);
        for (int i = 0; i < list1.size(); i++) {
            if (planMapper.getPlanTodayExist(list1.get(i).getPid())) {
                PlanToday planToday = planMapper.getTodayByPid(list1.get(i).getPid());
                Map map = new HashMap();
                map.put("deatail", list1.get(i));
                map.put("todayMSG", planToday);
                list.add(map);
            }
        }
        return list;
    }

    public List<Map> getPlanTodo(Integer uid) throws Exception {
        List<Map> list = new ArrayList<>();
        List<PlanTotal> list1 = planMapper.getTotalByUid(uid);
        for (int i = 0; i < list1.size(); i++) {
            if (planMapper.getPlanTodoExist(list1.get(i).getPid())) {
                PlanTodo planTodo = planMapper.getTodoByPid(list1.get(i).getPid());
                Map map = new HashMap();
                map.put("deatail", list1.get(i));
                map.put("todayMSG", planTodo);
                list.add(map);
            }
        }
        return list;
    }

    public List<Map> getPlanFinished(Integer uid) throws Exception {
        List<Map> list = new ArrayList<>();
        List<PlanTotal> list1 = planMapper.getTotalByUid(uid);
        for (int i = 0; i < list1.size(); i++) {
            if (planMapper.getPlanFinishedExist(list1.get(i).getPid())) {
                PlanFinished planFinished = planMapper.getFinishedByPid(list1.get(i).getPid());
                Map map = new HashMap();
                map.put("deatail", list1.get(i));
                map.put("todayMSG", planFinished);
                list.add(map);
            }
        }
        return list;
    }

    public List<Map> getPlanDelay(Integer uid) throws Exception {
        List<Map> list = new ArrayList<>();
        List<PlanTotal> list1 = planMapper.getTotalByUid(uid);
        for (int i = 0; i < list1.size(); i++) {
            if (planMapper.getPlanDelayExist(list1.get(i).getPid())) {
                PlanDelay planDelay = planMapper.getDelayByPid(list1.get(i).getPid());
                Map map = new HashMap();
                map.put("deatail", list1.get(i));
                map.put("todayMSG", planDelay);
                list.add(map);
            }
        }
        return list;
    }

    public List<Map> getPlanLong(Integer uid) throws Exception {
        List<Map> list = new ArrayList<>();
        List<PlanTotal> list1 = planMapper.getTotalLongByUid(uid);
        for (int i = 0; i < list1.size(); i++) {
            if (planMapper.getPlanLongExist(list1.get(i).getPid())) {
                PlanLong planLong = planMapper.getLongByPid(list1.get(i).getPid());
                Map map = new HashMap();
                map.put("deatail", list1.get(i));
                map.put("todayMSG", planLong);
                list.add(map);
            }
        }
        return list;
    }
}