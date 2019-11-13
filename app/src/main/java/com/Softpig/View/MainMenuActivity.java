package com.Softpig.View;

import android.content.Intent;
import android.os.Bundle;

import com.Softpig.Model.Employee;
import com.Softpig.Model.Installation;
import com.Softpig.Presenter.MainMenuPresenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import com.Softpig.View.fragment.AboutFragment;
import com.Softpig.View.fragment.DictionaryFragment;
import com.Softpig.View.fragment.ErrorFragment;
import com.Softpig.View.fragment.RaceFragment;
import com.Softpig.View.fragment.ToolFragment;
import com.Softpig.View.fragment.EmployeeFragment;
import com.Softpig.View.fragment.InstallationFragment;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.Softpig.R;
import com.Softpig.View.fragment.DashBoardFragment;
import com.Softpig.View.fragment.PigFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity  implements  NavigationView.OnNavigationItemSelectedListener{

    private Employee user;
    private PigFragment pigFragment;
    private EmployeeFragment employeeFragment;
    private MainMenuPresenter mainMenuPresenter;
    private ToolFragment toolFragment;
    private DashBoardFragment dashBoardFragment;
    private RaceFragment raceFragment;
    private DictionaryFragment dictionaryFragment;
    private ErrorFragment errorFragment;
    private BottomNavigationView bottomNavigationView;
    private DrawerLayout drawer;
    private Toast notificacion;
    private InstallationFragment installationFragment;
    private Toolbar toolbar;
    private ArrayList<Installation> listInstallations = new ArrayList<>();
    private MenuItem searchItem;
    private MenuInflater menuInflater;
    private SearchView searchView;
    private Bundle datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contaniner_main_menu);


        datos = this.getIntent().getExtras();
        user = (Employee) datos.getSerializable("empleado");
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        if(savedInstanceState == null){
            mainMenuPresenter = new MainMenuPresenter(this);
            dashBoardFragment = new DashBoardFragment();
            presentarFragment("dashboard");
            employeeFragment = new EmployeeFragment();
            installationFragment = new InstallationFragment();
            errorFragment = new ErrorFragment();
            dictionaryFragment = new DictionaryFragment();
            notificacion = new Toast(this);
            toolFragment = new ToolFragment();
            raceFragment = new RaceFragment();
            pigFragment = new PigFragment();
        }

        bottomNavigationView = findViewById(R.id.bottombar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);


    }

    private void presentarFragment(String titleFragment) {
        switch (titleFragment){
            case "dashboard": mainMenuPresenter.presentarDashboard(this, dashBoardFragment);
                break;
                default: break;
        }
    }

    /**
     * Este metodo controla las opciones de la barra inferior
     */
    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    switch (menuItem.getItemId()){
                        case R.id.ic_home:
                            searchItem.setVisible(false);
                            getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, dashBoardFragment).commit();
                            break;
                        case R.id.ic_pig:
                            searchItem.setVisible(true);
                            //getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, pigFragment).commit();
                            mainMenuPresenter.inflarPigFragment(MainMenuActivity.this,pigFragment);
                            break;
                        case R.id.ic_medicine:
                            notificacion.cancel();
                            notificacion = notificacion.makeText(MainMenuActivity.this, "Función aún no disponible...", Toast.LENGTH_SHORT);
                            notificacion.show();
                            break;
                        case R.id.ic_alert:
                            notificacion.cancel();
                            notificacion = notificacion.makeText(MainMenuActivity.this, "Función aún no disponible...", Toast.LENGTH_SHORT);
                            notificacion.show();

                            break;
                        default:
                            return false;
                    }
                    return true;
                }
            };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.navigation, menu);

        searchItem = menu.findItem(R.id.action_search);
        searchItem.setVisible(false);

        searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setQueryHint(getText(R.string.searchEmployee));



        return true;
    }

    /**
     * Metodo que controla los controles de la navegación izquierda
     * @param menuItem Item seleccionado
     * @return True: acción realizada correctamente
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_female:
                mainMenuPresenter.inflarFemalesFragment(this);
                break;
            case R.id.nav_male:
                mainMenuPresenter.inflarMalesFragment(this);
                break;
            case R.id.nav_race:
                mainMenuPresenter.inflarRacesFragment(this, raceFragment);
                break;
            case R.id.nav_dictionary:
                getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, dictionaryFragment).commit();
                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, new AboutFragment()).commit();
                break;
            case R.id.nav_report:
                //getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, reportFragment).commit();
                break;
            case R.id.nav_out:
                super.onBackPressed();
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * metodo que se comunica con el presetador para abrir un fragment
     * @param stringFragment
     */
    public void controllerFragment(String stringFragment){

        switch (stringFragment){

            case "Employees":
                mainMenuPresenter.inflarEmployeesFragment(this, employeeFragment);
                break;
            case "Tools":

                mainMenuPresenter.inflarArticlesFragment(this, toolFragment);
                break;

            case "Installations":
                mainMenuPresenter.inflarInstallationsFragment(this, installationFragment);
                break;

            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, errorFragment).commit();
                break;

        }


    }

    public void inflarFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, fragment).commit();
    }

    public void setTitleTolbar(String title){
        this.toolbar.setTitle(title);
    }

    public void iniciarProfileActivity(Employee employee) {
        Intent i = new Intent();
        i.setClass(this,ProfileActivity.class);
        i.putExtra("Empleado", employee);
        startActivity(i);
    }

    /*public void modificar(final String fragmentSearch) {
        searchView.setQueryHint(getText(R.string.searchEmployee));
        searchItem.setVisible(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                switch (fragmentSearch){
                    case "employee": employeeFragment.employeeAdapter.getFilter().filter(newText); break;

                }

                return  false;
            }
        });
    }*/


    //código para quitar las barras que de navegación de android en algunas versiones
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

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
                    case "Employee": employeeFragment.employeeAdapter.getFilter().filter(newText);
                        break;
                    case  "Installation": installationFragment.getInstallationAdapter().getFilter().filter(newText);
                        break;
                    case "Tool": toolFragment.getToolAdapter().getFilter().filter(newText);
                        break;
                    case "Race": raceFragment.getRaceAdapter().getFilter().filter(newText);


                }

                return  false;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            this.moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void eliminarArticulo(int position, String article) {
        mainMenuPresenter.eliminarArticulo(this, position, article);
    }
}