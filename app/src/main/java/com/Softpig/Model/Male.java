package com.Softpig.Model;

public class Male extends Pig{
    private short idMale;
    private String conformacionFisica;
    private String stateMale;

    public Male(short idMale, String conformacionFisica, String stateMale, String state, String sex, short weigth, String race, String growthPhase,
                String pigState, String health, String installation, String birthDate, String acquisitionDate) {
        super(idMale,state, sex,weigth,race,growthPhase,pigState,health,installation,birthDate,acquisitionDate);
        this.idMale = idMale;
        this.conformacionFisica = conformacionFisica;
        this.stateMale = stateMale;
    }

    public short getIdMale() {
        return idMale;
    }

    public String getConformacionFisica() {
        return conformacionFisica;
    }

    public String getStateMale(){return stateMale;}

}
