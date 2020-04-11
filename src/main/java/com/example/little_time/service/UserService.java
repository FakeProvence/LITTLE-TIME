package com.example.little_time.service;

import com.example.little_time.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public boolean registerIn(String phone,String password,String nickname) throws Exception{
        if(phone==null||password==null||nickname==null)
            return false;
        if(userMapper.findUser(phone))
            return false;
        userMapper.registerUser(phone,password);
        int uid = userMapper.getUid(phone);
        if (userMapper.setUserNickname(uid,nickname))
            return true;
        return false;
    }

    public int userLogin(String phone,String password)throws Exception{
        if(phone==null||password==null)
            return -1;//信息缺失
        if(!userMapper.findUser(phone))
            return -2;//没有用户
        if(!userMapper.findPassword(phone,password))
            return -3;//密码错误
        return userMapper.getUid(phone);
    }
}
