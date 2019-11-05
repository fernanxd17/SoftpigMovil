package com.Softpig.Presenter;

import com.Softpig.Model.Tool;

import java.util.ArrayList;

public class ArticlePresenter {

    public static ArrayList<Tool> getArticles(){

        ArrayList<Tool> listTool = new ArrayList<>();
        listTool.add(new Tool((short) 0, "Cuidado Porcino", "Cepillos", (short) 2));
        listTool.add(new Tool((short) 1, "Inseminisación", "Inyección", (short) 10));
        listTool.add(new Tool((short) 2, "Medicinas", "Vitaminas", (short) 100));

        return listTool;
    }
}
