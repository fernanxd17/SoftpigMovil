package com.softpig.View.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.softpig.model.Employee;
import com.softpig.presenter.adapters.EmployeeAdapter;
import com.softpig.R;
import com.softpig.View.MainMenuActivity;
import java.util.ArrayList;

public class EmployeeFragment extends Fragment {

    private RecyclerView recyclerEmployee;
    public  EmployeeAdapter employeeAdapter;
    private ArrayList<Employee> listEmployees;
    private  View viewEmployee;
    private  TextView tv_noEmployees;
    private SwipeRefreshLayout refreshListEmployee;

    public EmployeeFragment() {
        this.listEmployees = new ArrayList<>();
    }

    public void setListEmployees(ArrayList<Employee> listEmployees){
        this.listEmployees = listEmployees;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewEmployee =  inflater.inflate(R.layout.fragment_list_employees, container, false);
        refreshListEmployee = viewEmployee.findViewById(R.id.refresh_list_employee);
        refreshListEmployee.setOnRefreshListener(() -> ((MainMenuActivity)getContext()).actualizarListEmployee(refreshListEmployee));
        ((MainMenuActivity)getActivity()).setSearch("Employee");
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


        return viewEmployee;
    }

    public void notificarAdapter() {
        if (listEmployees.isEmpty()){
            tv_noEmployees = viewEmployee.findViewById(R.id.tv_noEmployees);
            tv_noEmployees.setText("NO tienes empleados registrados en tu granja");
        }

        employeeAdapter = new EmployeeAdapter(listEmployees, getContext());
        recyclerEmployee = viewEmployee.findViewById(R.id.recyclerEmployee);
        recyclerEmployee.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerEmployee.setAdapter(employeeAdapter);
    }
}