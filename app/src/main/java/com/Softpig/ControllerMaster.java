package com.Softpig;

import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public  class  ControllerMaster {

    public ControllerMaster(){

    }





   public  JSONObject login( final String email, final String password) {

        try{

            HashMap<String, String> params = new HashMap();
            params.put("user", email);
            params.put("password", password);
            params.put("Content-Type","application/json");

            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    "https://softpig.herokuapp.com/api",
                    new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println("Trae la información completa");
                            //notifyDataSetChanged();
                        }
                    },
                    new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("tuvo un error al pedir la data");

                        }
                    });




        }catch(Exception e){

            //Hubo un error conectandose al servidor
            System.out.println("Hubo un error en el try");
        }

        return null;

    }

    public  boolean  changePassword(){

        try{

            //Realiza la petion post de fragment_login a la API en Python, retorna el jsonObject

        }catch(Exception e){

            //Hubo un error conectandose al servidor
        }

       return true;
    }
}
