package com.Softpig.View;

import android.content.Intent;
import android.os.Bundle;

import com.Softpig.Model.Alarm;
import com.Softpig.Model.Employee;
import com.Softpig.Model.Female;
import com.Softpig.Model.Installation;
import com.Softpig.Model.Male;
import com.Softpig.Model.Pig;
import com.Softpig.Presenter.MainMenuPresenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import com.Softpig.View.fragment.AboutFragment;
import com.Softpig.View.fragment.AlarmFragment;
import com.Softpig.View.fragment.DictionaryFragment;
import com.Softpig.View.fragment.ErrorFragment;
import com.Softpig.View.fragment.FemaleFragment;
import com.Softpig.View.fragment.HeatFragment;
import com.Softpig.View.fragment.MaleFragment;
import com.Softpig.View.fragment.MedicineFragment;
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

import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.Softpig.R;
import com.Softpig.View.fragment.DashBoardFragment;
import com.Softpig.View.fragment.PigFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity  implements  NavigationView.OnNavigationItemSelectedListener{

    private Employee user;
    private PigFragment pigFragment;
    private FemaleFragment femaleFragment;
    private EmployeeFragment employeeFragment;
    private MaleFragment maleFragment;
    private MainMenuPresenter mainMenuPresenter;
    private ToolFragment toolFragment;
    private DashBoardFragment dashBoardFragment;
    private RaceFragment raceFragment;
    private DictionaryFragment dictionaryFragment;
    private ErrorFragment errorFragment;
    private MedicineFragment medicineFragment;
    private AlarmFragment alarmFragment;
    private HeatFragment heatFragment;
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

    public static String rol;
    private TextView tvNameIzq, tvEmailIzq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contaniner_main_menu);


        datos = this.getIntent().getExtras();
        user = new Employee();
        user = (Employee) datos.getSerializable("Empleado");
        rol = user.getRole();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        tvNameIzq = headerView.findViewById(R.id.name_izq);
        tvEmailIzq = headerView.findViewById(R.id.email_izq);
        tvEmailIzq.setText(user.getEmail());
        tvNameIzq.setText(user.getFirstName() + " "+ user.getLastName());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        bottomNavigationView = findViewById(R.id.bottombar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);


        if(savedInstanceState == null){
            alarmFragment = new AlarmFragment();
            mainMenuPresenter = new MainMenuPresenter();
            dashBoardFragment = new DashBoardFragment();
            employeeFragment = new EmployeeFragment();
            installationFragment = new InstallationFragment();
            errorFragment = new ErrorFragment();
            dictionaryFragment = new DictionaryFragment();
            notificacion = new Toast(this);
            toolFragment = new ToolFragment(false);
            raceFragment = new RaceFragment();
            pigFragment = new PigFragment();
            femaleFragment = new FemaleFragment();
            maleFragment = new MaleFragment();
            medicineFragment = new MedicineFragment();
            presentarFragment("dashboard", null,true);
        }


    }

    private void presentarFragment(String titleFragment, SwipeRefreshLayout refrescarDashboard, boolean inflar) {
        switch (titleFragment){
            case "dashboard": mainMenuPresenter.presentarDashboard(this, dashBoardFragment,refrescarDashboard,  inflar);
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
                            mainMenuPresenter.inflarPigFragment(MainMenuActivity.this,pigFragment, null, true);
                            break;
                        case R.id.ic_medicine:
                            searchItem.setVisible(true);
                            mainMenuPresenter.inflarMedicineFragment(MainMenuActivity.this, medicineFragment, null, true);
                            break;
                        case R.id.ic_alert:
                            searchItem.setVisible(true);
                            mainMenuPresenter.inflarAlarmFragment(MainMenuActivity.this, alarmFragment, user.getIdEmployee(), null, true);

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
        searchView.setQueryHint("Buscar...");
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
                mainMenuPresenter.inflarFemalesFragment(this, femaleFragment, null);
                break;
            case R.id.nav_male:
                mainMenuPresenter.inflarMalesFragment(this,maleFragment, null, true);
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
                mainMenuPresenter.inflarEmployeesFragment(this, employeeFragment, null, true);
                break;
            case "Tools":
                mainMenuPresenter.inflarToolsFragment(this, toolFragment, null, true);
                break;
            case "Installations":
                mainMenuPresenter.inflarInstallationsFragment(this, installationFragment, null, true);
                break;
            case "Heats":
                mainMenuPresenter.inflarHeatsFragment(this, heatFragment);
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
                        break;
                    case "Male": maleFragment.getMaleAdapter().getFilter().filter(newText);
                        break;
                    case "Female": femaleFragment.getFemaleAdapter().getFilter().filter(newText);
                        break;
                    case "Pig": pigFragment.getPigAdapter().getFilter().filter(newText);
                        break;
                    case "Medicine": medicineFragment.getMedicineAdapter().getFilter().filter(newText);
                        break;
                    case "Alarm": alarmFragment.getAlarmAdapter().getFilter().filter(newText);
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

    public void iniciarPigActivityMale(final Male male) {
        Intent i = new Intent();
        i.setClass(this, PigActivity.class);
        i.putExtra("Male", male);
        i.putExtra("fragment", "Male");
        startActivity(i);
    }

    public void iniciarPigActivityPig(final Pig pig){
        Intent i = new Intent();
        i.setClass(this, PigActivity.class);
        i.putExtra("Pig", pig);
        i.putExtra("fragment", "Pig");
        startActivity(i);
    }

    public void iniciarPigActivityFemale(final Female female) {
        mainMenuPresenter.iniciarPigActivityFemale(this, female);
    }

    public void eliminarExistenciaMedicina(short idMedicine) {
        mainMenuPresenter.eliminarExistenciasMedicina(this, idMedicine);
    }

    public void eliminarAlarma() {
        mainMenuPresenter.eliminarAlarmPerson(this, user.getIdEmployee());
    }

    public void crearAlerta(String fecha, String hora, String etiq) {
        mainMenuPresenter.addAlarm(this,user.getIdEmployee(), fecha, hora, etiq);
    }

    public boolean actualizarValoresDashboard(final SwipeRefreshLayout refreshDashboard) {
        presentarFragment("dashboard", refreshDashboard, false);
        return true;
    }

    public void actualizarValoresListPig(final SwipeRefreshLayout refreshListPig) {
        mainMenuPresenter.inflarPigFragment(this,pigFragment, refreshListPig, false);
    }

    public void actualizarListaMedicinas(final SwipeRefreshLayout refrescarListMedicine) {
        mainMenuPresenter.inflarMedicineFragment(this, medicineFragment, refrescarListMedicine, false);
    }

    public void actualizarListaAlarmas(final SwipeRefreshLayout refreshListAlarm) {
        mainMenuPresenter.inflarAlarmFragment(this, alarmFragment, user.getIdEmployee(), refreshListAlarm, false);
    }

    public void actualizarListEmployee(final SwipeRefreshLayout refreshListEmployee) {
        mainMenuPresenter.inflarEmployeesFragment(this, employeeFragment, refreshListEmployee, false);
    }

    public void actualizarListInstallation(final SwipeRefreshLayout refreshListInstallation) {
        mainMenuPresenter.inflarInstallationsFragment(this, installationFragment, refreshListInstallation, false);
    }

    public void actualizarListaTool(SwipeRefreshLayout refreshListTool) {
        mainMenuPresenter.inflarToolsFragment(this, toolFragment, refreshListTool, false);
    }

    public void actualizarListaMale(SwipeRefreshLayout refreshListMale) {
        mainMenuPresenter.inflarMalesFragment(this, maleFragment, refreshListMale,false);
    }

    public void actualizarListaFemale(final SwipeRefreshLayout refreshListFemale) {
        mainMenuPresenter.inflarFemalesFragment(this, femaleFragment, refreshListFemale);
    }
}