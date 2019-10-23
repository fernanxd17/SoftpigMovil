package com.Softpig.View.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softpig.R;

public class DashBoardFragment extends Fragment {

    private TextView tvNumAdmins, tvNumOperators, tvLookEmployees;


    public DashBoardFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_dash_board, container, false);
        this.tvNumAdmins = getActivity().findViewById(R.id.tv_num_admins);
        this.tvNumOperators = getActivity().findViewById(R.id.tv_num_operators);
        this.tvLookEmployees = getActivity().findViewById(R.id.tv_look_employees);

        this.tvLookEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return view;
    }


}
