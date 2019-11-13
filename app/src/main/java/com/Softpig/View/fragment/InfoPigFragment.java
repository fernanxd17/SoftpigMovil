package com.Softpig.View.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softpig.Model.Pig;
import com.Softpig.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoPigFragment extends Fragment {

    private Pig pig;
    public InfoPigFragment(Pig pig) {
        // Required empty public constructor
        this.pig = pig;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }

}
