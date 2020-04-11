package com.example.little_time.controller;

import com.example.little_time.bean.ResponseMessage;
import com.example.little_time.enums.ResultEnum;
import com.example.little_time.service.UserService;
import com.example.little_time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;


@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseMessage registerNewUser(@RequestBody JSONObject jsonObject) throws Exception {
        String phone = jsonObject.getString("phone"),
                password = jsonObject.getString("password"),
                nickname = jsonObject.getString("nickname");
        Map map = new HashMap();
        if (userService.registerIn(phone, password, nickname)) {
            map.put("Code", 0);
            return ResultUtil.GetResponseMessage(ResultEnum.OK, map);
        }
        map.put("Code", 1);
        return ResultUtil.GetResponseMessage(ResultEnum.OK, map);
    }

    @GetMapping("/login")
    public ResponseMessage login(String phone, String password) throws Exception {
        Map map = new HashMap();
        if (phone == null || password == null) {
            return ResultUtil.GetResponseMessage(ResultEnum.NECESSARY_ITEMS_NOT_FINISHED);
        }
        int id = userService.userLogin(phone, password);
        switch (id) {
            case -1: {
                return ResultUtil.GetResponseMessage(ResultEnum.NECESSARY_ITEMS_NOT_FINISHED);
            }
            case -2: {
                return ResultUtil.GetResponseMessage(ResultEnum.USER_EXCEPTION);
            }
            case -3: {
                return ResultUtil.GetResponseMessage(ResultEnum.PASSWORD_IS_NOT_NORMAL);
            }
            default: {
                map.put("uid", id);
                return ResultUtil.GetResponseMessage(ResultEnum.OK, map);
            }
        }
    }

}
