package com.Softpig.View.Fragment;


import android.graphics.Color;
import android.media.audiofx.AudioEffect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.Softpig.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
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
    private LineChart linePartos, lineGest, lineCelos;

    public FragmentGrafProm() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vBarChart = inflater.inflate(R.layout.fragment_bar_chart, container, false);
        bchar = vBarChart.findViewById(R.id.chart1);
        linePartos = vBarChart.findViewById(R.id.line_chart_anual_partos);
        lineGest = vBarChart.findViewById(R.id.line_chart_anual_gest);
        lineCelos = vBarChart.findViewById(R.id.line_chart_anual_celos);
        bchar.getDescription().setEnabled(true);
        labels = new ArrayList<>();
        setData();
        graficarLinealCelos();
        graficarLinealGest();
        graficarLinealPartos();

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

    private void graficarLinealCelos() {
        lineCelos.setVisibility(View.VISIBLE);
        lineCelos.setDragEnabled(true);
        lineCelos.setScaleEnabled(false);

        lineCelos.getAxisLeft().setEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(2017,1f));
        yValues.add(new Entry(2018,1f));
        yValues.add(new Entry(2019,12f));



        LineDataSet set1 = new LineDataSet(yValues, "Celos / Año");

        set1.setFillAlpha(110);

        set1.setColor(Color.RED);
        set1.setLineWidth(3f);
        set1.setValueTextSize(12f);
        set1.setValueTextColor(Color.BLACK);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        dataSets.add(set1);
        LineData data = new LineData(dataSets);
        lineCelos.setData(data);
    }

    private void graficarLinealGest() {
        lineGest.setVisibility(View.VISIBLE);
        lineGest.setDragEnabled(true);
        lineGest.setScaleEnabled(false);

        lineGest.getAxisLeft().setEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(2017,5));
        yValues.add(new Entry(2018,4));
        yValues.add(new Entry(2019,13));

        LineDataSet set1 = new LineDataSet(yValues, "Gestación / Año");

        set1.setFillAlpha(110);

        set1.setColor(Color.RED);
        set1.setLineWidth(3f);
        set1.setValueTextSize(12f);
        set1.setValueTextColor(Color.BLACK);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        dataSets.add(set1);
        LineData data = new LineData(dataSets);
        lineGest.setData(data);
    }

    private void graficarLinealPartos() {
        linePartos.setVisibility(View.VISIBLE);
        linePartos.setDragEnabled(true);
        linePartos.setScaleEnabled(false);

        linePartos.getAxisLeft().setEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(2017,1f));
        yValues.add(new Entry(2018,3f));
        yValues.add(new Entry(2019,10f));

        LineDataSet set1 = new LineDataSet(yValues, "Partos / Año");

        set1.setFillAlpha(110);

        set1.setColor(Color.RED);
        set1.setLineWidth(3f);
        set1.setValueTextSize(12f);
        set1.setValueTextColor(Color.BLACK);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        dataSets.add(set1);
        LineData data = new LineData(dataSets);
        linePartos.setData(data);
    }

    public void setValores(ArrayList<BarEntry> yVals) {
        this.yVals = yVals;
    }
}
