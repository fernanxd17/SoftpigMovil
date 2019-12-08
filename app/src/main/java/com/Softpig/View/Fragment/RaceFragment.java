package com.Softpig.View.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softpig.Model.Race;
import com.Softpig.Presenter.Adapters.RaceAdapter;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;

import java.util.ArrayList;

public class RaceFragment extends Fragment {

    private RecyclerView recyclerRace;
    private RaceAdapter raceAdapter;
    private ArrayList<Race> listRaces;
    private View viewRace;
    private TextView tvInfo;
    private SwipeRefreshLayout refreshRace;


    public RaceFragment(){

    }

    public RaceAdapter getRaceAdapter(){
        return raceAdapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewRace =  inflater.inflate(R.layout.fragment_list_races, container, false);
        refreshRace = viewRace.findViewById(R.id.refresh_list_race);
        refreshRace.setOnRefreshListener(() -> {
            ((MainMenuActivity)getActivity()).actualizarListaRazas(refreshRace);
        });
        ((MainMenuActivity) getActivity()).setTitleTolbar("Razas");
        ((MainMenuActivity)getActivity()).setSearch("Race");

        tvInfo = viewRace.findViewById(R.id.tv_info);
        tvInfo.setText(listRaces.size() + " Raza(s) encontrada(s)");

        raceAdapter = new RaceAdapter(this.listRaces);
        recyclerRace = viewRace.findViewById(R.id.recyclerRace);
        recyclerRace.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerRace.setAdapter(raceAdapter);
        return viewRace;
    }

    public void setListRace(ArrayList<Race> listRaces) {
        this.listRaces = listRaces;
    }

    public void notificarAdapter() {
        tvInfo.setText(listRaces.size() + " Raza(s) encontrada(s)");

        raceAdapter = new RaceAdapter(this.listRaces);
        recyclerRace = viewRace.findViewById(R.id.recyclerRace);
        recyclerRace.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerRace.setAdapter(raceAdapter);
    }
}