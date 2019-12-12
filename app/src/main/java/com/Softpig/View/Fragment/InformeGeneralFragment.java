package com.Softpig.View.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.Softpig.Model.GeneralReport;
import com.Softpig.Model.Report;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;


public class InformeGeneralFragment extends Fragment {

    private FragmentBarChart barChart;
    private PieChartFragment pieChartFragment;
    private VerracosHembrasFragment verracosHembrasFragment;
    private View viewGenerarFragment;
    private Button btGenerarPDF;
    private Report report;
    private TextView tvGrafEtapas;
    private GeneralReport generalReport;
    private FragmentGrafProm fragmentGrafProm;
    //encabezados de la tabla mostrada en el pdf de productividad general
    private String[] header={"Porcinos", "Hembras", "Machos", "Reproductoras", "Reproductores",
            "No Celos", "No Gestaciones", "No Partos", "Lechones", "Marrano", "Primal", "Gordo"};
    private String shortText = "Prueba";
    private String longText = "Acá supngo que puede ir una pequeña descirpción de lo que trata el reporte";
    private TextView tvTotalCerdos, tvTotalMachos, tvTotalHembras, tvMachosRpd, tvHembrasRpd;
    private TextView tvLechones, tvMarranos, tvPromNaci, tvPromCelos, tvPromGest;

    private TextView tvGrafCerdos, tv_graf_reproductores, tv_graf_fertilidad;


    public InformeGeneralFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewGenerarFragment = inflater.inflate(R.layout.fragment_informe_general, container, false);
        pieChartFragment = new PieChartFragment();
        barChart = new FragmentBarChart();
        verracosHembrasFragment = new VerracosHembrasFragment();
        fragmentGrafProm = new FragmentGrafProm();
        capturarCampos();
        setCampos();

        tvGrafCerdos.setOnClickListener(view -> {
            mostrarGraficoCerdos();
        });
        tvGrafEtapas.setOnClickListener(view -> {
            mostrarDiagBarras();
        });

        tv_graf_reproductores.setOnClickListener(view -> {
            mostrarGraficoReproductores();
        });

        tv_graf_fertilidad.setOnClickListener(view -> {
            mostrarGraficoFertilidad();
        });

        return viewGenerarFragment;
    }

    private void mostrarGraficoFertilidad() {
        ArrayList<BarEntry> yVals = setValoresPromedios();
        fragmentGrafProm.setValores(yVals);
        ((MainMenuActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, fragmentGrafProm).addToBackStack(null).commit();

    }

    private ArrayList<BarEntry> setValoresPromedios() {
        ArrayList<BarEntry> yVals = new ArrayList<>();

        yVals.add(new BarEntry(0, generalReport.getPromNaci()));
        yVals.add(new BarEntry(1, generalReport.getPromGest()));
        yVals.add(new BarEntry(2, generalReport.getPromCelos()));

        return yVals;
    }

    private void mostrarGraficoReproductores() {
        List<ArrayList<PieEntry>> valores = setValoresgraficoVerracosHembras();
        verracosHembrasFragment.setValores(valores);
        ((MainMenuActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, verracosHembrasFragment).addToBackStack(null).commit();
    }

    private List<ArrayList<PieEntry>> setValoresgraficoVerracosHembras() {
        List<ArrayList<PieEntry>> listValues = new ArrayList<>();
        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(generalReport.getMalesRpd(), "Verracos"));
        yValues.add(new PieEntry(generalReport.getMalesFarm(), "Machos"));
        listValues.add(yValues);

        yValues = new ArrayList<>();
        yValues.add(new PieEntry(generalReport.getFemaleRpd(), "Reproductoras"));
        yValues.add(new PieEntry(generalReport.getFemalesFarm(), "Hembras"));
        listValues.add(yValues);

        return listValues;
    }

    private void mostrarDiagBarras() {
        ArrayList<BarEntry> yVals = new ArrayList<>();
        yVals.add(new BarEntry(0, generalReport.getLechones()));
        yVals.add(new BarEntry(1, generalReport.getMarranos()));
        barChart.seValores(yVals);
        ((MainMenuActivity)getContext()).getSupportFragmentManager().beginTransaction().
                replace(R.id.containerFragments, barChart).addToBackStack(null).commit();

    }

    private void mostrarGraficoCerdos() {
        ArrayList<PieEntry> valores = setValoresgrafico();
        pieChartFragment.setValores(valores);
        ((MainMenuActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, pieChartFragment).addToBackStack(null).commit();
    }

    private ArrayList<PieEntry> setValoresgrafico() {

        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(generalReport.getMalesFarm(), "Machos"));
        yValues.add(new PieEntry(generalReport.getFemalesFarm(), "Hembras"));
        return yValues;
    }


    private void setCampos() {
        tvTotalCerdos.setText(String.valueOf(generalReport.getPigsFarm()));
        tvTotalMachos.setText(String.valueOf(generalReport.getMalesFarm()));
        tvTotalHembras.setText(String.valueOf(generalReport.getFemalesFarm()));
        tvMachosRpd.setText(String.valueOf(generalReport.getMalesRpd()));
        tvHembrasRpd.setText(String.valueOf(generalReport.getFemaleRpd()));
        tvLechones.setText(String.valueOf(generalReport.getLechones()));
        tvMarranos.setText(String.valueOf(generalReport.getMarranos()));
        tvPromNaci.setText(String.valueOf(generalReport.getPromNaci()));
        tvPromCelos.setText(String.valueOf(generalReport.getPromCelos()));
        tvPromGest.setText(String.valueOf(generalReport.getPromGest()));
    }

    private void capturarCampos(){
        tv_graf_fertilidad = viewGenerarFragment.findViewById(R.id.tv_graf_fertilidad);
        tvGrafCerdos = viewGenerarFragment.findViewById(R.id.tv_graf_cerdos);
        tvTotalCerdos = viewGenerarFragment.findViewById(R.id.tv_total_cerdos);
        tvTotalMachos = viewGenerarFragment.findViewById(R.id.tv_total_machos);
        tvTotalHembras = viewGenerarFragment.findViewById(R.id.tv_total_hembras);
        tvMachosRpd = viewGenerarFragment.findViewById(R.id.tv_total_male_rep);
        tvHembrasRpd = viewGenerarFragment.findViewById(R.id.tv_total_female_rep);
        tvLechones = viewGenerarFragment.findViewById(R.id.tv_lechones);
        tvMarranos = viewGenerarFragment.findViewById(R.id.tv_marranos);
        tvPromNaci = viewGenerarFragment.findViewById(R.id.tv_prom_naci);
        tvPromCelos = viewGenerarFragment.findViewById(R.id.tv_prom_celos);
        tvGrafEtapas = viewGenerarFragment.findViewById(R.id.tv_graf_etapas);
        tvPromGest = viewGenerarFragment.findViewById(R.id.tv_prom_gest);
        tv_graf_reproductores = viewGenerarFragment.findViewById(R.id.tv_graf_reproductores);
    }

    private void generarPdf() {
        Toast.makeText(this.getContext(), "Generar pdf", Toast.LENGTH_SHORT).show();
        report = new Report(this.getContext());
        report.addMetaData("Reporte de producción general","Producción","SoftPig");
        report.addTitles("Granja San Pablo", "Productividad", "9/12/2019");
        //report.createTable(header,);
    }

    public void setReport(final GeneralReport generalReport) {
        this.generalReport = generalReport;
    }
}
