package com.example.little_time.Util;

import com.example.little_time.bean.ResponseMessage;
import com.example.little_time.enums.ResultEnum;

public class ResultUtil {
    public static ResponseMessage GetResponseMessage(ResultEnum status, Object data) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setCode(status.getCode());
        responseMessage.setMsg(status.getMsg());
        responseMessage.setData(data);
        return responseMessage;
    }

    public static ResponseMessage GetResponseMessage(ResultEnum status) {
        return GetResponseMessage(status, null);
    }
}
