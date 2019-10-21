package com.Softpig.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.Softpig.R;
import com.Softpig.View.fragment.AlarmFragment;
import com.Softpig.View.fragment.DashBoardFragment;
import com.Softpig.View.fragment.MedicineFragment;
import com.Softpig.View.fragment.PigFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;

public class ContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottombar);
        bottomBar.setDefaultTab(R.id.pig); //Establecer que imagen aparece de primero
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(int tabId) {
                switch (tabId){
                    case R.id.home:
                        DashBoardFragment homeFragment = new DashBoardFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                        break;
                    case R.id.pig:
                        PigFragment pigFragment = new PigFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, pigFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                        break;
                    case R.id.medicine:
                        MedicineFragment medicineFragment = new MedicineFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,medicineFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                        break;
                    case R.id. alert:
                       AlarmFragment alarmFragment = new AlarmFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, alarmFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                        break;

                }
            }
        });


    }
}
