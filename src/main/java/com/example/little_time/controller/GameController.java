package com.example.little_time.controller;

import com.example.little_time.bean.ResponseMessage;
import com.example.little_time.enums.ResultEnum;
import com.example.little_time.Util.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @GetMapping("game/getFarmData")
    public ResponseMessage getFarmData(int uid) throws Exception {
        
        return ResultUtil.GetResponseMessage(ResultEnum.OK);
    }

    @GetMapping("game/getHealth")
    public ResponseMessage getHealth(int uid) throws Exception {

        return ResultUtil.GetResponseMessage(ResultEnum.OK);
    }


}
