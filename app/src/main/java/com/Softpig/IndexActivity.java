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
import android.widget.Toast;

import com.Softpig.Model.Employee;
import com.Softpig.Presenter.MasterPresenter;
import com.Softpig.View.MainMenuActivity;
import com.Softpig.View.fragment.AboutFragment;
import com.Softpig.View.fragment.DictionaryFragment;
import com.Softpig.View.fragment.ForgetPassFragment;
import com.Softpig.View.fragment.LoginFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class IndexActivity extends AppCompatActivity {

    private TextView tvOpc1, tvOpc2;
    private MasterPresenter masterPresenter;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    
    private static  final String  TAG = "LoginActivity";
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
     * @param email
     * @param password
     */
    public void login(String email, final String password){
        masterPresenter.login(this, email, password);
        //JSONObject datos = IndexActivity.this.masterPresenter.fragment_login(codeUser, password);
       //MasterPresenter.login(codeUser, password);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                String name = user.getDisplayName();
                                String email = user.getEmail();

                                // Check if user's email is verified
                                boolean emailVerified = user.isEmailVerified();

                                // The user's ID, unique to the Firebase project. Do NOT use this value to
                                // authenticate with your backend server, if you have one. Use
                                // FirebaseUser.getIdToken() instead.
                                String uid = user.getUid();
                                ControllerMaster controllerMaster = new ControllerMaster();
                                controllerMaster.login(email,password);

                                //Toast.makeText(IndexActivity.this, "Bienvenido, "+ email,
                                       // Toast.LENGTH_SHORT).show();
                            }

                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(IndexActivity.this, "¡Verifica tus datos!",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                    }
                });
        Intent i = new Intent();
        i.setClass(this, MainMenuActivity.class);
        startActivity(i);


    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void startApp(Employee employee){
        //obtiene los datos del empleado y abre la app

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
}