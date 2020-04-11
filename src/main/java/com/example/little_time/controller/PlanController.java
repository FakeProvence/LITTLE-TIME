package com.example.little_time.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.little_time.bean.ResponseMessage;
import com.example.little_time.enums.ResultEnum;
import com.example.little_time.service.PlanService;
import com.example.little_time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PlanController {

    @Autowired
    PlanService planService;

    @PostMapping("/plan/post_short_plan")
    public ResponseMessage postShortPlan(@RequestBody JSONObject jsonObject) throws Exception {
        if ((!jsonObject.containsKey("Uid")) || (!jsonObject.containsKey("Category")) || (!jsonObject.containsKey("privilege")) || (!jsonObject.containsKey("content")))
            return ResultUtil.GetResponseMessage(ResultEnum.NECESSARY_ITEMS_NOT_FINISHED);
        int uid = jsonObject.getInteger("Uid"), category = jsonObject.getInteger("Category"), privilege = jsonObject.getInteger("privilege");
        String content = jsonObject.getString("content");
        int id = planService.postShortPlan(uid, category, privilege, content);
        switch (id) {
            case -1: {
                return ResultUtil.GetResponseMessage(ResultEnum.USER_NOT_EXIST);
            }
            case -2: {
                return ResultUtil.GetResponseMessage(ResultEnum.UNKOWN_ERROR);
            }
            default: {
                return ResultUtil.GetResponseMessage(ResultEnum.OK);
            }
        }
    }

    @GetMapping("/plan/get_short_plan")
    public ResponseMessage getShortPlan(Integer uid) throws Exception {
        List<Map> list = new ArrayList<>();
        list = planService.getShortPlan(uid);
        if (list.size() == 1) {
            Map map = new HashMap();
            map = list.get(0);
            if (map.containsKey("err"))
                return ResultUtil.GetResponseMessage(ResultEnum.USER_NOT_EXIST);
        }
        return ResultUtil.GetResponseMessage(ResultEnum.OK, list);
    }

    @GetMapping("plan/get_long_plan")
    public ResponseMessage getLongPlan(Integer uid) throws Exception {
        List<Map> list = new ArrayList<>();
        list = planService.getLongPlan(uid);
        if (list.size() == 1) {
            Map map = new HashMap();
            map = list.get(0);
            if (map.containsKey("err"))
                return ResultUtil.GetResponseMessage(ResultEnum.USER_NOT_EXIST);
        }
        return ResultUtil.GetResponseMessage(ResultEnum.OK, list);
    }

    @GetMapping("plan/get_finished_plan")
    public ResponseMessage getFinishedPlan(Integer uid) throws Exception {
        List<Map> list = new ArrayList<>();
        list = planService.getFinishedPlan(uid);
        if (list.size() == 1) {
            Map map = new HashMap();
            map = list.get(0);
            if (map.containsKey("err"))
                return ResultUtil.GetResponseMessage(ResultEnum.USER_NOT_EXIST);
        }
        return ResultUtil.GetResponseMessage(ResultEnum.OK);
    }
}
