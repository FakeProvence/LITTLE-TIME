package com.example.little_time.bean;

import java.util.Date;

public class PlanLong {
    Integer plid, pid, delay_time;
    Date deadline;

    public Integer getPlid() {
        return plid;
    }

    public void setPlid(Integer plid) {
        this.plid = plid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getDelay_time() {
        return delay_time;
    }

    public void setDelay_time(Integer delay_time) {
        this.delay_time = delay_time;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
