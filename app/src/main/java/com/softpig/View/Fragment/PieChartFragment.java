package com.softpig.View.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.softpig.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PieChartFragment extends Fragment{
    private PieChart gfFertMachos;
    private LineChart gfLineChartAnual;
    private View viewFertMachos;
    private ArrayList<PieEntry> valoresGraf;
    Description description;
    private String grafico;

    public PieChartFragment(String grafico) {
        // Required empty public constructor
        this.grafico = grafico;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewFertMachos = inflater.inflate(R.layout.fragment_piechart, container, false);
        gfFertMachos = viewFertMachos.findViewById(R.id.grafic_fert_machos);
        gfLineChartAnual = viewFertMachos.findViewById(R.id.line_chart_anual);
        
        generarGrafico();
        if(grafico.equalsIgnoreCase("cerdos")){
            generarGraficoLineChartAnual();
        }else{
            gfLineChartAnual.setVisibility(View.INVISIBLE);
        }





        return viewFertMachos;
    }

    private void generarGraficoLineChartAnual() {
        //gfLineChartAnual.setOnChartGestureListener((OnChartGestureListener) getContext());
        //gfLineChartAnual.setOnChartValueSelectedListener((OnChartValueSelectedListener) getContext());
        gfLineChartAnual.setVisibility(View.VISIBLE);
        gfLineChartAnual.setDragEnabled(true);
        gfLineChartAnual.setScaleEnabled(false);

        gfLineChartAnual.getAxisLeft().setEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(2015,1f));
        yValues.add(new Entry(2017,2f));
        yValues.add(new Entry(2018,8f));
        yValues.add(new Entry(2019,9f));

        LineDataSet set1 = new LineDataSet(yValues, "Registro de cerdos");

        set1.setFillAlpha(110);

        set1.setColor(Color.RED);
        set1.setLineWidth(3f);
        set1.setValueTextSize(12f);
        set1.setValueTextColor(Color.BLACK);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        dataSets.add(set1);
        LineData data = new LineData(dataSets);
        gfLineChartAnual.setData(data);


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


    private class MyAxisValueFormatter extends IndexAxisValueFormatter {

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            switch ((int)value){
                case 1: return "2015";
                case 2: return "2017";
                case 3: return "2018";
                case 4: return "2019";
            }

            return "";
        }
    }


}


