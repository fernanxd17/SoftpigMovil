package com.Softpig.View.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softpig.Model.Race;
import com.Softpig.Presenter.Adapters.RaceAdapter;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RaceFragment extends Fragment {

    private RecyclerView recyclerRace;
    private static RaceAdapter raceAdapter;
    private ArrayList<Race> listRaces;
    private static View viewRace;
    private TextView tvInfo;


    public RaceFragment(ArrayList<Race> listRaces){
        this.listRaces = listRaces;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewRace =  inflater.inflate(R.layout.fragment_races, container, false);
        ((MainMenuActivity) getActivity()).setTitleTolbar("Razas");

        if(listRaces.isEmpty()){
            tvInfo = viewRace.findViewById(R.id.tv_info);
            tvInfo.setText("No existen razas registradas.");
            return viewRace;
        }
        raceAdapter = new RaceAdapter(this.listRaces);
        recyclerRace = viewRace.findViewById(R.id.recyclerRace);
        recyclerRace.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerRace.setAdapter(raceAdapter); //cuando ya funcione la api, hay que verificar si carga los cardview sin esta linea, en caso de que no funcione probrar des-comentando a linea

        return viewRace;
    }
}