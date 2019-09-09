package com.Softpig.Service;

import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

public class ControllerMaster {





   public JSONObject login( final String codeUser, final String password) {

        try{

            //Realiza la petion get de login a la API en Python
            if(true){
                //Si la peticion funciono y los datos son correctos crea una nueva activity del menu principal, returna el Json

            }else{
                //Si la peticion funciono pero los datos son incorrectos, returna el Json
                return null;
            }
        }catch(Exception e){

            //Hubo un error conectandose al servidor
        }

        return null;

    }
}
