package com.Softpig.View.Fragment;


import android.media.audiofx.AudioEffect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.Softpig.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGrafProm extends Fragment {

    private ArrayList<BarEntry> yVals;
    private ArrayList<String> labels;
    private BarChart bchar;
    View vBarChart;

    public FragmentGrafProm() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vBarChart = inflater.inflate(R.layout.fragment_bar_chart, container, false);
        bchar = vBarChart.findViewById(R.id.chart1);
        bchar.getDescription().setEnabled(false);
        labels = new ArrayList<>();
        setData();

        bchar.setFitBars(true);

        return vBarChart;
    }

    private void setData() {
        labels.add("Nacimientos");
        labels.add("Gestaciones");
        labels.add("Celos");
        BarDataSet set = new BarDataSet(yVals, "Promedios");
        set.setColors(ColorTemplate.COLORFUL_COLORS);
        Description description = new Description();
        description.setText("Promedios de Fertilidad");
        bchar.setDescription(description);
        set.setDrawValues(true);
        BarData data = new BarData(set);
        bchar.setData(data);
        bchar.invalidate();
        bchar.animateY(500);

        XAxis xAxis = bchar.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        //set position of labels
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(labels.size());
        xAxis.setLabelRotationAngle(270);
        bchar.animateY(2000);
        bchar.invalidate();
    }

    public void setValores(ArrayList<BarEntry> yVals) {
        this.yVals = yVals;
    }
}
