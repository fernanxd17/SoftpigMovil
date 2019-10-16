package com.Softpig.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.Softpig.Model.Pig;
import com.Softpig.Presenter.Adapters.PigAdapter;
import com.Softpig.Presenter.PigPresenter;
import com.Softpig.R;

import java.util.ArrayList;

public class PigActivity extends AppCompatActivity {

    ArrayList<Pig> listPig;
    RecyclerView recyclerPig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pigs);
        listPig = new ArrayList<Pig>();
        recyclerPig = findViewById(R.id.recyclerPig);
        recyclerPig.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        showPigs();
        PigAdapter PigAdapter = new PigAdapter(listPig);
        recyclerPig.setAdapter(PigAdapter);
    }

    public void showPigs(){
        listPig = PigPresenter.getPigs();
        PigAdapter pigAdapter = new PigAdapter(listPig);
        this.recyclerPig.setAdapter(pigAdapter);
    }
}
