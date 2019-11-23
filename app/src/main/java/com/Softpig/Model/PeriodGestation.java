package com.Softpig.Model;

import java.util.Date;

public class PeriodGestation {

    private short idPeriodGestation;
    private short idFemale;
    private short idMale;
    private String dateStart;

    public PeriodGestation(short idPeriodGestation, short idFemale, short idMale, String dateStart) {
        this.idPeriodGestation = idPeriodGestation;
        this.idFemale = idFemale;
        this.idMale = idMale;
        this.dateStart = dateStart;
    }

    public short getIdPeriodGestation() {
        return idPeriodGestation;
    }

    public short getIdFemale() {
        return idFemale;
    }

    public short getIdMale() {
        return idMale;
    }

    public String getDateStart() {
        return dateStart;
    }
}
