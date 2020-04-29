package com.example.little_time.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.little_time.bean.PostPrivilege;
import com.example.little_time.bean.ResponseMessage;
import com.example.little_time.enums.ResultEnum;
import com.example.little_time.service.PlanService;
import com.example.little_time.Util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/plan/addToDo")
    public ResponseMessage postToDoPlan(@RequestBody JSONObject jsonObject) throws Exception {
        if ((!jsonObject.containsKey("uid")) || (!jsonObject.containsKey("category")) || (!jsonObject.containsKey("privilege")) || (!jsonObject.containsKey("content")))
            return ResultUtil.GetResponseMessage(ResultEnum.NECESSARY_ITEMS_NOT_FINISHED);
        int pid = planService.postPlan(jsonObject, 0);
        String startTime, endTime;
        jsonObject.put("pid", pid);
        if (!jsonObject.containsKey("startTime")) {
            jsonObject.put("startTime", null);
        }
        if (!jsonObject.containsKey("endTime")) {
            jsonObject.put("endTime", null);
        }
        Map map = new HashMap();
        map.put("pid", pid);
        if (planService.postToDoPlan(jsonObject))
            return ResultUtil.GetResponseMessage(ResultEnum.OK, map);
        return ResultUtil.GetResponseMessage(ResultEnum.UNKOWN_ERROR);
    }

    @PostMapping("/plan/postLongTime")
    public ResponseMessage postLongTime(@RequestBody JSONObject jsonObject) throws Exception {
        if ((!jsonObject.containsKey("uid")) || (!jsonObject.containsKey("category")) || (!jsonObject.containsKey("privilege")) || (!jsonObject.containsKey("content")) || (!jsonObject.containsKey("deadline")))
            return ResultUtil.GetResponseMessage(ResultEnum.NECESSARY_ITEMS_NOT_FINISHED);
        int pid = planService.postPlan(jsonObject, 1);
        jsonObject.put("pid", pid);
        Map map = new HashMap();
        map.put("pid", pid);
        if (planService.postLongTime(jsonObject))
            return ResultUtil.GetResponseMessage(ResultEnum.OK, map);
        return ResultUtil.GetResponseMessage(ResultEnum.UNKOWN_ERROR);
    }

    @PostMapping("/plan/postPrivilege")
    public ResponseMessage postPrivilege(@RequestBody PostPrivilege postPrivilege) throws Exception {
        planService.postPrivilege(postPrivilege);
        return ResultUtil.GetResponseMessage(ResultEnum.OK);
    }

    @PostMapping("/plan/postPlanFinished")
    public ResponseMessage postPlanFinished(@RequestBody JSONObject jsonObject) throws Exception {
        if ((!jsonObject.containsKey("uid")) || (!jsonObject.containsKey("prid")) || (!jsonObject.containsKey("startTime")) || (!jsonObject.containsKey("endTime")) || (!jsonObject.containsKey("timeModel")) || (!jsonObject.containsKey("timeLength"))) {
            return ResultUtil.GetResponseMessage(ResultEnum.NECESSARY_ITEMS_NOT_FINISHED);
        }
        Map map = planService.postPlanFinished(jsonObject);
        return ResultUtil.GetResponseMessage(ResultEnum.OK, map);
    }

    @GetMapping("/plan/getPlanToday")
    public ResponseMessage getPlanToday(@RequestBody JSONObject jsonObject) throws Exception {
        Integer uid = jsonObject.getInteger("uid");
        List<Map> list = planService.getPlanToday(uid);
        return ResultUtil.GetResponseMessage(ResultEnum.OK, list);
    }

    @GetMapping("/plan/getPlanTodo")
    public ResponseMessage getPlanTodo(@RequestBody JSONObject jsonObject) throws Exception {
        Integer uid = jsonObject.getInteger("uid");
        List<Map> list = planService.getPlanTodo(uid);
        return ResultUtil.GetResponseMessage(ResultEnum.OK, list);
    }

    @GetMapping("/plan/getPlanFinished")
    public ResponseMessage getPlanFinished(@RequestBody JSONObject jsonObject) throws Exception {
        Integer uid = jsonObject.getInteger("uid");
        List<Map> list = planService.getPlanFinished(uid);
        return ResultUtil.GetResponseMessage(ResultEnum.OK, list);
    }

    @GetMapping("/plan/getPlanDelay")
    public ResponseMessage getPlanDelay(@RequestBody JSONObject jsonObject) throws Exception {
        Integer uid = jsonObject.getInteger("uid");
        List<Map> list = planService.getPlanDelay(uid);
        return ResultUtil.GetResponseMessage(ResultEnum.OK, list);
    }

    @GetMapping("/plan/getPlanLong")
    public ResponseMessage getPlanLong(@RequestBody JSONObject jsonObject) throws Exception {
        Integer uid = jsonObject.getInteger("uid");
        List<Map> list = planService.getPlanLong(uid);
        return ResultUtil.GetResponseMessage(ResultEnum.OK, list);
    }
}