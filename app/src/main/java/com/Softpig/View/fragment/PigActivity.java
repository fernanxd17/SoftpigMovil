package com.Softpig.View.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.Softpig.Model.Pig;
import com.Softpig.R;

public class PigActivity extends AppCompatActivity {

    private Bundle bundle;
    private Pig pig;
    private String fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_pig);
        bundle = this.getIntent().getExtras();
        this.pig = (Pig)bundle.get("pig");
        this.fragment = bundle.getString("fragment");
        inflarFragment();


    }

    private void inflarFragment() {
        switch (fragment){
            case "pig": getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsPigs, new InfoPigFragment(this.pig)).commit();
                break;
        }
    }
}
