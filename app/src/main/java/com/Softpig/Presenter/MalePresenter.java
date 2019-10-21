package com.Softpig.Presenter;

import com.Softpig.Model.Male;

import java.util.ArrayList;
import java.util.Date;

public class MalePresenter {

    public static ArrayList<Male> getMales(){

        ArrayList<Male> listMale = new ArrayList<>();
        Date fecha = new Date();

        listMale.add(new Male((short) 0,"Pequeña descrición de la conformación fisica del porcino", "Macho", (short) 90, "Duroc","Lechon", "Destete", "Sano", (short) 2, fecha, fecha));
        listMale.add(new Male((short) 0,"Pequeña descrición de la conformación fisica del porcino", "Macho", (short) 90, "Duroc","Lechon", "Destete", "Sano", (short) 2, fecha, fecha));
        listMale.add(new Male((short) 0,"Pequeña descrición de la conformación fisica del porcino", "Macho", (short) 90, "Duroc","Lechon", "Destete", "Sano", (short) 2, fecha, fecha));

        return listMale;
    }

}
