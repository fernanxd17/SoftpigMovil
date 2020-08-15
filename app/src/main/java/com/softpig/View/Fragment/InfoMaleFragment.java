package com.softpig.View.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.softpig.model.Male;
import com.softpig.R;
import com.softpig.View.MainMenuActivity;
import com.softpig.View.PigActivity;

public class InfoMaleFragment extends Fragment {

    private TextView idMale, stateMale, installationMale, razaMale, saludMale, pesoMale, fisicaMale;
    private TextView tvVerExamanes;
    private Button btDesasignarMale;
    private View viewInfoMale;
    private Male male;


    public InfoMaleFragment(Male male) {
        this.male = male;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewInfoMale =  inflater.inflate(R.layout.fragment_male, container, false);

        capturarCampos();
        modificarCampos();
        btDesasignarMale.setOnClickListener(view -> ((PigActivity) getContext()).desasignarMale(male.getIdMale()));

        tvVerExamanes.setOnClickListener(view -> ((PigActivity)getContext()).verExamanesMale(male.getIdMale()));

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
        pesoMale.setText(male.getWeigth() + "Kg");
        fisicaMale.setText(male.getConformacionFisica());
        if(MainMenuActivity.rol.equalsIgnoreCase("Empleado Operativo")){
            btDesasignarMale.setVisibility(View.INVISIBLE);
        }
    }

    public void volverActivy() {
        ((PigActivity)getContext()).finish();
    }
}
