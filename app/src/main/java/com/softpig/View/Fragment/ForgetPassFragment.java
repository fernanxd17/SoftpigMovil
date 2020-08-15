package com.softpig.View.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.softpig.IndexActivity;
import com.softpig.R;

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