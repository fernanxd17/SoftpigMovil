package com.Softpig.View;

import android.os.Bundle;

import com.Softpig.Presenter.MainMenuPresenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;

import com.Softpig.View.fragment.About.AboutFragment;
import com.Softpig.View.fragment.Dictionary.DictionaryFragment;
import com.Softpig.View.fragment.Female.FemaleFragment;
import com.Softpig.View.fragment.Male.MaleFragment;
import com.Softpig.View.fragment.Profile.ProfileFragment;
import com.Softpig.View.fragment.Race.RaceFragment;
import com.Softpig.View.fragment.Report.ReportFragment;
import com.Softpig.View.fragment.Tool.ToolFragment;
import com.Softpig.View.fragment.Employee.EmployeeFragment;
import com.Softpig.View.fragment.Installation.InstallationFragment;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.Softpig.R;
import com.Softpig.View.fragment.Alarm.AlarmFragment;
import com.Softpig.View.fragment.DashBoard.DashBoardFragment;
import com.Softpig.View.fragment.Medicine.MedicineFragment;
import com.Softpig.View.fragment.Pig.PigFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainMenuActivity extends AppCompatActivity  implements  NavigationView.OnNavigationItemSelectedListener{

    private static MainMenuPresenter mainMenuPresenter;
    private DashBoardFragment dashBoardFragment;
    private AlarmFragment alarmFragment;
    private FemaleFragment femaleFragment;
    private MaleFragment maleFragment;
    private RaceFragment raceFragment;
    private ReportFragment reportFragment;
    private DictionaryFragment dictionaryFragment;
    private MedicineFragment medicineFragment;
    private PigFragment pigFragment;
    private InstallationFragment installationFragment;
    private EmployeeFragment employeeFragment;
    private ProfileFragment profileFragment;
    private AppBarConfiguration mAppBarConfiguration;
    private ToolFragment articleFragment;
    private Bundle bundle;
    private BottomNavigationView bottomNavigationView;
    private DrawerLayout drawer;
    private Toast notificacion;
    private static Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);



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
            getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, new DashBoardFragment()).commit();
            employeeFragment = new EmployeeFragment();
            dashBoardFragment = new DashBoardFragment();
            installationFragment = new InstallationFragment();
            articleFragment = new ToolFragment();
            pigFragment = new PigFragment();
            femaleFragment = new FemaleFragment();
            maleFragment = new MaleFragment();
            profileFragment = new ProfileFragment();
            raceFragment = new RaceFragment();
            dictionaryFragment = new DictionaryFragment();
            mainMenuPresenter = new MainMenuPresenter();
            notificacion = new Toast(this);
        }

        bottomNavigationView = findViewById(R.id.bottombar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectFragment = null;
                    switch (menuItem.getItemId()){
                        case R.id.ic_home:
                            getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, dashBoardFragment).commit();
                            break;
                        case R.id.ic_pig:
                            getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, pigFragment).commit();
                            break;
                        case R.id.ic_medicine:
                            notificacion.cancel();
                            notificacion = notificacion.makeText(MainMenuActivity.this, "Función aún no disponible...", Toast.LENGTH_SHORT);
                            notificacion.show();
                            //selectFragment = new MedicineFragment();
                            break;
                        case R.id.ic_alert:
                            notificacion.cancel();
                            notificacion = notificacion.makeText(MainMenuActivity.this, "Función aún no disponible...", Toast.LENGTH_SHORT);
                            notificacion.show();
                            //selectFragment = new AlarmFragment();
                            break;
                        default:
                            return false;
                    }
                    return true;
                }
            };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }


    public void inflateFragment(int idFragment) {
        switch(idFragment){
            case 0: getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, dashBoardFragment).commit(); break;
            case 1: getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, employeeFragment).commit(); break;
            case 2: getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, installationFragment).commit(); break;
            case 3: getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, articleFragment).commit();
        }
    }

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

    /**
     * Metodo que controla los controles de la navegación izquierda
     * @param menuItem Item seleccionado
     * @return True: acción realizada correctamente
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_female:
                getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, femaleFragment).commit();
                break;
            case R.id.nav_male:
                getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, maleFragment).commit();
                break;
            case R.id.nav_race:
                getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, raceFragment).commit();
                break;
            case R.id.nav_dictionary:
                getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, dictionaryFragment).commit();
                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, new AboutFragment()).commit();
                break;
            case R.id.nav_report:
                getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, reportFragment).commit();
                break;
            case R.id.nav_out:
                super.onBackPressed();
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Metodo para cambiar el titulo del toolbar (barra superior) segun el fragment en que
     * se encuentre
     * @param title Titulo nuevo
     */
    public void setTitleTolbar(String title){
        this.toolbar.setTitle(title);
    }
}