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

    public Birth (short idMale, String dataBirth, short noBabies, short noMummy, short noDead){
        this.dataBirth = dataBirth;
        this.noBabies = noBabies;
        this.noMummy = noMummy;
        this.noDead = noDead;
        this.idMale = idMale;
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

    public void setIdBirth(short idBirth) {
        this.idBirth = idBirth;
    }

    public void setIdFemale(short idFemale) {
        this.idFemale = idFemale;
    }

    public void setIdMale(short idMale) {
        this.idMale = idMale;
    }

    public void setDataBirth(String dataBirth) {
        this.dataBirth = dataBirth;
    }

    public void setNoBabies(short noBabies) {
        this.noBabies = noBabies;
    }

    public void setNoMummy(short noMummy) {
        this.noMummy = noMummy;
    }

    public void setNoDead(short noDead) {
        this.noDead = noDead;
    }
}
