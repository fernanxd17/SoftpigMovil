package com.Softpig.View.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;

public class ReportFragment extends Fragment {

    private View viewReport;
    private LinearLayout llInformeGeneral, llFertilidadMachos, llProduccionHembras;
    private FertilidadMachosFragment fertilidadMachosFragment;

    public ReportFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewReport = inflater.inflate(R.layout.fragment_report, container, false);

        llInformeGeneral = viewReport.findViewById(R.id.ll_informe_general);
        llFertilidadMachos = viewReport.findViewById(R.id.ll_fertilidad_machos);
        llProduccionHembras = viewReport.findViewById(R.id.ll_produccion_hembras);

        fertilidadMachosFragment = new FertilidadMachosFragment();

        llInformeGeneral.setOnClickListener(view ->  invocarInformeGeneral());
        llFertilidadMachos.setOnClickListener(view -> invocarFertilidadMachos());
        llProduccionHembras.setOnClickListener(view -> invocarProduccionHembras());
        return viewReport;
    }

    private void invocarProduccionHembras() {
        //((MainMenuActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, daa).commit();
    }

    private void invocarFertilidadMachos() {
        ((MainMenuActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, fertilidadMachosFragment).commit();
    }

    private void invocarInformeGeneral() {
        //((MainMenuActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, dashBoardFragment).commit();
    }

}
