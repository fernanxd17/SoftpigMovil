package com.Softpig.View.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;

public class DashBoardFragment extends Fragment {

    private  TextView tvNumAdmins, tvNumOperators, tvLookEmployees;
    private  TextView tvNumInstallations, tvNumTypeInstallations, tvLookInstallations;
    private  TextView tvNumTools, tvNumTypeTools, tvLookTools;
    private  LinearLayout llEmployeeDashboard, llInstallationsDashboard, llToalsDashboard;

    public DashBoardFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_dash_board, container, false);
        tvNumAdmins = view.findViewById(R.id.tv_num_admins);
        tvNumOperators = view.findViewById(R.id.tv_num_operators);
        tvNumInstallations = view.findViewById(R.id.tv_num_installations);
        tvNumTypeInstallations = view.findViewById(R.id.tv_num_type_installations);
        tvNumTools = view.findViewById(R.id.tv_num_tools);
        tvNumTypeTools = view.findViewById(R.id.tv_num_type_tools);
        llEmployeeDashboard = view.findViewById(R.id.ll_empleyees_dashboard);
        llInstallationsDashboard = view.findViewById(R.id.ll_installations_dashboard);
        llToalsDashboard = view.findViewById(R.id.ll_tools_dashboard);



        this.llEmployeeDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainMenuActivity)getActivity()).controllerFragment("Employees");
            }
        });

        this.llInstallationsDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainMenuActivity)getActivity()).controllerFragment("Installations");
            }
        });

        this.llToalsDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainMenuActivity)getActivity()).controllerFragment("Tools");
            }
        });

        return view;
    }
}
