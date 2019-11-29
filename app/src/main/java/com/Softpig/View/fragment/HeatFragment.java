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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.Softpig.Model.Heat;
import com.Softpig.Presenter.Adapters.BirthAdapter;
import com.Softpig.Presenter.Adapters.HeatAdapter;
import com.Softpig.R;
import com.Softpig.View.PigActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HeatFragment extends Fragment {

    private RecyclerView recyclerHeat;
    private HeatAdapter heatAdapter;
    private List<Heat> listHeat;
    private TextView tvNoHeat;
    private FloatingActionButton fbAddHeat;

    private RadioButton matingNatural;
    private RadioButton matingEnsiminacion;
    private RadioButton btSi, btNo;
    private EditText mostrarFechaStart, mostrarFechaEnd;
    private ImageButton obtenerFechaStart, obtenerFechaEnd;
    private Button btCancelar, btAgregar;

    private boolean sincrony;
    private String mating;

    private static final String CERO = "0";
    private static final String RAYA = "-";
    private static final String DOS_PUNTOS = ":";


    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    public HeatFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewHeat = inflater.inflate(R.layout.fragment_list_heat, container, false);
        ((PigActivity)getActivity()).setSearch("Heat");
        tvNoHeat = viewHeat.findViewById(R.id.tv_noheats);
        if(listHeat.isEmpty()){
            tvNoHeat.setText("No hay celos registrados");
        }else{
            heatAdapter = new HeatAdapter(listHeat, getContext());
            recyclerHeat = viewHeat.findViewById(R.id.recyclerHeat);
            recyclerHeat.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerHeat.setAdapter(heatAdapter);
        }
        fbAddHeat = viewHeat.findViewById(R.id.fb_add_heat_female);
        fbAddHeat.setOnClickListener(view -> {



            final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
            View viewDialog = getLayoutInflater().inflate(R.layout.add_heat, null);


            matingNatural = viewDialog.findViewById(R.id.rb_natural);
            matingEnsiminacion = viewDialog.findViewById(R.id.rb_inseminacion);
            btAgregar = viewDialog.findViewById(R.id.bt_agregar);
            btCancelar = viewDialog.findViewById(R.id.bt_cancelar);
            mostrarFechaEnd = viewDialog.findViewById(R.id.et_mostrar_fecha_end);
            mostrarFechaStart = viewDialog.findViewById(R.id.et_mostrar_fecha_start);
            obtenerFechaEnd = viewDialog.findViewById(R.id.ib_obtener_fecha_end);
            obtenerFechaStart = viewDialog.findViewById(R.id.ib_obtener_fecha_start);
            btNo = viewDialog.findViewById(R.id.bt_no);
            btSi = viewDialog.findViewById(R.id.bt_si);


            obtenerFechaStart.setOnClickListener(view15 -> {
                DatePickerDialog recogerFecha = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view15, int year, int month, int dayOfMonth) {
                        //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                        final int mesActual = month + 1;
                        //Formateo el día obtenido: antepone el 0 si son menores de 10
                        String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                        //Formateo el mes obtenido: antepone el 0 si son menores de 10
                        String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                        //Muestro la fecha con el formato deseado
                        mostrarFechaStart.setText(year + RAYA + mesFormateado + RAYA + diaFormateado);
                    }
                    //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
                    /**
                     *También puede cargar los valores que usted desee
                     */
                },anio, mes, dia);
                //Muestro el widget
                recogerFecha.show();
            });

            obtenerFechaEnd.setOnClickListener(view12 -> {
                //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
/**
 *También puede cargar los valores que usted desee
 */
                DatePickerDialog recogerFecha = new DatePickerDialog(getContext(), (view1, year, month, dayOfMonth) -> {
                    //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                    final int mesActual = month + 1;
                    //Formateo el día obtenido: antepone el 0 si son menores de 10
                    String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                    //Formateo el mes obtenido: antepone el 0 si son menores de 10
                    String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                    //Muestro la fecha con el formato deseado
                    mostrarFechaEnd.setText(year + RAYA + mesFormateado + RAYA + diaFormateado);
                },anio, mes, dia);
                //Muestro el widget
                recogerFecha.show();
            });

            alert.setView(viewDialog);

            final AlertDialog alertDialog = alert.create();
            alertDialog.setCanceledOnTouchOutside(false);

            btCancelar.setOnClickListener(v -> alertDialog.dismiss());

            btAgregar.setOnClickListener(view16 -> {

                mating = "";
                if(matingNatural.isChecked()){
                    mating = "Natural";
                }else if(matingEnsiminacion.isChecked()){
                    mating = "Inseminacion";
                }

                sincrony = false;

                if(btSi.isChecked()){
                    sincrony= true;
                }
                Heat heat = new Heat(mating,sincrony, mostrarFechaStart.getText().toString(),
                        mostrarFechaEnd.getText().toString());
                ((PigActivity)getContext()).agregarCelo(heat, alertDialog);
            });

            alertDialog.show();

        });

        return viewHeat;
    }

    public HeatAdapter getHeatAdapter(){
        return heatAdapter;
    }
    public void setListHeat(List<Heat> listHeat) {
        this.listHeat = listHeat;
    }
}
