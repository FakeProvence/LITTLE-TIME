package com.example.little_time.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface PlanMapper {
    @Insert("insert into plan_ongoing (uid,category,content,privilege,is_finished) values (#{uid},#{category},#{content},#{privilege},0)")
    public boolean addShortPlan(int uid, int category, int privilege, String content) throws Exception;

    @Select("select * from plan_ongoing where uid=#{uid}")
    public List<Map> getShortPlan(Integer uid) throws Exception;

    @Select("select * from plan_longtime where uid=#{uid}")
    public List<Map> getLongPlan(Integer uid) throws Exception;

    @Select("select * from plan_finished where uid=#{uid}")
    public List<Map> getFinishedPlan(Integer uid) throws Exception;
}
