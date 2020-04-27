package com.example.little_time.controller;

import com.example.little_time.bean.ResponseMessage;
import com.example.little_time.enums.ResultEnum;
import com.example.little_time.Util.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DingController {

    @GetMapping("/game/PkWithSb")
    public ResponseMessage PKWithSb(int uid) throws Exception {
        return ResultUtil.GetResponseMessage(ResultEnum.OK);
    }
}
