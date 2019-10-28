package com.Softpig.View.fragment.Race;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softpig.Presenter.Adapters.RaceAdapter;
import com.Softpig.Presenter.RacePresenter;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class RaceFragment extends Fragment {

    private RecyclerView recyclerRace;
    private RacePresenter racePresenter;
    private RaceAdapter raceAdapter;
    public RaceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_races, container, false);
        ((MainMenuActivity)getActivity()).setTitleTolbar("Razas");
        racePresenter = new RacePresenter();
        recyclerRace = view.findViewById(R.id.recyclerRace);
        recyclerRace.setLayoutManager(new LinearLayoutManager(getContext()));
        raceAdapter = new RaceAdapter(racePresenter.getRaces());
        recyclerRace.setAdapter(raceAdapter);

        return view;
    }

}
