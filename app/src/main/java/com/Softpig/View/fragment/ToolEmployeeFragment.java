package com.Softpig.View.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softpig.R;
import com.Softpig.View.ProfileActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToolEmployeeFragment extends Fragment implements AddToolEmployeeFragment.AddToolEmployeeListerner{

    private FloatingActionButton addToolEmployee;

    public ToolEmployeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }

    public void openDialog() {
        AddToolEmployeeFragment addToolEmployeeFragment = new AddToolEmployeeFragment();
        addToolEmployeeFragment.show(getFragmentManager(), "example dialog");
    }

    @Override
    public void applyTexts(String username, String password) {

    }
}
