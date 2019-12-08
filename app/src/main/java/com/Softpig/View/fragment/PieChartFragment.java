package com.Softpig.View.fragment;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Softpig.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PieChartFragment extends Fragment {

    private PieChart gfFertMachos;
    private View viewFertMachos;

    public PieChartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewFertMachos = inflater.inflate(R.layout.fragment_piechart, container, false);
        gfFertMachos = viewFertMachos.findViewById(R.id.grafic_fert_machos);
        generarGrafico();


        return viewFertMachos;
    }

    private void generarGrafico() {
        gfFertMachos.setUsePercentValues(true);
        gfFertMachos.getDescription().setEnabled(false);
        gfFertMachos.setExtraOffsets(5,10,5,5);
        gfFertMachos.setDragDecelerationFrictionCoef(0.99f);

        gfFertMachos.setDrawHoleEnabled(true);
        gfFertMachos.setHoleColor(Color.WHITE);
        gfFertMachos.setTransparentCircleRadius(60f);

        ArrayList<PieEntry> yValues = tomarValores();

        Description description = new Description();
        description.setText("Este es un ejemplo de grafico");
        description.setTextSize(9);
        gfFertMachos.setDescription(description);

        gfFertMachos.animateY(1000, Easing.EaseInOutCubic);

        PieDataSet dataSet = new PieDataSet(yValues, "Paises");
        dataSet.setSliceSpace(2f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        gfFertMachos.setData(data);
    }

    private ArrayList<PieEntry> tomarValores() {

        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(34f, "España"));
        yValues.add(new PieEntry(24f, "Colombia"));
        yValues.add(new PieEntry(14f, "Brasil"));
        yValues.add(new PieEntry(4f, "Argentina"));
        yValues.add(new PieEntry(50f, "Chile"));
        yValues.add(new PieEntry(12, "Ven"));
        yValues.add(new PieEntry(14f, "Peru"));

        return yValues;
    }

}
