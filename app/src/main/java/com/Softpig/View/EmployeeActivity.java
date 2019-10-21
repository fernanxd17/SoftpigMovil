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

    private RecyclerView recyclerEmployee;
    private EmployeePresenter employeePresenter;
    private EmployeeAdapter employeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employees);
        employeePresenter = new EmployeePresenter();
        recyclerEmployee = findViewById(R.id.recyclerEmployee);
        recyclerEmployee.setLayoutManager(new LinearLayoutManager(this));
        employeeAdapter = new EmployeeAdapter(employeePresenter.getEmployees());
        recyclerEmployee.setAdapter(employeeAdapter);
    }
}
