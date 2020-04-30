package com.example.little_time.controller;

import com.example.little_time.bean.Friend;
import com.example.little_time.bean.ResponseMessage;
import com.example.little_time.enums.ResultEnum;
import com.example.little_time.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.little_time.Util.ResultUtil;

import java.util.List;

@RestController
public class FriendController {

    @Autowired
    FriendService friendService;

    @GetMapping("/friend/getFriendList")
    public ResponseMessage getFriendList(Integer uid) throws Exception {
        List<Friend> list = friendService.getFriendList(uid);
        return ResultUtil.GetResponseMessage(ResultEnum.OK, list);
    }
}
