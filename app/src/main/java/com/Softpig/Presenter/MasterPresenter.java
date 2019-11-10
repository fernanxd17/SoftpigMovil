package com.Softpig.Presenter;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import com.Softpig.IndexActivity;

import org.json.JSONObject;

public class MasterPresenter {


    //FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference myRef = database.getReference("message");

   public MasterPresenter(){

   }


   public  JSONObject login(final IndexActivity context,  final String codeUser, final String password) {

       try {
            //volley login
           //Realiza la petion post de fragment_login a la API en Python, retorna el jsonObject
           //crear empleado = respuesta del voley


       } catch (Exception e) {

           //Hubo un fragment_error conectandose al servidor
       }

       return null;

    }

    public  boolean  changePassword(){

        try{

            //Realiza la petion post de fragment_login a la API en Python, retorna el jsonObject

        }catch(Exception e){

            //Hubo un fragment_error conectandose al servidor
        }

       return true;
    }
}
