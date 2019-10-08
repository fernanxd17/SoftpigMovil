package com.Softpig.MovilesClase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.Softpig.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegistrarRestaurante extends AppCompatActivity {

    ImageView imagen;
    TextInputEditText etNombre, etDescripcion, etUbicacion;
    Button btGuardar;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_registrar_restaurante);
        database =   FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarRestaurante();
            }
        });
    }

    private void iniciarCampos(){
        btGuardar = findViewById(R.id.btRegistrarRest);
        etNombre = findViewById(R.id.etNameRest);
        etDescripcion = findViewById(R.id.etDesc);
        //Falta completar los demas campos, no son latitud ni longitud es solo un campo de ubicaicon
        //Falta los metodos de conectarse a la bd de firebase para guardar un restaurante
    }

    private void registrarRestaurante() {

    }



}
