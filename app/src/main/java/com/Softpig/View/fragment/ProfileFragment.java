package com.Softpig.View.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.Softpig.R;
import com.Softpig.View.ProfileActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private TextView tvAssignedItems;
    private Button btInhabilitar, btDespedir;
    private View viewProfile;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewProfile =  inflater.inflate(R.layout.fragment_profile, container, false);
        this.tvAssignedItems = viewProfile.findViewById(R.id.tv_assigned_items);
        this.tvAssignedItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ProfileActivity)getContext()).inflarToolsEmployeeFragment();
            }
        });
        return viewProfile;
    }



}
