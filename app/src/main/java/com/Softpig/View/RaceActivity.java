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

    private RecyclerView recyclerRace;
    private RacePresenter racePresenter;
    private RaceAdapter raceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.races);
        racePresenter = new RacePresenter();
        recyclerRace = findViewById(R.id.recyclerRace);
        recyclerRace.setLayoutManager(new LinearLayoutManager(this));
        raceAdapter = new RaceAdapter(racePresenter.getRaces());
        recyclerRace.setAdapter(raceAdapter);
    }

}
