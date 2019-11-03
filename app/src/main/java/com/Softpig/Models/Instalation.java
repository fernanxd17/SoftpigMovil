package com.Softpig.Models;

public class Instalation {

    private short idInstalation;
    private String typeInstalation;
    private String name;
    private short capacity;

    public Instalation(short idInstalation, String typeInstalation, String name, short capacity) {
        this.idInstalation = idInstalation;
        this.typeInstalation = typeInstalation;
        this.name = name;
        this.capacity = capacity;
    }

    public short getIdInstalation() {
        return idInstalation;
    }

    public String getTypeInstalation() {
        return typeInstalation;
    }

    public String getName() {
        return name;
    }

    public short getCapacity() {
        return capacity;
    }
}
