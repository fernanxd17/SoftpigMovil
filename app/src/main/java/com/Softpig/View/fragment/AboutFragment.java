package com.Softpig.View.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Softpig.R;

public class AboutFragment extends Fragment {
    
    public AboutFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //((MainMenuActivity)getActivity()).setTitleTolbar("Acerca De");
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        return view;
    }
}