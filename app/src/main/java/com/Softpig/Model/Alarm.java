package com.Softpig.Model;

import java.util.Date;

public class Alarm {

    private short idAlarm;
    private short idEmployee;
    private String date;
    private String hour;
    private String issue;

    public Alarm(short idAlarm, short idEmployee, String date, String hour,  String issue) {
        this.idAlarm = idAlarm;
        this.idEmployee = idEmployee;
        this.date = date;
        this.issue = issue;
        this.hour = hour;
    }

    public short getIdAlarm() {
        return idAlarm;
    }

    public short getIdEmployee() {
        return idEmployee;
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    public String getIssue() {
        return issue;
    }
}
