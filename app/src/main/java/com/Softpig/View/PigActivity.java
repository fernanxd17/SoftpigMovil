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

    private RecyclerView recyclerPig;
    private PigPresenter pigPresenter;
    private PigAdapter pigAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pigs);
        pigPresenter = new PigPresenter();
        recyclerPig = findViewById(R.id.recyclerPig);
        recyclerPig.setLayoutManager(new LinearLayoutManager(this));
        pigAdapter = new PigAdapter(pigPresenter.getPigs());
        recyclerPig.setAdapter(pigAdapter);
    }

}
