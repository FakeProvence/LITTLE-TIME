package com.example.little_time.bean;

import java.util.Date;

public class PlanToday {
    Integer todayid,pid,is_finished;
    Date startTime,endTime;

    public Integer getTodayid() {
        return todayid;
    }

    public void setTodayid(Integer todayid) {
        this.todayid = todayid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getIs_finished() {
        return is_finished;
    }

    public void setIs_finished(Integer is_finished) {
        this.is_finished = is_finished;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
