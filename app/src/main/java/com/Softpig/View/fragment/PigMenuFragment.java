package com.Softpig.View.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;

import org.w3c.dom.Text;


public class PigMenuFragment extends Fragment {
    private  TextView numPig, numLechones, numMarranos, numGordos, numPrimales, numVirgenes;
    private  TextView numFemales, numMales, numGestaciones;
    private  RelativeLayout rlPig, rlFemale, rlMale;
    private  View viewPigMenu;

    public PigMenuFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewPigMenu =  inflater.inflate(R.layout.fragment_pigs_bottom, container, false);

        numPig = viewPigMenu.findViewById(R.id.tv_num_pig);
        numLechones = viewPigMenu.findViewById(R.id.tv_num_lechones);
        numMarranos = viewPigMenu.findViewById(R.id.tv_num_marranos);
        numGordos = viewPigMenu.findViewById(R.id.tv_num_gordos);
        numPrimales = viewPigMenu.findViewById(R.id.tv_num_primal);
        numVirgenes = viewPigMenu.findViewById(R.id.tv_num_virgen);
        numFemales = viewPigMenu.findViewById(R.id.tv_num_female);
        numMales = viewPigMenu.findViewById(R.id.tv_num_male);
        numGestaciones = viewPigMenu.findViewById(R.id.tv_num_gestation);

        rlPig = viewPigMenu.findViewById(R.id.rl_pig);
        rlFemale = viewPigMenu.findViewById(R.id.rl_female);
        rlMale = viewPigMenu.findViewById(R.id.rl_male);

        rlPig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainMenuActivity)getActivity()).controllerFragment("porcinos");
            }
        });

        rlFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        rlMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return viewPigMenu;
    }

}
