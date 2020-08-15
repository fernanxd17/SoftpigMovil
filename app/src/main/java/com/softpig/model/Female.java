package com.softpig.model;

public class Female extends Pig {

    private short idFemale;
    private String virgin;
    private String gestation;
    private String stateFemale;

    public Female(short idFemale, String virgin, String gestation, String stateFemale, String state, String sex, short weigth, String race, String growthPhase,
                  String pigState, String health, String installation, String birthDate, String acquisitionDate) {

        super(idFemale,state, sex,weigth,race,growthPhase,pigState,health,installation,birthDate,acquisitionDate);
        this.idFemale = idFemale;
        this.virgin = virgin;
        this.gestation = gestation;
        this.stateFemale = stateFemale;
    }


    public short getIdFemale() {
        return idFemale;
    }

    public String getVirgin() {
        return virgin;
    }

    public String getGestation() {
        return gestation;
    }

    public String getStateFemale(){return  stateFemale;}

}
