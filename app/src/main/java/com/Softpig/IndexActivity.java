package com.Softpig;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.Softpig.Model.Employee;
import com.Softpig.View.MainMenuActivity;
import com.Softpig.View.Fragment.AboutFragment;
import com.Softpig.View.Fragment.DictionaryFragment;
import com.Softpig.View.Fragment.ForgetPassFragment;
import com.Softpig.View.Fragment.LoginFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class IndexActivity extends AppCompatActivity {

    private TextView tvOpc1, tvOpc2;
    private ControllerMaster controllerMaster;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private boolean loginFirebase;


    private FirebaseAuth mAuth;
    //private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_login);
        fragmentManager = getSupportFragmentManager();
        mAuth = FirebaseAuth.getInstance();
        if(savedInstanceState == null){
            tvOpc1 = findViewById(R.id.tv_opc_1);
            tvOpc2 = findViewById(R.id.tv_opc_2);
            controllerMaster = new ControllerMaster();
            fragmentManager.beginTransaction().replace(R.id.container_fragments_login, new LoginFragment()).commit();
        }

        tvOpc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtOpc1 = tvOpc1.getText().toString();
                switch (txtOpc1){
                    case "Diccionario":
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragments_login, new DictionaryFragment()).commit();
                        tvOpc1.setText("Iniciar Sesión");
                        tvOpc2.setText("Acerca De");
                        break;
                     default:
                         getSupportFragmentManager().beginTransaction().replace(R.id.container_fragments_login, new LoginFragment()).commit();
                         tvOpc1.setText("Diccionario");
                         break;
                }

            }
        });

        tvOpc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtOpc2 = tvOpc2.getText().toString();
                switch (txtOpc2){
                    case "Acerca De":
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragments_login, new AboutFragment()).commit();
                        tvOpc2.setText("Iniciar Sesión");
                        tvOpc1.setText("Diccionario");
                        break;
                    default:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragments_login, new LoginFragment()).commit();
                        tvOpc2.setText("Acerca De");
                        break;
                }
            }
        });


    }

    /**
     * Comunica al MasterPresenter la peticion de fragment_login
     * @param email
     * @param password
     */
    public void login(String email, String password){

        controllerMaster.login(this,mAuth, loginFirebase, email, password);



    }

    public void setLoginFirebase(boolean valor){
        this.loginFirebase = valor;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void startApp(Employee employee){
            Intent i = new Intent();
            i.setClass(this, MainMenuActivity.class);
            i.putExtra("Empleado", employee);
            startActivity(i);
    }


    public boolean openForgetFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragments_login, new ForgetPassFragment()).commit();
        return true;
    }

    public boolean openDictionary(View view){
        return true;
    }

    public boolean openAbout(View view){
        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragments_login, new AboutFragment()).commit();
        return true;
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

    public void backLogin() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragments_login, new LoginFragment()).commit();
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
}