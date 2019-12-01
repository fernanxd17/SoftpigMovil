package com.Softpig.View.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.Softpig.Model.Alarm;
import com.Softpig.Presenter.Adapters.AlarmAdapter;
import com.Softpig.Presenter.Adapters.MaleAdapter;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AlarmFragment extends Fragment {

    private RecyclerView recyclerAlarm;
    private AlarmAdapter alarmAdapter;
    private List<Alarm> listAlarm;
    private View viewAlarm;
    private TextView tvNoAlarm;
    private FloatingActionButton fbAddAlarm;

    private EditText etMostrarFecha, etMostrarHora, etEtiq;
    private ImageButton ibObtenerFecha, ibObtenerHora;
    private Button btCancelar, btAgregar;
    private static final String CERO = "0";
    private static final String RAYA = "-";
    private static final String DOS_PUNTOS = ":";
    private SwipeRefreshLayout refreshListAlarm;


    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);


    //Variables para obtener la hora hora
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);

    public AlarmFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewAlarm = inflater.inflate(R.layout.fragment_list_alarm, container, false);
        refreshListAlarm = viewAlarm.findViewById(R.id.refresh_list_alarm);
        refreshListAlarm.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ((MainMenuActivity)getContext()).actualizarListaAlarmas(refreshListAlarm);
            }
        });
        ((MainMenuActivity)getActivity()).setSearch("Alarm");
        if (listAlarm.isEmpty()){
            tvNoAlarm = viewAlarm.findViewById(R.id.tv_noAlarms);
            tvNoAlarm.setText("No existen alarmas");
        }
        alarmAdapter = new AlarmAdapter(this.listAlarm, getContext());
        recyclerAlarm = viewAlarm.findViewById(R.id.recyclerAlarms);
        recyclerAlarm.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerAlarm.setAdapter(alarmAdapter);

        fbAddAlarm = viewAlarm.findViewById(R.id.fb_add_alarm);
        fbAddAlarm.show();
        fbAddAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                View viewDialog = getLayoutInflater().inflate(R.layout.add_alarm, null);

                etMostrarFecha = viewDialog.findViewById(R.id.et_mostrar_fecha);
                etMostrarHora = viewDialog.findViewById(R.id.et_mostrar_hora);
                etEtiq = viewDialog.findViewById(R.id.et_etiquita_alam);
                ibObtenerFecha = viewDialog.findViewById(R.id.ib_obtener_fecha);
                ibObtenerHora = viewDialog.findViewById(R.id.ib_obtener_hora);
                btAgregar = viewDialog.findViewById(R.id.bt_agregar);
                btCancelar = viewDialog.findViewById(R.id.bt_cancelar);

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


                ibObtenerHora.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TimePickerDialog recogerHora = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                                String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                                String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);
                                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                                String AM_PM;
                                if(hourOfDay < 12) {
                                    AM_PM = "a.m.";
                                } else {
                                    AM_PM = "p.m.";
                                }
                                //Muestro la hora con el formato deseado
                                etMostrarHora.setText(horaFormateada + DOS_PUNTOS + minutoFormateado + " " + AM_PM);
                            }
                            //Estos valores deben ir en ese orden
                            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
                            //Pero el sistema devuelve la hora en formato 24 horas
                        }, hora, minuto, false);

                        recogerHora.show();
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
                        String etiq = etEtiq.getText().toString();
                        String hora = etMostrarHora.getText().toString();
                        String fecha = etMostrarFecha.getText().toString();
                        ((MainMenuActivity)getContext()).crearAlerta(fecha, hora, etiq);
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();


            }
        });



        return viewAlarm;
    }

    public void setListAlarm(ArrayList<Alarm> listAlarms) {
        this.listAlarm = listAlarms;
    }

    public AlarmAdapter getAlarmAdapter() {

        return alarmAdapter;
    }

    public void notificarAdapter() {
        if (listAlarm.isEmpty()){
            tvNoAlarm = viewAlarm.findViewById(R.id.tv_noAlarms);
            tvNoAlarm.setText("No existen alarmas");
        }
        alarmAdapter = new AlarmAdapter(this.listAlarm, getContext());
        recyclerAlarm = viewAlarm.findViewById(R.id.recyclerAlarms);
        recyclerAlarm.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerAlarm.setAdapter(alarmAdapter);
    }
}
