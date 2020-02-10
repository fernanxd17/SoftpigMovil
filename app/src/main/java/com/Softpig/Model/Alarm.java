package com.Softpig.Model;

public class Alarm {

    private short idAlarm;
    private short idEmployee;
    private String date;
    private String hour;
    private String issue;

    public Alarm(final short idAlarm, final short idEmployee, final String date, final String hour,
                 final String issue) {
        this.idAlarm = idAlarm;
        this.idEmployee = idEmployee;
        this.date = date;
        this.issue = issue;
        this.hour = hour;
    }

    public Alarm(final String date, final String hour, final String issue){
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

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public void setIdAlarm(short idAlarm) {
        this.idAlarm = idAlarm;
    }

    public void setIdEmployee(short idEmployee) {
        this.idEmployee = idEmployee;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
