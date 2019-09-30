package com.Softpig.Service;

import android.content.Intent;
import android.view.View;

import com.Softpig.Activitys.LoginActivity;
import com.Softpig.Activitys.MainActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONObject;

public class ControllerMaster {


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");

   public ControllerMaster(){

   }


   public static JSONObject login( final String codeUser, final String password) {

       try {

           //Realiza la petion post de login a la API en Python, retorna el jsonObject

       } catch (Exception e) {

           //Hubo un error conectandose al servidor
       }

       return null;

    }

    public static boolean  changePassword(){

        try{

            //Realiza la petion post de login a la API en Python, retorna el jsonObject

        }catch(Exception e){

            //Hubo un error conectandose al servidor
        }

       return true;
    }
}
