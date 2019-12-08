package com.Softpig.View.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

import com.Softpig.Model.Birth;
import com.Softpig.Presenter.Adapters.BirthAdapter;
import com.Softpig.Presenter.Adapters.MaleAdapter;
import com.Softpig.R;
import com.Softpig.View.PigActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BirthFragment extends Fragment {

    private List<Birth> listBirth;
    private RecyclerView recyclerBirth;
    private BirthAdapter birthAdapter;
    private TextView noBirth;
    private FloatingActionButton fbAddBirth;
    private Spinner spMale;
    private ArrayAdapter<String> adapterSpinnerMale;
    private EditText etMostrarFecha, etBabiesNumber, etMummyNumber, etDeadNumber;
    private ImageButton ibObtenerFecha;
    private Button btCancelar, btAgregar;

    private static final String CERO = "0";
    private static final String RAYA = "-";
    private static final String DOS_PUNTOS = ":";


    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    private String idMale;
    private List<String> listIdMale;

    private SwipeRefreshLayout refreshBirth;
    private View viewBirth;

    public BirthFragment(String [] listIdMale) {
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
        viewBirth = inflater.inflate(R.layout.fragment_list_birth, container, false);
        refreshBirth = viewBirth.findViewById(R.id.refresh_list_birth);
        refreshBirth.setOnRefreshListener(() -> {
            ((PigActivity)getActivity()).actualizarListBirth(refreshBirth);
        });

        fbAddBirth = viewBirth.findViewById(R.id.fb_add_birth_female);
        ((PigActivity)getActivity()).setSearch("Birth");
        noBirth = viewBirth.findViewById(R.id.tv_nobirth);

        noBirth.setText(listBirth.size() + " Parto(s) encontrado(s)");

        birthAdapter = new BirthAdapter(listBirth, getContext());
        recyclerBirth = viewBirth.findViewById(R.id.recyclerBirth);
        recyclerBirth.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerBirth.setAdapter(birthAdapter);


        fbAddBirth.setOnClickListener(view -> {
            final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
            View viewDialog = getLayoutInflater().inflate(R.layout.add_birth, null);

            spMale = viewDialog.findViewById(R.id.sp_male);
            etMostrarFecha = viewDialog.findViewById(R.id.et_mostrar_fecha);
            etBabiesNumber = viewDialog.findViewById(R.id.et_babies_number);
            etMummyNumber = viewDialog.findViewById(R.id.et_mummy_number);
            etDeadNumber = viewDialog.findViewById(R.id.et_number_dead);
            btCancelar = viewDialog.findViewById(R.id.bt_cancelar);
            btAgregar = viewDialog.findViewById(R.id.bt_agregar);
            ibObtenerFecha = viewDialog.findViewById(R.id.ib_obtener_fecha);


            adapterSpinnerMale = new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_spinner_item, listIdMale);
            adapterSpinnerMale.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


            spMale.setAdapter(adapterSpinnerMale);

            spMale.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if(position > 0)
                        idMale = (String) parent.getSelectedItem();
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }
            });

            ibObtenerFecha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatePickerDialog recogerFecha = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                            final int mesActual = month + 1;
                            //Formateo el día obtenido: antepone el 0 si son menores de 10
                            String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                            //Formateo el mes obtenido: antepone el 0 si son menores de 10
                            String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                            //Muestro la fecha con el formato deseado
                            etMostrarFecha.setText(year + RAYA + mesFormateado + RAYA + diaFormateado);
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
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });

            btAgregar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String fecha = etMostrarFecha.getText().toString();
                    short noBabies = Short.valueOf(etBabiesNumber.getText().toString());
                    short noMummy = Short.valueOf(etMummyNumber.getText().toString());
                    short noDead = Short.valueOf(etDeadNumber.getText().toString());
                    Birth birth  = new Birth(Short.valueOf(idMale), fecha, noBabies, noMummy, noDead);
                    ((PigActivity)getContext()).agregarParto(birth, alertDialog);
                }
            });



            alertDialog.show();
        });

        return viewBirth;
    }

    public void setListBirth(List<Birth> listBirth){
        this.listBirth = new ArrayList<>(listBirth);
    }

    public BirthAdapter getBirthAdapter(){
        return birthAdapter;
    }

    public void notificarAdapter() {
        noBirth.setText(listBirth.size() + " Parto(s) encontrado(s)");

        birthAdapter = new BirthAdapter(listBirth, getContext());
        recyclerBirth = viewBirth.findViewById(R.id.recyclerBirth);
        recyclerBirth.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerBirth.setAdapter(birthAdapter);
    }
}
