package com.Softpig.Presenter;

import com.Softpig.Model.Race;

import java.util.ArrayList;

public class RacePresenter {

    public static ArrayList<Race> getRaces(){

        ArrayList<Race> listRace = new ArrayList<>();
        listRace.add(new Race((short) 01, "Duroc", "Desciba aqui todo lo que desee de esta raaza"));
        listRace.add(new Race((short) 01, "Duroc", "Desciba aqui todo lo que desee de esta raaza"));
        listRace.add(new Race((short) 01, "Duroc", "Desciba aqui todo lo que desee de esta raaza"));
        listRace.add(new Race((short) 01, "Duroc", "Desciba aqui todo lo que desee de esta raaza"));
        listRace.add(new Race((short) 01, "Duroc", "Desciba aqui todo lo que desee de esta raaza"));
        listRace.add(new Race((short) 01, "Duroc", "Desciba aqui todo lo que desee de esta raaza"));
        return listRace;
    }
}
