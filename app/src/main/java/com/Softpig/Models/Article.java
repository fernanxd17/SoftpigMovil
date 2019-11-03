package com.Softpig.Models;

public class Article {

    private short idArticle;
    private String typeArticle;
    private String name;
    private short quantity;

    public Article(short idArticle, String typeArticle, String name, short quantity) {
        this.idArticle = idArticle;
        this.typeArticle = typeArticle;
        this.name = name;
        this.quantity = quantity;
    }

    public short getIdArticle() {
        return idArticle;
    }

    public String getTypeArticle() {
        return typeArticle;
    }

    public String getName() {
        return name;
    }

    public short getQuantity() {
        return quantity;
    }
}
