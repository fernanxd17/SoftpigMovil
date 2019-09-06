package com.Softpig.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.Softpig.Service.ControllerMaster;
import com.example.Softpig.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etCodeUser;
    private EditText etPassword;
    private Button btLogin;
    private ControllerMaster controllerMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        capturarCampos();
        this.controllerMaster = new ControllerMaster();

        this.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codeUser = LoginActivity.this.etCodeUser.getText().toString();
                String password = LoginActivity.this.etPassword.getText().toString();

                if(!codeUser.isEmpty() && !password.isEmpty()){
                        LoginActivity.this.controllerMaster.login(this, codeUser, password);
                }else{
                    //Datos vacios
                }
            }
        });
    }

    private void capturarCampos(){
        this.etCodeUser = (EditText)findViewById(R.id.etCodeUser);
        this.etPassword = (EditText)findViewById(R.id.etPassword);
        this.btLogin = (Button)findViewById(R.id.btLogin);
    }

}
