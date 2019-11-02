package com.Softpig.Models;

import java.util.Date;

public class Alarm {

    private short idAlarm;
    private short idEmployee;
    private Date dateStart;
    private String issue;

    public Alarm(short idAlarm, short idEmployee, Date dateStart, String issue) {
        this.idAlarm = idAlarm;
        this.idEmployee = idEmployee;
        this.dateStart = dateStart;
        this.issue = issue;
    }

    public short getIdAlarm() {
        return idAlarm;
    }

    public short getIdEmployee() {
        return idEmployee;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public String getIssue() {
        return issue;
    }
}
