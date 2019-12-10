package com.Softpig.Model;

public class GeneralReport {

    private String [] encabezados = {"Porcinos", "Hembras", "Machos", "Reproductoras", "Reproductores",
            "No Celos", "No Gestaciones", "No Partos", "Lechones", "Marrano", "Primal", "Gordo"};

    private int pigsFarm;
    private int femalesFarm;
    private int malesFarm;
    private int activeMales;
    private int activeFemales;
    private int totalLechones;

    public GeneralReport(){

    }

    public GeneralReport(int pigsFarm, int femalesFarm, int malesFarm, int activeMales, int activeFemales, int totalLechones) {
        this.pigsFarm = pigsFarm;
        this.femalesFarm = femalesFarm;
        this.malesFarm = malesFarm;
        this.activeMales = activeMales;
        this.activeFemales = activeFemales;
        this.totalLechones = totalLechones;
    }

    public String[] getEncabezados() {
        return encabezados;
    }

    public void setEncabezados(String[] encabezados) {
        this.encabezados = encabezados;
    }

    public int getPigsFarm() {
        return pigsFarm;
    }

    public void setPigsFarm(int pigsFarm) {
        this.pigsFarm = pigsFarm;
    }

    public int getFemalesFarm() {
        return femalesFarm;
    }

    public void setFemalesFarm(int femalesFarm) {
        this.femalesFarm = femalesFarm;
    }

    public int getMalesFarm() {
        return malesFarm;
    }

    public void setMalesFarm(int malesFarm) {
        this.malesFarm = malesFarm;
    }

    public int getActiveMales() {
        return activeMales;
    }

    public void setActiveMales(int activeMales) {
        this.activeMales = activeMales;
    }

    public int getActiveFemales() {
        return activeFemales;
    }

    public void setActiveFemales(int activeFemales) {
        this.activeFemales = activeFemales;
    }

    public int getTotalLechones() {
        return totalLechones;
    }

    public void setTotalLechones(int totalLechones) {
        this.totalLechones = totalLechones;
    }
}
