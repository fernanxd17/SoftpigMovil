package com.Softpig.Model;

public class GeneralReport {

    private String [] encabezados = {"Porcinos", "Hembras", "Machos", "Reproductoras", "Reproductores",
            "No Celos", "No Gestaciones", "No Partos", "Lechones", "Marrano", "Primal", "Gordo"};

    private int pigsFarm;
    private int malesFarm;
    private int femalesFarm;
    private int malesRpd;
    private int femaleRpd;
    private int lechones;
    private int marranos;
    private int promNaci;
    private int promCelos;
    private int promGest;

    public GeneralReport(){

    }



    public String[] getEncabezados() {
        return encabezados;
    }

    public void setEncabezados(String[] encabezados) {
        this.encabezados = encabezados;
    }

    public GeneralReport(int pigsFarm, int malesFarm, int femalesFarm, int malesRpd, int femaleRpd,
                         int lechones, int marranos, int promNaci, int promCelos, int promGest) {
        this.pigsFarm = pigsFarm;
        this.malesFarm = malesFarm;
        this.femalesFarm = femalesFarm;
        this.malesRpd = malesRpd;
        this.femaleRpd = femaleRpd;
        this.lechones = lechones;
        this.marranos = marranos;
        this.promNaci = promNaci;
        this.promCelos = promCelos;
        this.promGest = promGest;
    }

    public int getPigsFarm() {
        return pigsFarm;
    }

    public void setPigsFarm(int pigsFarm) {
        this.pigsFarm = pigsFarm;
    }

    public int getMalesFarm() {
        return malesFarm;
    }

    public void setMalesFarm(int malesFarm) {
        this.malesFarm = malesFarm;
    }

    public int getFemalesFarm() {
        return femalesFarm;
    }

    public void setFemalesFarm(int femalesFarm) {
        this.femalesFarm = femalesFarm;
    }

    public int getMalesRpd() {
        return malesRpd;
    }

    public void setMalesRpd(int malesRpd) {
        this.malesRpd = malesRpd;
    }

    public int getFemaleRpd() {
        return femaleRpd;
    }

    public void setFemaleRpd(int femaleRpd) {
        this.femaleRpd = femaleRpd;
    }

    public int getLechones() {
        return lechones;
    }

    public void setLechones(int lechones) {
        this.lechones = lechones;
    }

    public int getMarranos() {
        return marranos;
    }

    public void setMarranos(int marranos) {
        this.marranos = marranos;
    }

    public int getPromNaci() {
        return promNaci;
    }

    public void setPromNaci(int promNaci) {
        this.promNaci = promNaci;
    }

    public int getPromCelos() {
        return promCelos;
    }

    public void setPromCelos(int promCelos) {
        this.promCelos = promCelos;
    }

    public int getPromGest() {
        return promGest;
    }

    public void setPromGest(int promGest) {
        this.promGest = promGest;
    }
}
