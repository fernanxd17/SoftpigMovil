package com.Softpig.View.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;

public class ReportFragment extends Fragment {

    private View viewReport;
    private LinearLayout llInformeGeneral, llInformeFertilidad;
    private InformeFertilidadFragment informeFertilidadFragment;
    private InformeGeneralFragment informeGeneralFragment;

    public ReportFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewReport = inflater.inflate(R.layout.fragment_report, container, false);

        llInformeGeneral = viewReport.findViewById(R.id.ll_informe_general);
        llInformeFertilidad = viewReport.findViewById(R.id.ll_informe_fertilidad);

        informeFertilidadFragment = new InformeFertilidadFragment();
        informeGeneralFragment = new InformeGeneralFragment();


        llInformeGeneral.setOnClickListener(view ->  presentarInformeGeneral());
        llInformeFertilidad.setOnClickListener(view -> invocarInformeFertilidad());
        return viewReport;
    }



    private void invocarInformeFertilidad() {
        ((MainMenuActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, informeFertilidadFragment).commit();
    }

    private void presentarInformeGeneral() {
       // ((MainMenuActivity)getContext()).presentarInformeGeneral();
        ((MainMenuActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, informeGeneralFragment).commit();
    }

}
