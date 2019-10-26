package com.Softpig.View.fragment.DashBoard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;

public class DashBoardFragment extends Fragment {

    private TextView tvNumAdmins, tvNumOperators, tvLookEmployees;
    private TextView tvNumInstallations, tvNumTypeInstallations, tvLookInstallations;
    private TextView tvNumTools, tvNumTypeTools, tvLookTools;

    public DashBoardFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_dash_board, container, false);
        this.tvNumAdmins = view.findViewById(R.id.tv_num_admins);
        this.tvNumOperators = view.findViewById(R.id.tv_num_operators);
        this.tvLookEmployees = view.findViewById(R.id.tv_look_employees);
        this.tvNumInstallations = view.findViewById(R.id.tv_num_installations);
        this.tvNumTypeInstallations = view.findViewById(R.id.tv_num_type_installations);
        this.tvLookInstallations = view.findViewById(R.id.tv_look_installations);
        this.tvNumTools = view.findViewById(R.id.tv_num_tools);
        this.tvNumTypeTools = view.findViewById(R.id.tv_num_type_tools);
        this.tvLookTools = view.findViewById(R.id.tv_look_tools);


        this.tvLookEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainMenuActivity)getActivity()).inflateFragment(1);
            }
        });

        this.tvLookInstallations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainMenuActivity)getActivity()).inflateFragment(2);
            }
        });

        this.tvLookTools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainMenuActivity)getActivity()).inflateFragment(3);
            }
        });

        return view;
    }
}
