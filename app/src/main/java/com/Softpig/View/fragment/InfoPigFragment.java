package com.Softpig.View.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.Softpig.Model.Pig;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoPigFragment extends Fragment {

    private View viewInfoPig;
    private Pig pig;
    private TextView tvID, tvStat, tvSalud, tvSex, tvPeso, tvRaza, tvCreci, tvEtapa, tvBirth;
    private TextView tvBuy, tvFeMale, tvInstallation;
    private Button btBaja;
    public InfoPigFragment(Pig pig) {
        // Required empty public constructor
        this.pig = pig;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewInfoPig = inflater.inflate(R.layout.fragment_pig, container, false);
        capturarCampos();
        modificarCampos();
        btBaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((PigActivity)getContext()).darBajaPig(pig.getIdPig());
            }
        });

        return viewInfoPig;
    }

    private void modificarCampos() {
        tvID.setText(String.valueOf(pig.getIdPig()));
        tvStat.setText(pig.getState());
        tvSalud.setText(pig.getHealth());
        tvSex.setText(pig.getSex());
        tvPeso.setText(String.valueOf(pig.getWeigth()));
        tvRaza.setText(pig.getRace());
        tvCreci.setText(pig.getGrowthPhase());
        tvEtapa.setText(pig.getPigState());
        tvBuy.setText("-");
        if(!pig.getAcquisitionDate().equalsIgnoreCase("09/11/2000")){
            tvBuy.setText(pig.getAcquisitionDate());
        }
        tvBirth.setText("-");
        if(!pig.getBirthDate().equalsIgnoreCase("09/11/2000")){
            tvBirth.setText(pig.getBirthDate());
        }

        tvInstallation.setText(pig.getInstallation());
    }

    private void capturarCampos() {
        tvID = viewInfoPig.findViewById(R.id.tv_valor_id_pig);
        tvStat = viewInfoPig.findViewById(R.id.tv_valor_state_pig);
        tvSalud = viewInfoPig.findViewById(R.id.tv_valor_salud_pig);
        tvSex = viewInfoPig.findViewById(R.id.tv_valor_sex_pig);
        tvPeso = viewInfoPig.findViewById(R.id.tv_valor_peso_pig);
        tvRaza = viewInfoPig.findViewById(R.id.tv_valor_raza_pig);
        tvCreci = viewInfoPig.findViewById(R.id.tv_valor_creci_pig);
        tvEtapa = viewInfoPig.findViewById(R.id.tv_valor_etapa_pig);
        tvBirth = viewInfoPig.findViewById(R.id.tv_valor_birth_pig);
        tvBuy = viewInfoPig.findViewById(R.id.tv_valor_buy_pig);
        tvFeMale = viewInfoPig.findViewById(R.id.tv_is_fe_male);
        tvInstallation = viewInfoPig.findViewById(R.id.tv_valor_pig_installation);
        btBaja = viewInfoPig.findViewById(R.id.bt_baja_pig);
        if(MainMenuActivity.rol.equalsIgnoreCase("Empleado Operativo")){
            btBaja.setVisibility(View.INVISIBLE);
        }
    }

}
