package com.Softpig.Presenter;

import com.Softpig.Model.Female;

import java.util.ArrayList;
import java.util.Date;

public class FemalePresenter {

    public static ArrayList<Female> getFemale(){

        ArrayList<Female> listFemale = new ArrayList<>();
        Date fecha = new Date();
        listFemale.add(new Female((short)0,false, false, "Hembra", (short) 90, "Duroc", "Primal",
                "Crecimiento", "Sano", (short) 2, fecha, fecha));
        listFemale.add(new Female((short)0,false, false, "Hembra", (short) 90, "Duroc", "Primal",
                "Crecimiento", "Sano", (short) 2, fecha, fecha));
        listFemale.add(new Female((short)0,false, false, "Hembra", (short) 90, "Duroc", "Primal",
                "Crecimiento", "Sano", (short) 2, fecha, fecha));

        return listFemale;
    }
}
