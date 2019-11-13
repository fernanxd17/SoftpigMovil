package com.Softpig.Model;

import java.io.Serializable;
import java.util.Date;

public class Pig implements Serializable {

    private short idPig;
    private String state;
    private String sex;
    private short weigth;
    private String race;
    private String growthPhase;
    private String pigState;
    private String health;
    private String installation;
    private String birthDate;
    private String acquisitionDate;


    public Pig(short idPig, String state, String sex, short weigth, String race, String growthPhase,
               String pigState, String health,String installation, String birthDate, String acquisitionDate) {
        this.idPig = idPig;
        this.state = state;
        this.sex = sex;
        this.weigth = weigth;
        this.race = race;
        this.growthPhase = growthPhase;
        this.pigState = pigState;
        this.health = health;
        this.installation = installation;
        this.birthDate = birthDate;
        this.acquisitionDate = acquisitionDate;
    }

    public short getIdPig() {
        return idPig;
    }

    public String getState(){return state;}

    public String getSex() {
        return sex;
    }

    public short getWeigth() { return weigth; }

    public String getRace() {
        return race;
    }

    public String getGrowthPhase() {
        return growthPhase;
    }

    public String getPigState() {
        return pigState;
    }

    public String getHealth() {
        return health;
    }

    public String getInstallation() {
        return installation;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getAcquisitionDate() {
        return acquisitionDate;
    }

}
