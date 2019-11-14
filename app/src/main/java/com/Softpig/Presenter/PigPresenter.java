package com.Softpig.Presenter;

import android.app.ProgressDialog;
import android.widget.Toast;

import com.Softpig.View.fragment.PigActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class PigPresenter {


    public PigPresenter (){

    }
    public void darDeBajaPig(short idPig, final PigActivity context) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Realizando Baja...");
        progressDialog.show();

        RequestQueue queue = Volley.newRequestQueue(context);
        try{

            HashMap<String, String> params = new HashMap();
            params.put("id", String.valueOf(idPig));
            params.put("Content-Type","application/json");

            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.PUT,
                    "https://softpig.herokuapp.com/api/inactivate_pig/"+ idPig,
                    new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                int respo = response.getInt("status");
                                System.out.println("respo: "+ respo);



                                progressDialog.dismiss();



                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(context, "Error en la APP, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(context, "Error obteniendo datos, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                        }
                    });
            queue.add(arrayRequest);
        }catch(Exception e){
            Toast.makeText(context, "Error interno, Intentelo mas tarde", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }
    }

    public void desasignarFemale(final short idFemale, final PigActivity context) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Des-asignando reporductora...");

        progressDialog.show();

        RequestQueue queue = Volley.newRequestQueue(context);
        try{

            HashMap<String, String> params = new HashMap();
            params.put("id", String.valueOf(idFemale));

            params.put("Content-Type","application/json");

            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.PUT,
                    "https://softpig.herokuapp.com/api/remove_female/"+ idFemale,
                    new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                int respo = response.getInt("status");
                                System.out.println("respo: "+ respo);
                                progressDialog.dismiss();

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(context, "Error en la APP, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(context, "Error obteniendo datos, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                        }
                    });
            queue.add(arrayRequest);
        }catch(Exception e){
            Toast.makeText(context, "Error interno, Intentelo mas tarde", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }
    }



    public void desasignarMale(final short idMale, final PigActivity context) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Des-asignado Reproductor...");



        progressDialog.show();

        RequestQueue queue = Volley.newRequestQueue(context);
        try{

            HashMap<String, String> params = new HashMap();
            params.put("id", String.valueOf(idMale));



            params.put("Content-Type","application/json");

            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.PUT,

                    "https://softpig.herokuapp.com/api/remove_male/"+ idMale,



                    new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                int respo = response.getInt("status");

                                System.out.println("respo: "+ respo);

                                progressDialog.dismiss();

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(context, "Error en la APP, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(context, "Error obteniendo datos, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                        }
                    });
            queue.add(arrayRequest);
        }catch(Exception e){
            Toast.makeText(context, "Error interno, Intentelo mas tarde", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }
    }

}
