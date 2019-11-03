package com.Softpig.Models;

public class Medicine {

    private short idMedicine;
    private String typeMedicine;
    private String name;
    private short quantity;

    public Medicine(short idMedicine, String typeMedicine, String name, short quantity) {
        this.idMedicine = idMedicine;
        this.typeMedicine = typeMedicine;
        this.name = name;
        this.quantity = quantity;
    }

    public short getIdMedicine() {
        return idMedicine;
    }

    public String getTypeMedicine() {
        return typeMedicine;
    }

    public String getName() {
        return name;
    }

    public short getQuantity() {
        return quantity;
    }
}

