package com.Softpig.Model;

import java.util.Date;

public class Pig {

    private short idPig;
    private String sex;
    private short weigth;
    private short idRace;
    private String growthPhase;
    private String pigState;
    private String health;
    private short idInstalation;
    private Date birthDate;
    private Date acquisitionDate;

    public Pig(short idPig, String sex, short weigth, short idRace, String growthPhase,
               String pigState, String health, short idInstalation, Date birthDate, Date acquisitionDate) {
        this.idPig = idPig;
        this.sex = sex;
        this.weigth = weigth;
        this.idRace = idRace;
        this.growthPhase = growthPhase;
        this.pigState = pigState;
        this.health = health;
        this.idInstalation = idInstalation;
        this.birthDate = birthDate;
        this.acquisitionDate = acquisitionDate;
    }

    public short getIdPig() {
        return idPig;
    }

    public String getSex() {
        return sex;
    }

    public short getWeigth() {
        return weigth;
    }

    public short getIdRace() {
        return idRace;
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

    public short getIdInstalation() {
        return idInstalation;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getAcquisitionDate() {
        return acquisitionDate;
    }
}
