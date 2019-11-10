package com.Softpig.Model;

public class Tool {

    private short idArticle;
    private String typeArticle;
    private String name;
    private short quantity;
    private short available;
    private short loan;

    public Tool(short idArticle, String name) {
        this.idArticle = idArticle;
        this.name = name;
    }

    public Tool(short idArticle, String typeArticle, String name, short quantity, short available, short loan) {
        this.idArticle = idArticle;
        this.typeArticle = typeArticle;
        this.name = name;
        this.quantity = quantity;
        this.available = available;
        this.loan = loan;
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

    public short getAvailable() {
        return available;
    }

    public short getLoan() {
        return loan;
    }
}
