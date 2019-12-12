package com.Softpig.View.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.Softpig.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerracosHembrasFragment extends Fragment {

    private List<ArrayList<PieEntry>> valores;
    private PieChart gfVerracos;
    private PieChart gfHembras;
    private View viewFertMachos;

    public VerracosHembrasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewFertMachos = inflater.inflate(R.layout.fragment_graf_verracos_hembras, container, false);
        gfHembras = viewFertMachos.findViewById(R.id.grafic_hembras);
        gfVerracos = viewFertMachos.findViewById(R.id.grafic_verracos);

        generarGraficoVerracos();
        generarGraficoHembras();

        return viewFertMachos;
    }

    private void generarGraficoHembras() {
        gfHembras.setUsePercentValues(true);
        gfHembras.getDescription().setEnabled(false);
        gfHembras.setExtraOffsets(10,10,10,10);
        gfHembras.setDragDecelerationFrictionCoef(0.99f);

        gfHembras.setDrawHoleEnabled(true);
        gfHembras.setHoleColor(Color.WHITE);
        gfHembras.setTransparentCircleRadius(60f);



        Description description = new Description();
        description.setText("Reproductoras / Hembras");
        description.setTextSize(12);
        gfHembras.setDescription(description);

        gfHembras.animateY(1000, Easing.EaseInOutCubic);

        PieDataSet dataSet = new PieDataSet(valores.get(1), " | Tipo");
        dataSet.setSliceSpace(4f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        gfHembras.setData(data);
    }

    private void generarGraficoVerracos() {

        gfVerracos.setUsePercentValues(true);
        gfVerracos.getDescription().setEnabled(false);
        gfVerracos.setExtraOffsets(10,10,10,10);
        gfVerracos.setDragDecelerationFrictionCoef(0.99f);

        gfVerracos.setDrawHoleEnabled(true);
        gfVerracos.setHoleColor(Color.WHITE);
        gfVerracos.setTransparentCircleRadius(60f);



        Description description = new Description();
        description.setText("Verracos/Machos.");
        description.setTextSize(12);
        gfVerracos.setDescription(description);

        gfVerracos.animateY(1000, Easing.EaseInOutCubic);

        PieDataSet dataSet = new PieDataSet(valores.get(0), " | Tipo");
        dataSet.setSliceSpace(4f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        gfVerracos.setData(data);

    }

    public void setValores(List<ArrayList<PieEntry>> valores) {
        this.valores = valores;
    }
}
