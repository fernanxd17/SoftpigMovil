package com.Softpig.Model;

import java.util.Date;

public class Female extends Pig {

    private short idFemale;
    private String virgin;
    private String gestation;

    public Female(short idFemale, String virgin, String gestation, String sex, short weigth, String race, String growthPhase,
                  String pigState, String health, String installation, Date birthDate, Date acquisitionDate) {
        super(idFemale,sex,weigth,race,growthPhase,pigState,health,installation,birthDate,acquisitionDate);
        this.idFemale = idFemale;
        this.virgin = virgin;
        this.gestation = gestation;
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


}
