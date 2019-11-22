package com.Softpig.Model;

public class Medicine {

    private short idMedicine;
    private String typeMedicine;
    private String name;
    private short quantity;

    public Medicine(short idMedicine, String name, String type, short quantity) {
        this.idMedicine = idMedicine;
        this.typeMedicine = type;
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

