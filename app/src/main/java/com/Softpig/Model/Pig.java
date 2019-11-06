package com.Softpig.Model;

import java.util.Date;

public class Pig {

    private short idPig;
    private String sex;
    private short weigth;
    private String race;
    private String growthPhase;
    private String pigState;
    private String health;
    private String installation;
    private Date birthDate;
    private Date acquisitionDate;

    public Pig(short idPig, String sex, short weigth, String race, String growthPhase,
               String pigState, String health,String installation, Date birthDate, Date acquisitionDate) {
        this.idPig = idPig;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

}
