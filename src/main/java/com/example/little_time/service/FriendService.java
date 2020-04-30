package com.example.little_time.service;

import com.example.little_time.bean.Friend;
import com.example.little_time.mapper.FriendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {

    @Autowired
    FriendMapper friendMapper;

    public List<Friend> getFriendList(Integer uid) throws Exception {
        List<Friend> uid_f = friendMapper.getFriendList(uid);
        return uid_f;
    }


}
