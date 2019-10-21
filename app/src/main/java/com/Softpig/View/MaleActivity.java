package com.Softpig.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.Softpig.Model.Male;
import com.Softpig.Presenter.Adapters.MaleAdapter;
import com.Softpig.Presenter.MalePresenter;
import com.Softpig.R;

import java.util.ArrayList;

public class MaleActivity extends AppCompatActivity {

    private RecyclerView recyclerMale;
    private MalePresenter malePresenter;
    private MaleAdapter maleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.males);
        malePresenter = new MalePresenter();
        recyclerMale = findViewById(R.id.recyclerMale);
        recyclerMale.setLayoutManager(new LinearLayoutManager(this));
        maleAdapter = new MaleAdapter(malePresenter.getMales());
        recyclerMale.setAdapter(maleAdapter);
    }

}
