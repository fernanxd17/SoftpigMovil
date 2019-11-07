package com.Softpig.View.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softpig.Model.Employee;
import com.Softpig.Presenter.Adapters.EmployeeAdapter;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;

import java.util.ArrayList;

public class EmployeeFragment extends Fragment {

    private RecyclerView recyclerEmployee;
    public EmployeeAdapter employeeAdapter;
    private ArrayList<Employee> listEmployees;
    private static View viewEmployee;
    private TextView tv_noEmployees;


    public EmployeeFragment(ArrayList<Employee> listEmployees) {
        this.listEmployees = listEmployees;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewEmployee =  inflater.inflate(R.layout.fragment_list_employees, container, false);
        ((MainMenuActivity)getActivity()).setTitleTolbar("Empleados");
        if (listEmployees.isEmpty()){
            tv_noEmployees = viewEmployee.findViewById(R.id.tv_noEmployees);
            tv_noEmployees.setText("NO tienes empleados registrados en tu granja");
            return viewEmployee;
        }

        employeeAdapter = new EmployeeAdapter(listEmployees, getContext());
        recyclerEmployee = viewEmployee.findViewById(R.id.recyclerEmployee);
        recyclerEmployee.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerEmployee.setAdapter(employeeAdapter);
        ((MainMenuActivity)getActivity()).modificar("employee");


        return viewEmployee;
    }






}
