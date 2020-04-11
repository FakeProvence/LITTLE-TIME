package com.example.little_time.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select count(*) from user_key where phone=#{phone}")
    public boolean findUser(String phone) throws Exception;

    @Insert("insert into user_key (phone,password) values (#{phone},#{password})")
    public boolean registerUser(String phone, String password) throws Exception;

    @Select("select uid from user_key where phone=#{phone}")
    public int getUid(String phone) throws Exception;

    @Insert("insert into user_msg (uid,nickname) values (#{uid},#{nickname})")
    public boolean setUserNickname(int uid, String nickname) throws Exception;

    @Select("select count(*) from user_key where phone=#{phone} and password=#{password}")
    public boolean findPassword(String phone, String password) throws Exception;

    @Select("select count(*) from user_key where uid=#{uid}")
    public boolean findUserByUid(int uid) throws Exception;
}
