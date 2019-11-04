package com.Softpig.Presenter;

import com.Softpig.Model.Employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeePresenter {

    public static List<Employee> getEmployees(){

        List<Employee> listEmployee = new ArrayList<>();
        Date fecha = new Date();
        listEmployee.add(new Employee((short) 01, "Administrador", "Activo",fecha , "1090512864", "Masculino", "Eduardo", "Jose", "Pajaro", "Caballero", "eduardojosepc@ufps.edu.co", "55555","3504018064"));
        listEmployee.add(new Employee((short) 01, "Administrador", "Activo",fecha , "1090512864", "Masculino", "Fernando", "Jose", "Pajaro", "Caballero", "eduardojosepc@ufps.edu.co", "55555","3504018064"));
        listEmployee.add(new Employee((short) 01, "Administrador", "Activo",fecha , "1090512864", "Masculino", "Eduardo", "Jose", "Pajaro", "Caballero", "eduardojosepc@ufps.edu.co", "55555","3504018064"));

        return listEmployee;
    }
}
