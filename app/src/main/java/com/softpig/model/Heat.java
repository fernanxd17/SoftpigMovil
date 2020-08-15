package com.softpig.model;

public class Heat {

    private short idHeat;
    private short idFemale;
    private String typeMating;
    private boolean sincrony;
    private String dateStart;
    private String dateEnd;

    public Heat(short idHeat, short idFemale, String typeMating, boolean sincrony, String dateStart, String dateEnd) {
        this.idHeat = idHeat;
        this.idFemale = idFemale;
        this.typeMating = typeMating;
        this.sincrony = sincrony;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public Heat(String typeMating, boolean sincrony, String dateStart, String dateEnd){
        this.typeMating = typeMating;
        this.sincrony = sincrony;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public void setIdHeat(short idHeat) {
        this.idHeat = idHeat;
    }

    public void setIdFemale(short idFemale) {
        this.idFemale = idFemale;
    }

    public void setTypeMating(String typeMating) {
        this.typeMating = typeMating;
    }

    public void setSincrony(boolean sincrony) {
        this.sincrony = sincrony;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public short getIdHeat() {
        return idHeat;
    }

    public short getIdFemale() {
        return idFemale;
    }

    public String getTypeMating() {
        return typeMating;
    }

    public boolean isSincrony() {
        return sincrony;
    }

    public String getDateStart() {
        return dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }
}
