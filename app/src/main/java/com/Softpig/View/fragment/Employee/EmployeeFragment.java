package com.Softpig.View.fragment.Employee;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softpig.Presenter.Adapters.EmployeeAdapter;
import com.Softpig.Presenter.EmployeePresenter;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;
import com.Softpig.View.ProfileActivity;

public class EmployeeFragment extends Fragment {

    private RecyclerView recyclerEmployee;
    private EmployeePresenter employeePresenter;
    private EmployeeAdapter employeeAdapter;


    public EmployeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_employees, container, false);
        employeePresenter = new EmployeePresenter();
        recyclerEmployee = view.findViewById(R.id.recyclerEmployee);
        recyclerEmployee.setLayoutManager(new LinearLayoutManager(getContext()));
        employeeAdapter = new EmployeeAdapter(employeePresenter.getEmployees(), getContext());
        recyclerEmployee.setAdapter(employeeAdapter);


        return view;
    }

    public void inflarPerfilActivity(){

    }


}
