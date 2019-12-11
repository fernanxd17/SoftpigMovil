package com.Softpig.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import com.Softpig.Model.Birth;
import com.Softpig.Model.ExamMale;
import com.Softpig.Model.Female;
import com.Softpig.Model.Heat;
import com.Softpig.Model.Male;
import com.Softpig.Model.PeriodGestation;
import com.Softpig.Model.Pig;
import com.Softpig.Presenter.PigPresenter;
import com.Softpig.R;
import com.Softpig.View.Fragment.BirthFragment;
import com.Softpig.View.Fragment.ErrorFragment;
import com.Softpig.View.Fragment.ExamMaleListFragment;
import com.Softpig.View.Fragment.GestationFragment;
import com.Softpig.View.Fragment.HeatFragment;
import com.Softpig.View.Fragment.InfoFemaleFragment;
import com.Softpig.View.Fragment.InfoMaleFragment;
import com.Softpig.View.Fragment.InfoPigFragment;
import com.Softpig.View.Fragment.MaleExamFragment;

import java.util.List;

public class PigActivity extends AppCompatActivity{

    private Bundle bundle;
    private Pig pig;
    private Male male;
    private Female female;
    private String fragment;
    private MenuInflater menuInflater;
    private MaleExamFragment maleExamFragment;
    private InfoPigFragment infoPigFragment;
    private InfoMaleFragment infoMaleFragment;
    private InfoFemaleFragment infoFemaleFragment;
    private BirthFragment birthFragment;
    private HeatFragment heatFragment;
    private PigPresenter pigPresenter;
    private ExamMaleListFragment examMaleListFragment;
    private GestationFragment gestationFragment;
    private MenuItem searchItem;
    private SearchView searchView;
    private String [] listIdMale;

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
                listIdMale = (String[]) bundle.get("listIdMale");
                this.infoFemaleFragment = new InfoFemaleFragment(female, listIdMale);
                break;
        }

        heatFragment = new HeatFragment();

        pigPresenter = new PigPresenter();
        maleExamFragment = new MaleExamFragment();
        examMaleListFragment = new ExamMaleListFragment();
        inflarFragment(fragment);
    }

    public void inflarFragment(String fragment) {
        switch (fragment){
            case "Pig": getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsPigs, infoPigFragment).addToBackStack(null).commit();
                break;
            case "Male": getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsPigs, infoMaleFragment).addToBackStack(null).commit();
                break;
            case "Female":
                gestationFragment = new GestationFragment((String [])bundle.get("listIdMale"));
                birthFragment = new BirthFragment((String []) bundle.get("listIdMale"));
                heatFragment = new HeatFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsPigs, infoFemaleFragment).addToBackStack(null).commit();
                break;
            case "Birth":
                getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsPigs, birthFragment).addToBackStack(null).commit();
                break;
            case "Heat": getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsPigs, heatFragment).addToBackStack(null).commit();
                break;
            case "Gestation": getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsPigs, gestationFragment).addToBackStack(null).commit();
                break;
            case "ExamMaleList": getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsPigs, examMaleListFragment).addToBackStack(null).commit();
                break;
            case "MaleExam": getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsPigs, maleExamFragment).addToBackStack(null).commit();
                break;
            case "Error": getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsPigs, new ErrorFragment()).addToBackStack(null).commit();
                break;
        }
    }

    public void darBajaPig(short idPig) {
        pigPresenter.darDeBajaPig(idPig, this);
    }


    public void desasignarMale(final short idMale) {
        pigPresenter.desasignarMale(idMale, infoMaleFragment);
    }

    public void desasignarFemale(final short idFemale) {
        pigPresenter.desasignarFemale(idFemale, infoFemaleFragment);
    }

    public void inflarFragmentPartos(short idFemale) {
        pigPresenter.presentarBirthFragment(this, birthFragment, idFemale, null);
    }

    public void inflarFragmentGestacion(short idFemale) {
        pigPresenter.presentarGestacionFragment(this, gestationFragment, idFemale, null);

    }

    public void inflarFragmentCelos(short idFemale) {
        pigPresenter.presentarCelosFragment(this, heatFragment,idFemale, null);
    }

    public void setListBirth(List<Birth> listBirth) {
        birthFragment.setListBirth(listBirth);
    }



    public void verExamanesMale(short idMale) {
        pigPresenter.presentarExamanesFragment(this, examMaleListFragment, idMale, null);
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
                    case "MaleExam": examMaleListFragment.getMaleExamAdapter().getFilter().filter(newText);
                }
                return  false;
            }
        });
    }


    public void inflarMaleExam(ExamMale examMale) {
        maleExamFragment.setMaleExamFragment(examMale);
        inflarFragment("MaleExam");
    }

    public void agregarGestacion(final PeriodGestation periodGestation, final AlertDialog alertDialog) {
        periodGestation.setIdFemale(female.getIdFemale());
        pigPresenter.agregarGestation(gestationFragment,periodGestation, alertDialog);
    }

    public void modificarExamMale(short idExamMale, String result, String date) {
        pigPresenter.addExamReport(this, male.getIdMale(), idExamMale, result, date);
    }

    public void agregarParto(Birth birth, final AlertDialog alertDialog) {
        birth.setIdFemale(female.getIdFemale());
        pigPresenter.agregarBirth(this, birth, alertDialog);
    }

    public void agregarCelo(final Heat heat, final AlertDialog alertDialog) {
        heat.setIdFemale(female.getIdFemale());
        pigPresenter.agregarHeat(heatFragment, heat, alertDialog);
    }

    public void actualizarListMaleExam(final SwipeRefreshLayout refreshListExamMale) {
            pigPresenter.presentarExamanesFragment(this,examMaleListFragment, male.getIdPig(), refreshListExamMale);
    }

    public void actualizarListHeat(final SwipeRefreshLayout refreshListHeat) {
        pigPresenter.presentarCelosFragment(this, heatFragment,female.getIdFemale(), refreshListHeat);
    }


    public void actualizarListGestation(final SwipeRefreshLayout refreshGestation) {
        pigPresenter.presentarGestacionFragment(this, gestationFragment, female.getIdFemale(), refreshGestation);
    }

    public void actualizarListBirth(final SwipeRefreshLayout refreshBirth) {
        pigPresenter.presentarBirthFragment(this, birthFragment, female.getIdFemale(), refreshBirth);
    }
}