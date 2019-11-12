package com.Softpig.Model;

import java.util.Date;

public class Male extends Pig{
    private short idMale;
    private String conformacionFisica;

    public Male(short idMale, String conformacionFisica, String state, String sex, short weigth, String race, String growthPhase,
                String pigState, String health, String installation, Date birthDate, Date acquisitionDate) {
        super(idMale,state, sex,weigth,race,growthPhase,pigState,health,installation,birthDate,acquisitionDate);
        this.idMale = idMale;
        this.conformacionFisica = conformacionFisica;
    }

    public short getIdMale() {
        return idMale;
    }

    public String getConformacionFisica() {
        return conformacionFisica;
    }

}
