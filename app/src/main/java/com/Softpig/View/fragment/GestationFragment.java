package com.Softpig.View.fragment;


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
import android.widget.Spinner;
import android.widget.TextView;

import com.Softpig.Model.PeriodGestation;
import com.Softpig.Presenter.Adapters.GestationAdapter;
import com.Softpig.R;
import com.Softpig.View.PigActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
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
    private String idMale;
    private List<String> listIdMale;

    public GestationFragment() {
        listIdMale = new ArrayList<>();
        idMale = "";
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

    public void setListIdMale(List<String> listIdMale){
        this.listIdMale = listIdMale;
    }
}
