package com.Softpig.Model;

import java.sql.Date;

public class Birth {

    private short idBirth;
    private short idFemale;
    private short idMale;
    private String dataBirth;
    private short noBabies;
    private short noMummy;
    private short noDead;

    public Birth(short idBirth, short idFemale, short idMale, String dataBirth, short noBabies, short noMummy, short noDead) {
        this.idBirth = idBirth;
        this.idFemale = idFemale;
        this.idMale = idMale;
        this.dataBirth = dataBirth;
        this.noBabies = noBabies;
        this.noMummy = noMummy;
        this.noDead = noDead;
    }

    public short getIdBirth() {
        return idBirth;
    }

    public short getIdFemale() {
        return idFemale;
    }

    public short getIdMale() {
        return idMale;
    }

    public String getDataBirth() {
        return dataBirth;
    }

    public short getNoBabies() {
        return noBabies;
    }

    public short getNoMummy() {
        return noMummy;
    }

    public short getNoDead() {
        return noDead;
    }
}
