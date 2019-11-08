package com.Softpig.View.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.Softpig.IndexActivity;
import com.Softpig.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private static EditText etCodeUser, etPassword;
    private static TextView etForgetPass;
    private static Button btLogin;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_login, container, false);
        etCodeUser = view.findViewById(R.id.etCodeUser);
        etPassword = view.findViewById(R.id.etPassword);
        etForgetPass = view.findViewById(R.id.etForgetPassword);
        btLogin = view.findViewById(R.id.btLoginUp);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((IndexActivity)getActivity()).login(etCodeUser.getText().toString(), etPassword.getText().toString());
            }
        });

        //Averigurar como se usa el metodo onClick desde un layout a un fragment
        etForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((IndexActivity)getActivity()).openForgetFragment();
            }
        });

        return view;
    }

}
