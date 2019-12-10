package com.Softpig.View.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.Softpig.Model.Report;
import com.Softpig.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InformeGeneralFragment extends Fragment {


    private View viewGenerarFragment;
    private Button btGenerarPDF;
    private Report report;
    //encabezados de la tabla mostrada en el pdf de productividad general
    private String[] header={"Porcinos", "Hembras", "Machos", "Reproductoras", "Reproductores",
            "No Celos", "No Gestaciones", "No Partos", "Lechones", "Marrano", "Primal", "Gordo"};
    private String shortText = "Prueba";
    private String longText = "Acá supngo que puede ir una pequeña descirpción de lo que trata el reporte";


    public InformeGeneralFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewGenerarFragment = inflater.inflate(R.layout.fragment_informe_general, container, false);

        btGenerarPDF = viewGenerarFragment.findViewById(R.id.bt_generar_pdf);
        btGenerarPDF.setOnClickListener(view -> {
            generarPdf();
        });

        return viewGenerarFragment;
    }

    private void generarPdf() {
        Toast.makeText(this.getContext(), "Generar pdf", Toast.LENGTH_SHORT).show();
        report = new Report(this.getContext());
        report.addMetaData("Reporte de producción general","Producción","SoftPig");
        report.addTitles("Granja San Pablo", "Productividad", "9/12/2019");
        //report.createTable(header,);
    }

}
