package com.example.little_time.mapper;

import com.example.little_time.bean.PlanMoment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.mapping.StatementType;

import java.util.Date;
import java.util.Map;

public interface MomentMapper {

    @Select("select plan_praise from data_daily where uid_i=#{uid_i} and uid_f=#{uid_f} and date=#{date}")
    public Map getPraiseNubmer(Integer uid_i, Integer uid_f, Date date) throws Exception;

    @Select("select count(*) from plan_moment where pmid=#{pmid}")
    public Boolean findMomentByPmid(Integer pmid) throws Exception;

    @Select("select * from plan_moment where pmid={pmid} and uid={uid}")
    public Map getMomentMsgDetail(Integer pmid, Integer uid) throws Exception;

    @Insert("Insert into plan_moment(uid,content,loc,privilege,time,photo_number) values(#{uid},#{content},#{loc},#{privilege},#{time},#{photo_number})")
    @SelectKey(statement = "select Last_Insert_ID()", keyColumn = "pmid", keyProperty = "pmid", before = false, statementType = StatementType.STATEMENT, resultType = Integer.class)
    public int addPlanMoment(PlanMoment planMoment) throws Exception;

    @Insert("Insert into privilege_moment(privilege,pmid,uid) values(#{pri},#{pmid},#{uid})")
    public void postMomentPrivilege(Integer pri, Integer pmid, Integer uid) throws Exception;

    @Select("select privilege from plan_moment where pmid=#{pmid}")
    public Integer getPrivilegeInPlanMoment(Integer pmid) throws Exception;

    @Select("select uid from plan_moment where pmid=#{pmid}")
    public Integer getUidInPlanMoment(Integer pmid) throws Exception;

    @Select("select count(*) from privilege_moment where pmid=#{pmid} and uid=#{uid} and privilege=#{privilege}")
    public Boolean ifCanSeeByPri(Integer pmid, Integer uid, Integer privilege) throws Exception;
}
