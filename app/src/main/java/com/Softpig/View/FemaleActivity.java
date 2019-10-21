package com.Softpig.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.Softpig.Model.Female;
import com.Softpig.Presenter.Adapters.FemaleAdapter;
import com.Softpig.Presenter.FemalePresenter;
import com.Softpig.R;

import java.util.ArrayList;

public class FemaleActivity extends AppCompatActivity {

    private RecyclerView recyclerFemale;
    private FemalePresenter femalePresenter;
    private FemaleAdapter femaleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.females);
        femalePresenter = new FemalePresenter();
        recyclerFemale = findViewById(R.id.recyclerFemale);
        recyclerFemale.setLayoutManager(new LinearLayoutManager(this));
        FemaleAdapter femaleAdapter = new FemaleAdapter(FemalePresenter.getFemale());
        recyclerFemale.setAdapter(femaleAdapter);
    }
}
