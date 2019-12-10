package com.Softpig.Model;

public class GeneralReport {

    private String [] encabezados = {"Porcinos", "Hembras", "Machos", "Reproductoras", "Reproductores",
            "No Celos", "No Gestaciones", "No Partos", "Lechones", "Marrano", "Primal", "Gordo"};

    private int nPorcinos;

    public GeneralReport(int nPorcinos){
        this.nPorcinos = nPorcinos;
    }

    public String[] getEncabezados() {
        return encabezados;
    }

    public void setEncabezados(String[] encabezados) {
        this.encabezados = encabezados;
    }

    public int getnPorcinos() {
        return nPorcinos;
    }

    public void setnPorcinos(int nPorcinos) {
        this.nPorcinos = nPorcinos;
    }
}
