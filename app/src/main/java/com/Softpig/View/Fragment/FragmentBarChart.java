package com.Softpig.View.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public class FragmentBarChart extends Fragment {

    private BarChart bchar;
    private View vBarChart;
    private ArrayList<BarEntry> yVals;
    private ArrayList<String> labels;
    Description description;
    String typeGraf;

    public FragmentBarChart(String typeGraf) {
        // Required empty public constructor
        this.typeGraf = typeGraf;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vBarChart = inflater.inflate(R.layout.fragment_bar_chart, container, false);
        bchar = vBarChart.findViewById(R.id.chart1);
        bchar.getDescription().setEnabled(true);
        labels = new ArrayList<>();
        if(typeGraf.equalsIgnoreCase("partos")){
            setDataPromedios();
        }else{
            setDataPartos();
        }


        bchar.setFitBars(true);

        return vBarChart;
    }

    private void setDataPromedios() {
        labels = new ArrayList<>();
        labels.add("Hembras");
        labels.add("Machos");
        BarDataSet set = new BarDataSet(yVals, "Promedios");
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        Description description = new Description();
        description.setText("Promedios de Partos");
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

    public void setDataPartos() {
        labels = new ArrayList<>();
        labels.add("Bebes");
        labels.add("Muertos");
        labels.add("Momias");
        BarDataSet set = new BarDataSet(yVals, "Promedios");
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        Description description = new Description();
        description.setText("Promedios de Partos");
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

    public void seValores(ArrayList<BarEntry> yVals) {
        this.yVals = yVals;
    }


}
