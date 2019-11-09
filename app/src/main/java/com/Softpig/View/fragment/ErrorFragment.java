package com.Softpig.View.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ErrorFragment extends Fragment {

    private View viewRace;

    public ErrorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewRace =  inflater.inflate(R.layout.fragment_error, container, false);
        ((MainMenuActivity) getActivity()).setTitleTolbar("Razas");
        return viewRace;
    }

}
