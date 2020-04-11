package com.example.little_time.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.little_time.bean.ResponseMessage;
import com.example.little_time.enums.ResultEnum;
import com.example.little_time.service.MomentService;
import com.example.little_time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MomentController {
    @Autowired
    MomentService momentService;

    @PostMapping("moment/add_moment")
    public ResponseMessage addPlanMoment(@RequestBody JSONObject jsonObject) throws Exception {
        int id = momentService.addPlanMoment(jsonObject);
        if (id == -1) {
            return ResultUtil.GetResponseMessage(ResultEnum.NECESSARY_ITEMS_NOT_FINISHED);
        } else {
            Map map = new HashMap();
            map.put("pmid", id);
            return ResultUtil.GetResponseMessage(ResultEnum.OK, map);
        }
    }

    @GetMapping("moment/get_praise_number")
    public ResponseMessage getPraiseNumber(Integer uid_i, Integer uid_f, Date date) throws Exception {
        Map map = new HashMap();
        map = momentService.getPraiseNumber(uid_i, uid_f, date);
        if (map.containsKey("err"))
            return ResultUtil.GetResponseMessage(ResultEnum.USER_NOT_EXIST);
        return ResultUtil.GetResponseMessage(ResultEnum.OK, map);
    }

    @GetMapping("moment/get_momentMsg")
    public ResponseMessage getMomentMsg(String pmid, Integer uid) throws Exception {
        if (pmid == null)
            return ResultUtil.GetResponseMessage(ResultEnum.NECESSARY_ITEMS_NOT_FINISHED);
        Map map = new HashMap();
        map = momentService.GetMomentMsgDetail(pmid, uid);
        return ResultUtil.GetResponseMessage(ResultEnum.OK);
    }

}
