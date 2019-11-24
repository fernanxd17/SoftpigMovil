package com.Softpig.Model;

public class ExamMale {
    private short idMale;
    private short idExam;
    private String name;
    private String descripcion;
    private String  examDate;
    private String result;

    public ExamMale(short idMale, short idExam, String examDate, String name, String descripcion, String result){
        this.idMale = idMale;
        this.idExam = idExam;
        this.examDate = examDate;
        this.result = result;
        this.name = name;
        this.descripcion = descripcion;
    }

    public short getIdMale() {
        return idMale;
    }

    public short getIdExam() {
        return idExam;
    }

    public String getExamDate() {
        return examDate;
    }

    public String getResult() {
        return result;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public String getName(){
        return name;
    }
}
