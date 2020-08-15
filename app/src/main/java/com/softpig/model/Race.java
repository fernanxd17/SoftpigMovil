package com.softpig.model;

/**
 * Se necesita por que cualquier usuario puede listar las
 * razas que maneja la granja
 */

public class Race {
    private short idRace;
    private String nameRace;
    private String description;

    public Race(short idRace, String nameRace, String description) {
        this.idRace = idRace;
        this.nameRace = nameRace;
        this.description = description;
    }

    public short getIdRace() {
        return idRace;
    }

    public String getNameRace() {
        return nameRace;
    }

    public String getDescription() {
        return description;
    }
}
