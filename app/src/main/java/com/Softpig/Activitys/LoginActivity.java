package com.Softpig.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Softpig.Service.ControllerMaster;
import com.example.Softpig.R;

import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

    private static EditText etCodeUser;
    private static EditText etPassword;
    private static Button btLogin;
    private static TextView etForgetPassword;
    private static TextView etDictionary;
    private static TextView etAbout;
    private ControllerMaster controllerMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        this.controllerMaster = new ControllerMaster();
        iniciarCampos();


        this.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codeUser = LoginActivity.this.etCodeUser.getText().toString();
                String password = LoginActivity.this.etPassword.getText().toString();

                if(!codeUser.isEmpty() && !password.isEmpty()){
                    //Realiza el login
                    login(codeUser, password);
                }else{
                    //Notifica que no estan los campos completos
                    Toast.makeText(LoginActivity.this, "Algunos campos estan vacios", Toast.LENGTH_SHORT).show();
                }
            }
        });

        this.etDictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dictionary = new Intent();
                dictionary.setClass(LoginActivity.this, DictionaryActivity.class);
                startActivity(dictionary);
            }
        });

        this.etAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent about = new Intent();
                about.setClass(LoginActivity.this, AboutActivity.class);
                startActivity(about);
            }
        });



    }

    private void iniciarCampos() {
        this.etCodeUser = findViewById(R.id.et_code_user);
        this.etPassword = findViewById(R.id.et_password);
        this.btLogin = findViewById(R.id.bt_loginup);
        this.etForgetPassword = findViewById(R.id.et_forget_password);
        this.etDictionary = findViewById(R.id.tv_dictionary);
        this.etAbout = findViewById(R.id.tv_about);
    }


    private void login(String codeUser, String password){
        //JSONObject datos = LoginActivity.this.controllerMaster.login(codeUser, password);


    }

    public void startAbout(View view){
        Intent i = new Intent();
        i.setClass(this, AboutActivity.class);
        startActivity(i);


    }

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

}
