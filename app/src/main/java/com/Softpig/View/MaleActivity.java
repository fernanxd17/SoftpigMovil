package com.Softpig.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.Softpig.Model.Male;
import com.Softpig.Presenter.Adapters.MaleAdapter;
import com.Softpig.R;

import java.util.ArrayList;

public class MaleActivity extends AppCompatActivity {

    ArrayList<Male> listMales;
    RecyclerView recyclerMale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.males);
        listMales = new ArrayList<Male>();
        recyclerMale = findViewById(R.id.recyclerMale);
        recyclerMale.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        showMales();
        MaleAdapter maleAdapter = new MaleAdapter(listMales);
        recyclerMale.setAdapter(maleAdapter);
    }

    private void showMales() {
    }


}
