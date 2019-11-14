package com.Softpig.View.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.Softpig.Model.Pig;
import com.Softpig.Presenter.PigPresenter;
import com.Softpig.R;

public class PigActivity extends AppCompatActivity {

    private Bundle bundle;
    private Pig pig;
    private String fragment;
    private InfoPigFragment infoPigFragment;
    private PigPresenter pigPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_pig);
        bundle = this.getIntent().getExtras();
        this.pig = (Pig)bundle.get("Pig");
        System.out.println("Sexo: ");
        System.out.println(pig.getSex());
        this.fragment = bundle.getString("fragment");

            this.infoPigFragment = new InfoPigFragment(pig);
            pigPresenter = new PigPresenter();


        inflarFragment();


    }

    private void inflarFragment() {
        switch (fragment){
            case "pig": getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsPigs, infoPigFragment).commit();
                break;
        }
    }

    public void darBajaPig(short idPig) {
        pigPresenter.darDeBajaPig(idPig, this);
    }
}
