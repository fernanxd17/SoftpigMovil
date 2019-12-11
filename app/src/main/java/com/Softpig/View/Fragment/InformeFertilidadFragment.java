package com.Softpig.View.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softpig.Model.FertilityReport;
import com.Softpig.Model.Report;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InformeFertilidadFragment extends Fragment {


    private FertilityReport fertilityReport;
    private FragmentBarChart barChart;
    private View viewGenerarFragment;
    private Report report;
    private TextView tv_prom_female, tv_prom_male, tv_graf_prom_partos, tv_babies, tv_mommy, tv_dead, tv_weigth, tv_graf_info_births;

    public InformeFertilidadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewGenerarFragment = inflater.inflate(R.layout.fragment_informe_fertilidad, container, false);

        barChart = new FragmentBarChart();
        capturarCampos();
        setCampos();

        tv_graf_prom_partos.setOnClickListener(view -> {
            mostrarGraficoPromPartos();
        });

        tv_graf_info_births.setOnClickListener(view -> {
            mostrarGraficoBarras();
        });

        return viewGenerarFragment;
    }

    private void mostrarGraficoBarras() {
        ArrayList<BarEntry> values = new ArrayList<>();
        values.add(new BarEntry(0, fertilityReport.getPrombabies()));
        values.add(new BarEntry(1, fertilityReport.getPromMommy()));
        values.add(new BarEntry(1, fertilityReport.getPromDead()));
        values.add(new BarEntry(1, fertilityReport.getPromWeigth()));
        barChart.seValores(values);
        ((MainMenuActivity)getContext()).getSupportFragmentManager().beginTransaction().
                replace(R.id.containerFragments, barChart).addToBackStack(null).commit();

    }

    private void mostrarGraficoPromPartos() {
        ArrayList<BarEntry> values = new ArrayList<>();
        values.add(new BarEntry(0, fertilityReport.getPromBirthsFemale()));
        values.add(new BarEntry(1, fertilityReport.getPromBirthsMale()));
        barChart.seValores(values);
        ((MainMenuActivity)getContext()).getSupportFragmentManager().beginTransaction().
                replace(R.id.containerFragments, barChart).addToBackStack(null).commit();

    }

    private void setCampos() {
        tv_prom_female.setText(String.valueOf(fertilityReport.getPromBirthsFemale()));
        tv_prom_male.setText(String.valueOf(fertilityReport.getPromBirthsMale()));
        tv_babies.setText(String.valueOf(fertilityReport.getPrombabies()));
        tv_mommy.setText(String.valueOf(fertilityReport.getPromMommy()));
        tv_dead.setText(String.valueOf(fertilityReport.getPromDead()));
        tv_weigth.setText(String.valueOf(fertilityReport.getPromWeigth()));
    }

    private void capturarCampos() {
        tv_prom_female = viewGenerarFragment.findViewById(R.id.tv_prom_female);
        tv_prom_male = viewGenerarFragment.findViewById(R.id.tv_prom_male);
        tv_babies = viewGenerarFragment.findViewById(R.id.tv_babies);
        tv_mommy = viewGenerarFragment.findViewById(R.id.tv_mommy);
        tv_dead = viewGenerarFragment.findViewById(R.id.tv_dead);
        tv_weigth = viewGenerarFragment.findViewById(R.id.tv_weigth);
        tv_graf_prom_partos = viewGenerarFragment.findViewById(R.id.tv_graf_prom_partos);
        tv_graf_info_births = viewGenerarFragment.findViewById(R.id.tv_graf_info_births);
    }

    public void setReport(FertilityReport fertilityReport) {
        this.fertilityReport = fertilityReport;
    }
}
