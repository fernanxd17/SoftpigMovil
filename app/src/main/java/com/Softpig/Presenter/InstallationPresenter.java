package com.Softpig.Presenter;

import com.Softpig.Model.Installation;

import java.util.ArrayList;

public class InstallationPresenter {

    /**
     * Realiza la peticion de traer todas las instalaciones
     * Return: ArrayList con las instalaciones
     */
    public static ArrayList<Installation> getInstallations() {
        ArrayList<Installation> listInstallations = new ArrayList<>();
        listInstallations.add(new Installation((short) 0, "Oficina", "Oficina administrativa", (short) 500));
        listInstallations.add(new Installation((short) 1, "Cocheras", "Oficina administrativa", (short) 600));
        listInstallations.add(new Installation((short) 2, "Bodega", "Oficina administrativa", (short) 700));
        listInstallations.add(new Installation((short) 3, "Enfermeria", "Oficina administrativa", (short) 1000));
        return listInstallations;
    }
}
