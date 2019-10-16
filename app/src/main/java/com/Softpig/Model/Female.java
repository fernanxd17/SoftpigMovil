package com.Softpig.Model;

import java.util.Date;

public class Female extends Pig {

    private short idFemale;
    private boolean virgin;
    private boolean gestation;

    public Female(short idFemale, boolean virgin, boolean gestation, String sex, short weigth, short idRace, String growthPhase,
                  String pigState, String health, short idInstalation, Date birthDate, Date acquisitionDate) {
        super(idFemale,sex,weigth,idRace,growthPhase,pigState,health,idInstalation,birthDate,acquisitionDate);
        this.idFemale = idFemale;
        this.virgin = virgin;
        this.gestation = gestation;
    }

    public short getIdFemale() {
        return idFemale;
    }

    public boolean isVirgin() {
        return virgin;
    }

    public boolean isGestation() {
        return gestation;
    }


}
