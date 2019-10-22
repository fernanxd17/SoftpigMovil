package com.Softpig.Presenter;

import com.Softpig.Model.Pig;

import java.util.ArrayList;
import java.util.Date;

public class PigPresenter {

    public static ArrayList<Pig> getPigs() {
        ArrayList<Pig> listPig = new ArrayList<>();
        Date fecha = new Date();
        listPig.add(new Pig((short) 01, "Hembra", (short) 100, "Duroc", "Lechón",
                "Destete", "Sano", (short) 2, fecha, fecha));
        listPig.add(new Pig((short) 01, "Hembra", (short) 100, "Duroc", "Lechón",
                "Destete", "Sano", (short) 2, fecha, fecha));
        listPig.add(new Pig((short) 01, "Hembra", (short) 100, "Duroc", "Lechón",
                "Destete", "Sano", (short) 2, fecha, fecha));
        listPig.add(new Pig((short) 01, "Hembra", (short) 100, "Duroc", "Lechón",
                "Destete", "Sano", (short) 2, fecha, fecha));
        return listPig;
    }
}
