package com.Softpig.View.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Softpig.R;

import org.w3c.dom.Text;


public class PigMenuFragment extends Fragment {
    private TextView numPig, numLechones, numMarranos, numGordos, numPrimales, numVirgenes;
    private TextView numFemales, numMales, numGestaciones;
    private LinearLayout llPig;

    public PigMenuFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_pigs_bottom, container, false);

        numPig = view.findViewById(R.id.tv_num_pig);
        numLechones = view.findViewById(R.id.tv_num_lechones);
        numMarranos = view.findViewById(R.id.tv_num_marranos);
        numGordos = view.findViewById(R.id.tv_num_gordos);
        numPrimales = view.findViewById(R.id.tv_num_primal);
        numVirgenes = view.findViewById(R.id.tv_num_virgen);
        numFemales = view.findViewById(R.id.tv_num_female);
        numMales = view.findViewById(R.id.tv_num_male);
        numGestaciones = view.findViewById(R.id.tv_num_gestation);

        llPig = view.findViewById(R.id.ll_pig);

        llPig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

}
