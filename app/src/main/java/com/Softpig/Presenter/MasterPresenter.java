package com.Softpig.Presenter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

public class MasterPresenter {


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");

   public MasterPresenter(){

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
