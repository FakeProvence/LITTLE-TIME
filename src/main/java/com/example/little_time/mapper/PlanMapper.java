package com.example.little_time.mapper;

import com.example.little_time.bean.*;
import org.apache.ibatis.annotations.*;
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

    //

    @Insert("Insert into plan_total(uid,privilege,is_long,category,content) values(#{uid},#{privilege},#{is_long},#{category},#{content})")
    @SelectKey(statement = "select Last_Insert_ID()", keyColumn = "pid", keyProperty = "pid", before = false, statementType = StatementType.STATEMENT, resultType = Integer.class)
    public Integer postPlan(PlanTotal planTotal) throws Exception;

    @Insert("Insert into plan_todo(pid,startTime,endTime) values(#{pid},#{startTime},#{endTime})")
    public Boolean postToDoPlan(int pid, String startTime, String endTime) throws Exception;

    @Insert("Insert into plan_today(pid,startTime,endTime,is_finished) values(#{pid},#{startTime},#{endTime},#{is_finished})")
    public Boolean postTodayPlan(int pid, String startTime, String endTime, int is_finished) throws Exception;

    @Insert("Insert into plan_longtime(pid,deadline,delay_time) values(#{pid},#{deadline},#{delay_time})")
    public Boolean postLongTime(Integer pid, Date deadline, Integer delay_time) throws Exception;

    @Insert("Insert into privilege(privilege,pid,uid) values(#{pri},#{pid},#{uid})")
    public void postPrivilege(Integer pri, Integer pid, Integer uid) throws Exception;

    @Select("select * from plan_total where uid=#{uid} and pid=#{pid}")
    public Map getPlanTotalByPidAndUid(Integer uid, Integer pid) throws Exception;

    @Select("select * from plan_today where prid=#{prid}")
    public Map getTodayByPrid(Integer prid) throws Exception;

    @Update("update plan_today set is_finished=#{is_finished} where todayid=#{todayid}")
    public void updateFinishedByTodayid(Integer todayid, Integer is_finished) throws Exception;

    @Insert("insert into plan_finished(pid,timeModel,startTime,endTime,timeLenget) values(#{pid},#{timeModel},#{startTime},#{endTime},#{timeLenget})")
    public void insertFinishedPlan(Integer pid, Integer timeModel, Date startTime, Date endTime, Integer timeLength) throws Exception;

    //查看今日待办是否有该pid
    @Select("select count(*) from plan_today where pid=#{pid}")
    public Boolean getPlanTodayExist(Integer pid) throws Exception;

    //查找今日待办
    @Select("select * from plan_today where pid=#{pid}")
    public PlanToday getTodayByPid(Integer pid) throws Exception;

    //查找用户计划
    @Select("select * from plan_total where uid=#{uid}")
    public List<PlanTotal> getTotalByUid(Integer uid) throws Exception;

    @Select("select count(*) from plan_todo where pid=#{pid}")
    public Boolean getPlanTodoExist(Integer pid) throws Exception;

    @Delete("delete from plan_todo where pid=#{pid}")
    public void deletePlanTodoBy(Integer pid);

    @Select("select * from plan_todo where pid=#{pid}")
    public PlanTodo getTodoByPid(Integer pid) throws Exception;

    @Select("select count(*) from plan_finished where pid=#{pid}")
    public Boolean getPlanFinishedExist(Integer pid) throws Exception;

    @Select("select * from plan_finished where pid=#{pid}")
    public PlanFinished getFinishedByPid(Integer pid) throws Exception;

    @Select("select count(*) from plan_delay where pid=#{pid}")
    public Boolean getPlanDelayExist(Integer pid) throws Exception;

    @Select("select * from plan_delay where pid=#{pid}")
    public PlanDelay getDelayByPid(Integer pid) throws Exception;

    @Select("select * from plan_total where uid=#{uid} and is_long=#{1}")
    public List<PlanTotal> getTotalLongByUid(Integer uid) throws Exception;

    @Select("select count(*) from plan_long where pid=#{pid}")
    public Boolean getPlanLongExist(Integer pid) throws Exception;

    @Select("select * from plan_long where pid=#{pid}")
    public PlanLong getLongByPid(Integer pid) throws Exception;
}
