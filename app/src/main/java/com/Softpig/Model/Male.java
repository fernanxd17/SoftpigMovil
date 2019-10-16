package com.Softpig.Model;

import java.util.Date;

public class Male extends Pig{
    private short idMale;
    private String conformacionFisica;

    public Male(short idMale, String conformacionFisica, String sex, short weigth, short idRace, String growthPhase,
                String pigState, String health, short idInstalation, Date birthDate, Date acquisitionDate) {
        super(idMale,sex,weigth,idRace,growthPhase,pigState,health,idInstalation,birthDate,acquisitionDate);
        this.idMale = idMale;
        this.conformacionFisica = conformacionFisica;
    }

    public short getIdMale() {
        return idMale;
    }

    public void setIdMale(short idMale) {
        this.idMale = idMale;
    }

    public String getConformacionFisica() {
        return conformacionFisica;
    }


    public void setConformacionFisica(String conformacionFisica) {
        this.conformacionFisica = conformacionFisica;
    }
}
