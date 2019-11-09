package com.Softpig;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.Softpig.Presenter.MasterPresenter;
import com.Softpig.View.MainMenuActivity;
import com.Softpig.View.fragment.AboutFragment;
import com.Softpig.View.fragment.DictionaryFragment;
import com.Softpig.View.fragment.ForgetPassFragment;
import com.Softpig.View.fragment.LoginFragment;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;

public class IndexActivity extends AppCompatActivity {

    private TextView tvOpc1, tvOpc2;
    private MasterPresenter masterPresenter;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private static  final String  TAG = "LoginActivity";
    //private FirebaseAuth firebaseAuth;
    //private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_login);
        fragmentManager = getSupportFragmentManager();

        if(savedInstanceState == null){
            tvOpc1 = findViewById(R.id.tv_opc_1);
            tvOpc2 = findViewById(R.id.tv_opc_2);
            masterPresenter = new MasterPresenter();
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
     * @param codeUser
     * @param password
     */
    public void login(String codeUser, String password){
        masterPresenter.login(codeUser, password);
        //JSONObject datos = IndexActivity.this.masterPresenter.fragment_login(codeUser, password);
       /* MasterPresenter.login(codeUser, password);
        Intent i = new Intent();
        i.setClass(this, MainMenuActivity.class);
        startActivity(i);*/
    }

    /*private void inicialize() {
        firebaseAuth = FirebaseAuth.getInstance();
        /*Me ayuda a detectar cuando hay un cambio en a sesión*/
       /* authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    Log.w(TAG, "onAuthStateChanged - logeado" + firebaseUser.getEmail());
                } else {
                    Log.w(TAG, "onAuthStateChanged - cerro sesion");
                }
            }
        };

    }

    @Override
        protected void onStart() {
            super.onStart();
            firebaseAuth.addAuthStateListener(authStateListener);
        }

        @Override
        protected void onStop() {
            super.onStop();
            firebaseAuth.removeAuthStateListener(authStateListener);
        }*/


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
}