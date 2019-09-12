package com.Softpig.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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
    private ControllerMaster controllerMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.etCodeUser = (EditText)findViewById(R.id.etCodeUser);
        this.etPassword = (EditText)findViewById(R.id.etPassword);
        this.btLogin = (Button)findViewById(R.id.btLogin);
        this.etForgetPassword = (TextView)findViewById(R.id.etForgetPassword);

        this.controllerMaster = new ControllerMaster();

        this.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        this.etForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codeUser = LoginActivity.this.etCodeUser.getText().toString();
                String password = LoginActivity.this.etPassword.getText().toString();

                if(!codeUser.isEmpty() && !password.isEmpty()){
                    //Realiza el login
                    login(codeUser, password);
                }else{
                    //Notifica que no estan los campos completos
                    Toast.makeText(LoginActivity.this, "Algunos campos estan vacios", Toast.LENGTH_SHORT);
                }
            }
        });
    }



    private void login(String codeUser, String password){
        JSONObject datos = LoginActivity.this.controllerMaster.login(codeUser, password);
    }

}
