package com.Softpig.View.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.Softpig.Model.Birth;
import com.Softpig.Model.Female;
import com.Softpig.Model.Male;
import com.Softpig.Model.Pig;
import com.Softpig.Presenter.PigPresenter;
import com.Softpig.R;

import java.util.List;

public class PigActivity extends AppCompatActivity {

    private Bundle bundle;
    private Pig pig;
    private Male male;
    private Female female;
    private String fragment;
    private InfoPigFragment infoPigFragment;
    private InfoMaleFragment infoMaleFragment;
    private InfoFemaleFragment infoFemaleFragment;
    private BirthFragment birthFragment;
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

        birthFragment = new BirthFragment();
        pigPresenter = new PigPresenter();
        inflarFragment(fragment);
    }

    public void inflarFragment(String fragment) {
        switch (fragment){
            case "Pig": getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsPigs, infoPigFragment).commit();
                break;
            case "Male": getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsPigs, infoMaleFragment).commit();
                break;
            case "Female": getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsPigs, infoFemaleFragment).commit();
                break;
            case "Birth": getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsPigs, birthFragment).commit();
                break;
            case "Error": getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsPigs, new ErrorFragment()).commit();
                break;
        }
    }

    public void darBajaPig(short idPig) {
        pigPresenter.darDeBajaPig(idPig, this);
    }


    public void desasignarMale(short idMale) {
        System.out.println("des - pig activity");
        pigPresenter.desasignarMale(idMale, this);
    }
    public void desasignarFemale(short idFemale) {
        pigPresenter.desasignarFemale(idFemale, this);

    }

    public void inflarFragmentPartos(short idFemale) {
        pigPresenter.presentarBirthFragment(this, idFemale);
    }

    public void inflarFragmentGestacion(short idFemale) {
        pigPresenter.presentarGestacionFragment(this, idFemale);
    }

    public void inflarFragmentCelos(short idFemale) {
        pigPresenter.presentarCelosFragment(this, idFemale);
    }

    public void setListBirth(List<Birth> listBirth) {
        birthFragment.setListBirth(listBirth);
    }


}
