package com.Softpig.View.fragment;


import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.Softpig.Model.PeriodGestation;
import com.Softpig.Presenter.Adapters.GestationAdapter;
import com.Softpig.R;
import com.Softpig.View.PigActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class GestationFragment extends Fragment {

    private List<PeriodGestation> listPeriodGestation;
    private RecyclerView recyclerGestation;
    private GestationAdapter gestationAdapter;
    private TextView noGestation;
    private FloatingActionButton fbAddGestation;
    private ArrayAdapter<String> adapterSpinnerGestation;
    private Spinner spIdMale;
    private Button btCancelar, btAgregar;
    private ImageButton ibFechaGestacion;
    private EditText etFechaGestacion;
    private String idMale;
    private List<String> listIdMale;
    private static final String CERO = "0";
    private static final String RAYA = "-";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    public GestationFragment(String [] listIdMale) {
        this.listIdMale = new ArrayList<String>();
        llenarListaId(listIdMale);
        idMale = "";

    }

    private void llenarListaId(String[] listIdMale) {
        this.listIdMale.add("Elija ID Reproductor");
        for (int i = 0; i < listIdMale.length; i++){
            this.listIdMale.add(listIdMale[i]);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewGestation =  inflater.inflate(R.layout.fragment_list_gestation, container, false);
        ((PigActivity)getActivity()).setSearch("Gestation");
        noGestation = viewGestation.findViewById(R.id.tv_noGestationPeriod);

        if(listPeriodGestation.isEmpty()){
            noGestation.setText("No existen registros de Periodos de Gestación");
        }else{
            gestationAdapter = new GestationAdapter(listPeriodGestation, getContext());
            recyclerGestation = viewGestation.findViewById(R.id.recyclergestation);
            recyclerGestation.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerGestation.setAdapter(gestationAdapter);
        }

        fbAddGestation = viewGestation.findViewById(R.id.fb_add_gestation_female);
        fbAddGestation.show();

        fbAddGestation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                View viewDialog = getLayoutInflater().inflate(R.layout.add_gestation, null);

                adapterSpinnerGestation = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, listIdMale);
                adapterSpinnerGestation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spIdMale = viewDialog.findViewById(R.id.sp_id_male);
                btCancelar = viewDialog.findViewById(R.id.bt_cancelar);
                btAgregar = viewDialog.findViewById(R.id.bt_agregar);

                spIdMale = viewDialog.findViewById(R.id.sp_id_male);
                spIdMale.setAdapter(adapterSpinnerGestation);

                spIdMale.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        if(position > 0)
                            idMale = (String) parent.getSelectedItem();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) { }
                });


                etFechaGestacion = viewDialog.findViewById(R.id.et_mostrar_fecha);
                ibFechaGestacion = viewDialog.findViewById(R.id.ib_obtener_fecha);
                ibFechaGestacion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog recogerFecha = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                                final int mesActual = month + 1;
                                //Formateo el día obtenido: antepone el 0 si son menores de 10
                                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                                //Muestro la fecha con el formato deseado
                                etFechaGestacion.setText(year + RAYA + mesFormateado + RAYA + diaFormateado);
                            }
                            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
                            /**
                             *También puede cargar los valores que usted desee
                             */
                        },anio, mes, dia);
                        //Muestro el widget
                        recogerFecha.show();
                    }
                });

                alert.setView(viewDialog);

                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);



                btCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                btAgregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((PigActivity)getContext()).agregarGestacion(idMale, etFechaGestacion.getText().toString());
                    }
                });

                alertDialog.show();
            }


        });


        return viewGestation;

    }



    public void setListGestation(List<PeriodGestation> listGestation) {
        this.listPeriodGestation = listGestation;
    }

    public GestationAdapter getGestationAdapter() {
        return gestationAdapter;
    }

}
