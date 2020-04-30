package com.example.little_time.mapper;

import com.example.little_time.bean.Friend;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FriendMapper {
    @Select("select * from friend where uid_i=#{uid}")
    public List<Friend> getFriendList(Integer uid) throws Exception;

    @Select("select count(*) from friend where uid_i=#{uid_i} and uid_f=#{uid_f}")
    public Integer ifIsFriend(Integer uid_f, Integer uid_i) throws Exception;
}
