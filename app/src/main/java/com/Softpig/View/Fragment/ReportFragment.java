package com.Softpig.View.Fragment;

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
    private LinearLayout llInformeGeneral, llInformeFertilidad;
    private short  [] valores;
    private TextView tvBebes, tvDead, tvGestaciones, tvPartos;

    public ReportFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewReport = inflater.inflate(R.layout.fragment_report, container, false);

        capturarCampos();
        setearCampos();
        llInformeGeneral.setOnClickListener(view ->  presentarInformeGeneral());
        llInformeFertilidad.setOnClickListener(view -> presentarInformeFertilidad());
        return viewReport;
    }

    private void setearCampos() {
        tvBebes.setText("Bebes: "+valores[0]);
        tvDead.setText("Muertos: "+valores[1]);
        tvPartos.setText("Partos: "+valores[2]);
        tvGestaciones.setText("Gestaciones: "+valores[3]);
    }

    private void capturarCampos() {
        llInformeGeneral = viewReport.findViewById(R.id.ll_informe_general);
        llInformeFertilidad = viewReport.findViewById(R.id.ll_informe_fertilidad);
        tvBebes = viewReport.findViewById(R.id.no_bebes_mes);
        tvDead = viewReport.findViewById(R.id.no_dead_mes);
        tvGestaciones = viewReport.findViewById(R.id.no_gestaciones_mes);
        tvPartos = viewReport.findViewById(R.id.no_partos_mes);

    }


    private void presentarInformeFertilidad() {
        //((MainMenuActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, informeFertilidadFragment).commit();
    }

    private void presentarInformeGeneral() {
        ((MainMenuActivity)getContext()).presentarInformeGeneral();
    }

    public void setValores(final short[] valores) {
        this.valores = valores;
    }
}
