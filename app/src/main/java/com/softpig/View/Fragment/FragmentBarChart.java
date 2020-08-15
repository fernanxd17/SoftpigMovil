package com.softpig.View.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.softpig.R;
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
public class FragmentBarChart extends Fragment {

    private BarChart bchar;
    private View vBarChart;
    private ArrayList<BarEntry> yVals;
    private LineChart lineChartPartos, lineChartGest, lineChartCelos;
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
        lineChartCelos = vBarChart.findViewById(R.id.line_chart_anual_celos);
        lineChartGest = vBarChart.findViewById(R.id.line_chart_anual_gest);
        lineChartPartos = vBarChart.findViewById(R.id.line_chart_anual_partos);
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

        //graficarLinealPartos();


    }

    private void graficarPartos(){

        lineChartPartos.setVisibility(View.VISIBLE);
        lineChartPartos.setDragEnabled(true);
        lineChartPartos.setScaleEnabled(false);

        lineChartPartos.getAxisLeft().setEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(2017,1f));
        yValues.add(new Entry(2018,5f));
        yValues.add(new Entry(2019,10f));

        LineDataSet set1 = new LineDataSet(yValues, "Partos");

        set1.setFillAlpha(110);

        set1.setColor(Color.RED);
        set1.setLineWidth(3f);
        set1.setValueTextSize(12f);
        set1.setValueTextColor(Color.BLACK);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        dataSets.add(set1);
        LineData data = new LineData(dataSets);
        lineChartPartos.setData(data);

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

        graficarLinealBebes();
        graficarLinealMomias();
        graficarLinealMuertos();

    }

    private void graficarLinealBebes() {
        lineChartCelos.setVisibility(View.VISIBLE);
        lineChartCelos.setDragEnabled(true);
        lineChartCelos.setScaleEnabled(false);

        lineChartCelos.getAxisLeft().setEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(2018,14f));
        yValues.add(new Entry(2019,39f));



        LineDataSet set1 = new LineDataSet(yValues, "Bebes / Año");

        set1.setFillAlpha(110);

        set1.setColor(Color.RED);
        set1.setLineWidth(3f);
        set1.setValueTextSize(12f);
        set1.setValueTextColor(Color.BLACK);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        dataSets.add(set1);
        LineData data = new LineData(dataSets);
        lineChartCelos.setData(data);
    }

    private void graficarLinealMuertos() {
        lineChartGest.setVisibility(View.VISIBLE);
        lineChartGest.setDragEnabled(true);
        lineChartGest.setScaleEnabled(false);

        lineChartGest.getAxisLeft().setEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(2018,0));
        yValues.add(new Entry(2019,4));

        LineDataSet set1 = new LineDataSet(yValues, "Muertos / Año");

        set1.setFillAlpha(110);

        set1.setColor(Color.RED);
        set1.setLineWidth(3f);
        set1.setValueTextSize(12f);
        set1.setValueTextColor(Color.BLACK);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        dataSets.add(set1);
        LineData data = new LineData(dataSets);
        lineChartGest.setData(data);
    }

    private void graficarLinealMomias() {
        lineChartPartos.setVisibility(View.VISIBLE);
        lineChartPartos.setDragEnabled(true);
        lineChartPartos.setScaleEnabled(false);

        lineChartPartos.getAxisLeft().setEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(2018,1f));
        yValues.add(new Entry(2019,1f));

        LineDataSet set1 = new LineDataSet(yValues, "Momias / Año");

        set1.setFillAlpha(110);

        set1.setColor(Color.RED);
        set1.setLineWidth(3f);
        set1.setValueTextSize(12f);
        set1.setValueTextColor(Color.BLACK);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        dataSets.add(set1);
        LineData data = new LineData(dataSets);
        lineChartPartos.setData(data);
    }

    public void seValores(ArrayList<BarEntry> yVals) {
        this.yVals = yVals;
    }


}
