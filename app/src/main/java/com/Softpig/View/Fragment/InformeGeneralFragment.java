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


public class InformeGeneralFragment extends Fragment {

    private FragmentBarChart barChart;
    private PieChartFragment pieChartFragment;
    private View viewGenerarFragment;
    private Button btGenerarPDF;
    private Report report;
    private TextView tvGrafEtapas;
    private GeneralReport generalReport;
    //encabezados de la tabla mostrada en el pdf de productividad general
    private String[] header={"Porcinos", "Hembras", "Machos", "Reproductoras", "Reproductores",
            "No Celos", "No Gestaciones", "No Partos", "Lechones", "Marrano", "Primal", "Gordo"};
    private String shortText = "Prueba";
    private String longText = "Acá supngo que puede ir una pequeña descirpción de lo que trata el reporte";
    private TextView tvTotalCerdos, tvTotalMachos, tvTotalHembras, tvMachosRpd, tvHembrasRpd;
    private TextView tvLechones, tvMarranos, tvPromNaci, tvPromCelos, tvPromGest;

    private TextView tvGrafCerdos;


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
        capturarCampos();
        setCampos();

        tvGrafCerdos.setOnClickListener(view -> {
            mostrarGraficoCerdos();
        });
        btGenerarPDF.setOnClickListener(view -> {
            generarPdf();
        });
        tvGrafEtapas.setOnClickListener(view -> {
            mostrarDiagBarras();
        });

        return viewGenerarFragment;
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
        tvTotalCerdos.setText(generalReport.getPigsFarm()  + " unidades");
        tvTotalMachos.setText(generalReport.getMalesFarm() + " unidades");
        tvTotalHembras.setText(generalReport.getFemalesFarm() + " unidades");
        tvMachosRpd.setText(generalReport.getMalesRpd() + " unidades");
        tvHembrasRpd.setText(generalReport.getFemaleRpd() + " unidades");
        tvLechones.setText(generalReport.getLechones() + " unidades");
        tvMarranos.setText(generalReport.getMarranos() + " unidades");
        tvPromNaci.setText(generalReport.getPromNaci() + " unidades");
        tvPromCelos.setText(generalReport.getPromCelos() + " unidades");
        tvPromGest.setText(generalReport.getPromGest() + " unidades");
    }

    private void capturarCampos(){
        tvGrafCerdos = viewGenerarFragment.findViewById(R.id.tv_graf_cerdos);
        btGenerarPDF = viewGenerarFragment.findViewById(R.id.bt_generar_pdf);
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
