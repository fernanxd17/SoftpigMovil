package com.Softpig.Presenter;

import com.Softpig.Model.Article;

import java.util.ArrayList;

public class ArticlePresenter {

    public static ArrayList<Article> getArticles(){

        ArrayList<Article> listArticle = new ArrayList<>();
        listArticle.add(new Article((short) 0, "Cuidado Porcino", "Cepillos", (short) 2));
        listArticle.add(new Article((short) 1, "Inseminisación", "Inyección", (short) 10));
        listArticle.add(new Article((short) 2, "Medicinas", "Vitaminas", (short) 100));

        return listArticle;
    }
}
