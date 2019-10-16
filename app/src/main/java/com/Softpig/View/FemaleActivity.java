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

    ArrayList<Female> listFemale;
    RecyclerView recyclerFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.females);
        listFemale = new ArrayList<Female>();
        recyclerFemale = findViewById(R.id.recyclerFemale);
        recyclerFemale.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        showFemale();
        FemaleAdapter femaleAdapter = new FemaleAdapter(listFemale);
        recyclerFemale.setAdapter(femaleAdapter);
    }

    public void showFemale(){
        listFemale = FemalePresenter.getFemale();
        FemaleAdapter femaleAdapter = new FemaleAdapter(listFemale);
        this.recyclerFemale.setAdapter(femaleAdapter);
    }
}
