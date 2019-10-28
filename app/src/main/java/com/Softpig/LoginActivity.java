package com.Softpig;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.Softpig.Presenter.MasterPresenter;
import com.Softpig.View.ForgetPasswordActivity;
import com.Softpig.View.MainMenuActivity;

public class LoginActivity extends AppCompatActivity {

    private static EditText etCodeUser, etPassword;
    private static TextView etForgetPassword, etDictionary, etAbout;
    private static Button btLogin;
    private MasterPresenter masterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        this.masterPresenter = new MasterPresenter();
        this.startFilds();


        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codeUser = etCodeUser.getText().toString();
                String password = etPassword.getText().toString();

                if(!codeUser.isEmpty() && !password.isEmpty()){
                    //Realiza el login
                    login(codeUser, password);
                }else{
                    //Notifica que no estan los campos completos
                    Toast.makeText(LoginActivity.this, "Algunos campos estan vacios", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * Toma control de los campos del layout
     */
    private void startFilds() {
        etCodeUser = findViewById(R.id.etCodeUser);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLoginUp);
        etForgetPassword = findViewById(R.id.etForgetPassword);
        etDictionary = findViewById(R.id.tvDictionary);
        etAbout = findViewById(R.id.tvAbout);
    }

    /**
     * Comunica al MasterPresenter la peticion de login
     * @param codeUser
     * @param password
     */
    private void login(String codeUser, String password){
        //JSONObject datos = LoginActivity.this.masterPresenter.login(codeUser, password);
        MasterPresenter.login(codeUser, password);
        Intent i = new Intent();
        i.setClass(this, MainMenuActivity.class);
        startActivity(i);
    }





    /**
     * Inicia ForgetPassword activity
     * @param view Necesaria para tener el control del metodo a traves del onClic
     */
    public void openForgetPassword(View view){
        Intent forgetPassword  = new Intent();
        forgetPassword.setClass(this, ForgetPasswordActivity.class);
        startActivity(forgetPassword);
    }




}