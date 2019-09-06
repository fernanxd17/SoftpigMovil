package com.Softpig.Models;

/**
 * Se necesita por que cualquier usuario puede listar las
 * razas que maneja la granja
 */

public class Race {
    private short idRace;
    private String race;
    private String description;

    public Race(short idRace, String race, String description) {
        this.idRace = idRace;
        this.race = race;
        this.description = description;
    }

    public short getIdRace() {
        return idRace;
    }

    public String getRace() {
        return race;
    }

    public String getDescription() {
        return description;
    }
}
