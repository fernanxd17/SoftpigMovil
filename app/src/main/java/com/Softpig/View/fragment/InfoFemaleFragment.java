package com.Softpig.View.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.Softpig.Model.Female;
import com.Softpig.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFemaleFragment extends Fragment {


    private Female female;
    private View viewInfoFemale;
    private TextView tv_valor_id_female, tv_valor_state_female, tv_valor_installation_female, tv_valor_nulipara_female, tv_valor_gestation_female, tv_valor_peso_female, tv_valor_raza_female, tv_valor_salud_female;
    private Button bt_desasignar_female;

    public InfoFemaleFragment(Female female) {
        this.female = female;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewInfoFemale =  inflater.inflate(R.layout.fragment_female, container, false);
        capturarCampos();
        modificarCampos();
        bt_desasignar_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((PigActivity) getContext()).desasignarFemale(female.getIdFemale());
            }
        });

        return viewInfoFemale;
    }

    private void capturarCampos() {
       tv_valor_id_female = viewInfoFemale.findViewById(R.id.tv_valor_id_female);
       tv_valor_state_female = viewInfoFemale.findViewById(R.id.tv_valor_state_female);
       tv_valor_installation_female = viewInfoFemale.findViewById(R.id.tv_valor_installation_female);
       tv_valor_nulipara_female = viewInfoFemale.findViewById(R.id.tv_valor_nulipara_female);
       tv_valor_gestation_female = viewInfoFemale.findViewById(R.id.tv_valor_gestation_female);
       tv_valor_peso_female = viewInfoFemale.findViewById(R.id.tv_valor_peso_female);
       tv_valor_raza_female = viewInfoFemale.findViewById(R.id.tv_valor_raza_female);
       tv_valor_salud_female = viewInfoFemale.findViewById(R.id.tv_valor_salud_female);
       bt_desasignar_female = viewInfoFemale.findViewById(R.id.bt_desasignar_female);
    }

    private void modificarCampos() {
        tv_valor_id_female.setText(String.valueOf(female.getIdFemale()));
        tv_valor_state_female.setText(female.getStateFemale());
        tv_valor_installation_female.setText(female.getInstallation());
        tv_valor_nulipara_female.setText(female.getVirgin());
        tv_valor_gestation_female.setText(female.getGestation());
        tv_valor_peso_female.setText(String.valueOf(female.getWeigth())+ " kg");
        tv_valor_raza_female.setText(female.getRace());
        tv_valor_salud_female.setText(female.getHealth());
    }

}
