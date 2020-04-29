package com.example.little_time.bean;

import java.util.Date;

public class PlanTodo {
    Integer todoid,pid;
    Date startTime,endTime;

    public Integer getTodoid() {
        return todoid;
    }

    public void setTodoid(Integer todoid) {
        this.todoid = todoid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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
