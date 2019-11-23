package com.Softpig.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import com.Softpig.Model.Birth;
import com.Softpig.Model.Female;
import com.Softpig.Model.Heat;
import com.Softpig.Model.Male;
import com.Softpig.Model.PeriodGestation;
import com.Softpig.Model.Pig;
import com.Softpig.Presenter.PigPresenter;
import com.Softpig.R;
import com.Softpig.View.fragment.BirthFragment;
import com.Softpig.View.fragment.ErrorFragment;
import com.Softpig.View.fragment.GestationFragment;
import com.Softpig.View.fragment.HeatFragment;
import com.Softpig.View.fragment.InfoFemaleFragment;
import com.Softpig.View.fragment.InfoMaleFragment;
import com.Softpig.View.fragment.InfoPigFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class PigActivity extends AppCompatActivity{

    private Bundle bundle;
    private Pig pig;
    private Male male;
    private Female female;
    private String fragment;
    private MenuInflater menuInflater;
    private InfoPigFragment infoPigFragment;
    private InfoMaleFragment infoMaleFragment;
    private InfoFemaleFragment infoFemaleFragment;
    private BirthFragment birthFragment;
    private HeatFragment heatFragment;
    private PigPresenter pigPresenter;
    private GestationFragment gestationFragment;
    private MenuItem searchItem;
    private SearchView searchView;

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

        heatFragment = new HeatFragment();
        gestationFragment = new GestationFragment();
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
            case "Heat": getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsPigs, heatFragment).commit();
                break;
            case "Gestation": getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsPigs, gestationFragment).commit();
                break;
            case "Error": getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsPigs, new ErrorFragment()).commit();
                break;
        }
    }

    public void darBajaPig(short idPig) {
        pigPresenter.darDeBajaPig(idPig, this);
    }


    public void desasignarMale(short idMale) {
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

    public void setListHeat(List<Heat> listHeat) {
        heatFragment.setListHeat(listHeat);
    }

    public void setListGestation(List<PeriodGestation> listGestation) {
        gestationFragment.setListGestation(listGestation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.navigation, menu);

        searchItem = menu.findItem(R.id.action_search);

        searchItem.setVisible(false);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setQueryHint("Buscar...");
        return true;
    }

    public void setSearch(final String fragment){
        searchItem.setVisible(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                switch (fragment){
                    case "Birth": birthFragment.getBirthAdapter().getFilter().filter(newText);
                        break;
                    case "Heat": heatFragment.getHeatAdapter().getFilter().filter(newText);
                        break;
                    case "Gestation": gestationFragment.getGestationAdapter().getFilter().filter(newText);
                        break;
                }
                return  false;
            }
        });
    }



}