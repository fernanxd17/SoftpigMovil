package com.Softpig.View.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.Softpig.Model.Female;
import com.Softpig.Model.Male;
import com.Softpig.Model.Pig;
import com.Softpig.Presenter.PigPresenter;
import com.Softpig.R;

public class PigActivity extends AppCompatActivity {

    private Bundle bundle;
    private Pig pig;
    private Male male;
    private Female female;
    private String fragment;
    private InfoPigFragment infoPigFragment;
    private InfoMaleFragment infoMaleFragment;
    private InfoFemaleFragment infoFemaleFragment;
    private PigPresenter pigPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_pig);
        bundle = this.getIntent().getExtras();
        this.fragment = bundle.getString("fragment");
        switch (fragment){
            case "Pig":
                this.pig = (Pig)bundle.get("Pig");
                this.infoPigFragment = new InfoPigFragment(pig);
                break;
            case "Male":
                this.male = (Male)bundle.get("Male");
                this.infoMaleFragment = new InfoMaleFragment(male);
                break;
            case "Female": 
                this.female = (Female)bundle.get("Female");
                this.infoFemaleFragment = new InfoFemaleFragment(female);
                break;
        }


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
