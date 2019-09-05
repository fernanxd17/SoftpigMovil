package com.Softpig.Models;

public class Article {

    private short idArticle;
    private short idType;
    private String name;
    private short quantity;

    public Article(short idArticle, short idType, String name, short quantity) {
        this.idArticle = idArticle;
        this.idType = idType;
        this.name = name;
        this.quantity = quantity;
    }

    public short getIdArticle() {
        return idArticle;
    }

    public short getIdType() {
        return idType;
    }

    public String getName() {
        return name;
    }

    public short getQuantity() {
        return quantity;
    }
}
