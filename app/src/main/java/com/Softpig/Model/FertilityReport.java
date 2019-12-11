package com.Softpig.Model;

public class FertilityReport {

    private short prombabies;
    private short promMommy;
    private short promDead;
    private short promWeigth;
    private short promBirthsFemale;
    private short promBirthsMale;

    public FertilityReport(short prombabies, short promMommy, short promDead, short promWeigth, short promBirthsFemale, short promBirthsMale) {
        this.prombabies = prombabies;
        this.promMommy = promMommy;
        this.promDead = promDead;
        this.promWeigth = promWeigth;
        this.promBirthsFemale = promBirthsFemale;
        this.promBirthsMale = promBirthsMale;
    }

    public short getPrombabies() {
        return prombabies;
    }

    public void setPrombabies(short prombabies) {
        this.prombabies = prombabies;
    }

    public short getPromMommy() {
        return promMommy;
    }

    public void setPromMommy(short promMommy) {
        this.promMommy = promMommy;
    }

    public short getPromDead() {
        return promDead;
    }

    public void setPromDead(short promDead) {
        this.promDead = promDead;
    }

    public short getPromWeigth() {
        return promWeigth;
    }

    public void setPromWeigth(short promWeigth) {
        this.promWeigth = promWeigth;
    }

    public short getPromBirthsFemale() {
        return promBirthsFemale;
    }

    public void setPromBirthsFemale(short promBirthsFemale) {
        this.promBirthsFemale = promBirthsFemale;
    }

    public short getPromBirthsMale() {
        return promBirthsMale;
    }

    public void setPromBirthsMale(short promBirthsMale) {
        this.promBirthsMale = promBirthsMale;
    }
}
