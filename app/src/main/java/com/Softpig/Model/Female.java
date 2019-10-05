package com.Softpig.Model;

public class Female {

    private short idFemale;
    private boolean virgin;
    private boolean gestation;

    public Female(short idFemale, boolean virgin, boolean gestation) {
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
