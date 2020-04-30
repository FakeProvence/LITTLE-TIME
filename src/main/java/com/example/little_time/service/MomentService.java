package com.example.little_time.service;

import com.alibaba.fastjson.JSONObject;
import com.example.little_time.bean.PlanMoment;
import com.example.little_time.bean.PostMomentPrivilege;
import com.example.little_time.mapper.FriendMapper;
import com.example.little_time.mapper.MomentMapper;
import com.example.little_time.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MomentService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    MomentMapper momentMapper;

    @Autowired
    FriendMapper friendMapper;

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

    public Map GetMomentMsgDetail(Integer pmid, Integer uid) throws Exception {
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
        //没有权限
        if (pri == 0 || (!momentMapper.findMomentByPmid(pmid))) {
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
        if (jsonObject.containsKey("pid"))
            planMoment.setPid(jsonObject.getInteger("pid"));
        else {
            planMoment.setPid(null);
        }
        Date time = new Date();
        planMoment.setTime(time);
        momentMapper.addPlanMoment(planMoment);
        return planMoment.getPmid();
    }

    public void postMomentPrivilege(PostMomentPrivilege postMomentPrivilege) throws Exception {
        List<Integer> uid = postMomentPrivilege.getUid();
        Integer pri = postMomentPrivilege.getPrivilege(), pmid = postMomentPrivilege.getPmid();
        for (int i = 0; i < uid.size(); i++) {
            momentMapper.postMomentPrivilege(pri, pmid, uid.get(i));
        }
    }
}
