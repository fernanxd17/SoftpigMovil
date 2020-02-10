package com.Softpig.View.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;
import java.util.Calendar;

public class ReportFragment extends Fragment {

    private View viewReport;
    private LinearLayout llInformeGeneral, llInformeFertilidad;
    private short  [] valores;
    private TextView tvBebes, tvDead, tvGestaciones, tvPartos;
    private Calendar fecha;

    public ReportFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewReport = inflater.inflate(R.layout.fragment_report, container, false);

        capturarCampos();
        setearCampos();
        llInformeGeneral.setOnClickListener(view ->  presentarInformeGeneral());
        llInformeFertilidad.setOnClickListener(view -> presentarInformeFertilidad());
        return viewReport;
    }
    //Estos son los campos que se muestran en el dashboard de los informes
    private void setearCampos() {
        fecha = Calendar.getInstance();
        tvBebes.setText("Total de bebes nacidos: "+valores[0]+ " en "+fecha.get(Calendar.YEAR));
        tvDead.setText("Total bebes muertos al nacer : "+valores[1]+" en "+fecha.get(Calendar.YEAR));
        tvPartos.setText("Total de partos: "+valores[2]+" en el "+ fecha.get(Calendar.YEAR));
        tvGestaciones.setText("Total de gestaciones: "+valores[3]+" en el "+ fecha.get(Calendar.YEAR));
    }

    private void capturarCampos() {
        llInformeGeneral = viewReport.findViewById(R.id.ll_informe_general);
        llInformeFertilidad = viewReport.findViewById(R.id.ll_informe_fertilidad);
        tvBebes = viewReport.findViewById(R.id.no_bebes_mes);
        tvDead = viewReport.findViewById(R.id.no_dead_mes);
        tvGestaciones = viewReport.findViewById(R.id.no_gestaciones_mes);
        tvPartos = viewReport.findViewById(R.id.no_partos_mes);

    }


    private void presentarInformeFertilidad() {
        ((MainMenuActivity)getContext()).presentarInformeFertilidad();
    }

    private void presentarInformeGeneral() {
        ((MainMenuActivity)getContext()).presentarInformeGeneral();
    }

    public void setValores(final short[] valores) {
        this.valores = valores;
    }
}
