package com.Softpig.View.Fragment;


import android.graphics.Color;
import android.graphics.Paint;
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
    private ArrayList<PieEntry> valoresGraf;
    Description description;

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

    public void setDescription(String description){
        this.description= new Description();
        this.description.setText(description);
        this.description.setTextSize(18);
    }

    private void generarGrafico() {
        gfFertMachos.setUsePercentValues(true);
        gfFertMachos.getDescription().setEnabled(false);
        gfFertMachos.setExtraOffsets(10,10,10,10);
        gfFertMachos.setDragDecelerationFrictionCoef(0.99f);

        gfFertMachos.setDrawHoleEnabled(true);
        gfFertMachos.setHoleColor(Color.WHITE);
        gfFertMachos.setTransparentCircleRadius(60f);


        gfFertMachos.setDescription(description);

        gfFertMachos.animateY(1000, Easing.EaseInOutCubic);

        PieDataSet dataSet = new PieDataSet(valoresGraf, "");
        dataSet.setSliceSpace(4f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        gfFertMachos.setData(data);
    }


    public void setValores(final ArrayList<PieEntry> valoresGraf) {
        this.valoresGraf = valoresGraf;
    }
}
