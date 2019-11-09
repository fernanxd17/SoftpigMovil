package com.Softpig.View.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softpig.IndexActivity;
import com.Softpig.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetPassFragment extends Fragment {

    private  TextView backLogin;
    private  View viewForgetPass;

    public ForgetPassFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewForgetPass = inflater.inflate(R.layout.fragment_forget_password, container, false);

        backLogin = viewForgetPass.findViewById(R.id.backLogin);
        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((IndexActivity)getActivity()).backLogin();
                }
        });
        return viewForgetPass;
    }

}
