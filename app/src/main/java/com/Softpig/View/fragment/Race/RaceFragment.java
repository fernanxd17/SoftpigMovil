package com.Softpig.View.fragment.Race;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Softpig.Model.Race;
import com.Softpig.Presenter.Adapters.RaceAdapter;
import com.Softpig.Presenter.RacePresenter;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RaceFragment extends Fragment {

    private RecyclerView recyclerRace;
    private static RacePresenter racePresenter;
    private static RaceAdapter raceAdapter;
    private ArrayList<Race> listRaces;
    private static View viewRace;
    private static boolean consultando = true;

    public RaceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainMenuActivity) getActivity()).setTitleTolbar("Razas");
        racePresenter = new RacePresenter(this);
        raceAdapter = new RaceAdapter(listRaces);

        racePresenter.getRaces(getContext(), listRaces); //si no funciona, usted es el problema, ok?
        while(consultando){

        }


        if(listRaces == null){

        }
        viewRace =  inflater.inflate(R.layout.fragment_races, container, false);


        recyclerRace = viewRace.findViewById(R.id.recyclerRace);
        recyclerRace.setLayoutManager(new LinearLayoutManager(getContext()));

        //recyclerRace.setAdapter(raceAdapter); //cuando ya funcione la api, hay que verificar si carga los cardview sin esta linea, en caso de que no funcione probrar des-comentando a linea
        llenarDatosAdapter();
        if(listRaces == null){
            System.out.println("null");
            viewRace =  inflater.inflate(R.layout.fragment_error, container, false);
            return viewRace;
        }else if(listRaces.size() == 0){
            //Agregar el dialogBuilder
            return null;
        }

        return viewRace;
    }



    private void inflarVista(int idVista){
        switch (idVista){
            case 1: //Vista normal

                break;
        }
    }

    public void setConsultado(boolean consultando){
        this.setConsultado(consultando);
    }

    public boolean notificarError(){
        ((MainMenuActivity)getActivity()).inflateFragment(1);
        return true;
    }
}
