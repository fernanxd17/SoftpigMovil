package com.Softpig.View;

import android.os.Bundle;

import com.Softpig.Presenter.MainMenuPresenter;

import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.Softpig.View.fragment.ToolFragment;
import com.Softpig.View.fragment.EmployeeFragment;
import com.Softpig.View.fragment.InstallationFragment;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import androidx.appcompat.widget.Toolbar;

import com.Softpig.R;
import com.Softpig.View.fragment.AlarmFragment;
import com.Softpig.View.fragment.DashBoardFragment;
import com.Softpig.View.fragment.MedicineFragment;
import com.Softpig.View.fragment.PigFragment;
import com.google.android.material.tabs.TabLayout;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;

public class MainMenuActivity extends AppCompatActivity  {

    private static MainMenuPresenter mainMenuPresenter;
    private DashBoardFragment dashBoardFragment;
    private AlarmFragment alarmFragment;
    private MedicineFragment medicineFragment;
    private PigFragment pigFragment;
    private InstallationFragment installationFragment;
    private EmployeeFragment employeeFragment;
    private AppBarConfiguration mAppBarConfiguration;
    private ToolFragment articleFragment;
    private Bundle bundle;
    BottomBar bottomBar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        alarmFragment = new AlarmFragment();
        medicineFragment = new MedicineFragment();
        pigFragment = new PigFragment();
        dashBoardFragment = new DashBoardFragment();
        employeeFragment = new EmployeeFragment();
        installationFragment = new InstallationFragment();
        articleFragment = new ToolFragment();

        bundle = new Bundle();
        mainMenuPresenter = new MainMenuPresenter();
        bottomBar = findViewById(R.id.bottombar);
        bottomBar.setDefaultTab(R.id.ic_home); //Establecer que imagen aparece de primero
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(int tabId) {
                switch (tabId){
                    case R.id.ic_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, dashBoardFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                        break;
                    case R.id.ic_pig:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, pigFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                        break;
                    case R.id.ic_medicine:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments,medicineFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                        break;
                    case R.id.ic_alert:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, alarmFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                        break;

                }
            }
        });


        getSupportFragmentManager().beginTransaction().add(R.id.containerFragments, dashBoardFragment).commit();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.containerFragments);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.containerFragments);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void inflateEmployeeFragment(){

    }


    public void inflateFragment(int idFragment) {
        switch(idFragment){
            case 0: getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, dashBoardFragment).commit(); break;
            case 1: getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, employeeFragment).commit(); break;
            case 2: getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, installationFragment).commit(); break;
            case 3: getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, articleFragment).commit();
        }
    }
}
