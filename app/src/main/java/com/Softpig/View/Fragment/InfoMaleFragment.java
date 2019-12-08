package com.Softpig.View.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.Softpig.Model.Male;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;
import com.Softpig.View.PigActivity;

public class InfoMaleFragment extends Fragment {

    private TextView idMale, stateMale, installationMale, razaMale, saludMale, pesoMale, fisicaMale;
    private TextView tvVerExamanes;
    private Button btDesasignarMale;
    private View viewInfoMale;
    private Male male;
    private String [] listIdMale;


    public InfoMaleFragment(Male male) {
        this.male = male;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewInfoMale =  inflater.inflate(R.layout.fragment_male, container, false);

        capturarCampos();
        modificarCampos();
        btDesasignarMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((PigActivity) getContext()).desasignarMale(male.getIdMale());
            }
        });

        tvVerExamanes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((PigActivity)getContext()).verExamanesMale(male.getIdMale());
            }
        });

        return viewInfoMale;
    }

    private void capturarCampos() {
        idMale = viewInfoMale.findViewById(R.id.tv_valor_id_male);
        stateMale = viewInfoMale.findViewById(R.id.tv_valor_state_male);
        installationMale = viewInfoMale.findViewById(R.id.tv_valor_installation_male);
        razaMale = viewInfoMale.findViewById(R.id.tv_valor_raza_male);
        saludMale = viewInfoMale.findViewById(R.id.tv_valor_salud_male);
        pesoMale = viewInfoMale.findViewById(R.id.tv_valor_peso_male);
        fisicaMale = viewInfoMale.findViewById(R.id.tv_valor_fisica_male);
        btDesasignarMale = viewInfoMale.findViewById(R.id.bt_desasignar_male);
        tvVerExamanes = viewInfoMale.findViewById(R.id.tv_ver_examanes);
    }


    private void modificarCampos(){
        idMale.setText(String.valueOf(male.getIdMale()));
        stateMale.setText(male.getStateMale());
        installationMale.setText(male.getInstallation());
        razaMale.setText(male.getRace());
        saludMale.setText(male.getHealth());
        pesoMale.setText(String.valueOf(male.getWeigth()) + "Kg");
        fisicaMale.setText(male.getConformacionFisica());
        if(MainMenuActivity.rol.equalsIgnoreCase("Empleado Operativo")){
            btDesasignarMale.setVisibility(View.INVISIBLE);
        }
    }

}
