package com.Softpig.Model;

public class Tool {

    private String typeArticle;
    private String name;
    private short quantity, loan, idArticle;

    public Tool(short idArticle, String name, String typeArticle, short loan) {
        this.idArticle = idArticle;
        this.typeArticle = typeArticle;
        this.name = name;
        this.loan  = loan;
    }

    public Tool(short idArticle, String typeArticle, String name, short quantity, short loan) {
        this.idArticle = idArticle;
        this.typeArticle = typeArticle;
        this.name = name;
        this.quantity = quantity;
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

    public short getLoan(){
        return loan;
    }
}
