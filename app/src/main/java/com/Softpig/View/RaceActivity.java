package com.Softpig.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.Softpig.Model.Race;
import com.Softpig.Presenter.Adapters.EmployeeAdapter;
import com.Softpig.Presenter.Adapters.RaceAdapter;
import com.Softpig.Presenter.RacePresenter;
import com.Softpig.R;

import java.util.ArrayList;

public class RaceActivity extends AppCompatActivity {

    ArrayList<Race> listRaces;
    RecyclerView recyclerRace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.races);
        listRaces = new ArrayList<Race>();
        recyclerRace = findViewById(R.id.recyclerRace);
        recyclerRace.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        showRaces();
        RaceAdapter raceAdapter = new RaceAdapter(listRaces);
        recyclerRace.setAdapter(raceAdapter);
    }

    private void showRaces() {
        listRaces = RacePresenter.getRaces();
        RaceAdapter raceAdapter = new RaceAdapter(listRaces);
        this.recyclerRace.setAdapter(raceAdapter);
    }
}
