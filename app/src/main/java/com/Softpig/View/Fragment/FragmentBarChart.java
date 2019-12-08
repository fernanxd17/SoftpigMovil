package com.Softpig.View.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Softpig.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBarChart extends Fragment {

    private BarChart bchar;
    private View vBarChart;

    public FragmentBarChart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vBarChart = inflater.inflate(R.layout.fragment_bar_chart, container, false);
        bchar = vBarChart.findViewById(R.id.chart1);
        bchar.getDescription().setEnabled(false);

        setData(10);
        bchar.setFitBars(true);

        return vBarChart;
    }

    private void setData(int count) {
        ArrayList<BarEntry> yVals = new ArrayList<>();

        for (int i = 0; i< count; i++){
            float value = (float)(Math.random()*100);
            yVals.add(new BarEntry(i, (int)value));
        }

        BarDataSet set = new BarDataSet(yVals, "Data Set");
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        set.setDrawValues(true);

        BarData data = new BarData(set);

        bchar.setData(data);
        bchar.invalidate();
        bchar.animateY(500);

    }

}
