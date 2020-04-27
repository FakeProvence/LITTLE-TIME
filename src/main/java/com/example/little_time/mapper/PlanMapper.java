package com.example.little_time.mapper;

import com.example.little_time.bean.PlanTotal;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.mapping.StatementType;

import java.util.Date;
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

    @Insert("Insert into plan_total(uid,privilege,is_long,category,content) values(#{uid},#{privilege},#{is_long},#{category},#{content})")
    @SelectKey(statement = "select Last_Insert_ID()", keyColumn = "pid", keyProperty = "pid", before = false, statementType = StatementType.STATEMENT, resultType = Integer.class)
    public Integer postPlan(PlanTotal planTotal) throws Exception;

    @Insert("Insert into plan_todo(pid,startTime,endTime) values(#{pid},#{startTime},#{endTime})")
    public Boolean postToDoPlan(int pid, String startTime, String endTime) throws Exception;

    @Insert("Insert into plan_today(pid,startTime,endTime,is_finished) values(#{pid},#{startTime},#{endTime},#{is_finished})")
    public Boolean postTodayPlan(int pid, String startTime, String endTime, int is_finished) throws Exception;

    @Insert("Insert into plan_longtime(pid,deadline,delay_time) values(#{pid},#{deadline},#{delay_time})")
    public Boolean postLongTime(Integer pid, Date deadline, Integer delay_time) throws Exception;
}
