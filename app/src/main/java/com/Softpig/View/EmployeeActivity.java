package com.Softpig.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.Softpig.Model.Employee;
import com.Softpig.Model.Installation;
import com.Softpig.Presenter.Adapters.EmployeeAdapter;
import com.Softpig.Presenter.EmployeePresenter;
import com.Softpig.R;

import java.util.ArrayList;
import java.util.Date;

public class EmployeeActivity extends AppCompatActivity {

    ArrayList<Employee> listEmployee;
    RecyclerView recyclerEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employees);
        listEmployee = new ArrayList<Employee>();
        recyclerEmployee = findViewById(R.id.recyclerEmployee);
        recyclerEmployee.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        showEmployees();
        EmployeeAdapter employeeAdapter = new EmployeeAdapter(listEmployee);
        recyclerEmployee.setAdapter(employeeAdapter);
    }

    private void showEmployees() {
        listEmployee = EmployeePresenter.getEmployees();
        EmployeeAdapter employeeAdapter = new EmployeeAdapter(listEmployee);
        this.recyclerEmployee.setAdapter(employeeAdapter);

        /*Date fecha = new Date();
        listEmployee.add(new Employee((short) 01, "Administrador", "Activo",fecha , "1090512864", "Masculino", "Eduardo", "Jose", "Pajaro", "Caballero", "eduardojosepc@ufps.edu.co", "55555","3504018064"));
        listEmployee.add(new Employee((short) 01, "Administrador", "Activo",fecha , "1090512864", "Masculino", "Eduardo", "Jose", "Pajaro", "Caballero", "eduardojosepc@ufps.edu.co", "55555","3504018064"));
        listEmployee.add(new Employee((short) 01, "Administrador", "Activo",fecha , "1090512864", "Masculino", "Eduardo", "Jose", "Pajaro", "Caballero", "eduardojosepc@ufps.edu.co", "55555","3504018064"));*/
    }
}
