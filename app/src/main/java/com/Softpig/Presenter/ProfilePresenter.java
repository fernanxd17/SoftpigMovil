package com.Softpig.Presenter;

import android.app.ProgressDialog;
import android.widget.Toast;

import com.Softpig.Model.Tool;
import com.Softpig.View.ProfileActivity;
import com.Softpig.View.fragment.ToolFragment;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ProfilePresenter {

    private static final String URLAPI = "https://softpig.herokuapp.com/api/";


    public void presentarToolsPerson(final ProfileActivity context, final ToolFragment toolPersonFragment, final int idEmployee) {

                  final ProgressDialog progressDialog = new ProgressDialog(context);
                  progressDialog.setMessage("Loading...");
                  progressDialog.show();
                  String url = URLAPI + "article-person_list/" +idEmployee;
                  if(idEmployee < 10)
                      url = URLAPI + "article-person_list/0" +idEmployee;
                  JsonObjectRequest json = new JsonObjectRequest(
                          Request.Method.GET,
                          url,
                          null,
                          new Response.Listener<JSONObject>() {
                              @Override
                              public void onResponse(JSONObject response) {

                                  try {

                                      JSONArray jsonToolPerson = response.getJSONArray("articles");
                                      ArrayList<Tool> toolEmployee = new ArrayList<>();
                                      for(int i = 0; i < jsonToolPerson.length(); i++) {
                                          JSONObject toolPersonObject = jsonToolPerson.getJSONObject(i);
                                          short idTool = (short) toolPersonObject.getInt("id");
                                          String name = toolPersonObject.getString("name");
                                          String type = toolPersonObject.getString("type");
                                          toolEmployee.add(new Tool(idTool, name, type));
                                      }
                                      toolPersonFragment.setContext(context);
                                      toolPersonFragment.setListTool(toolEmployee);
                                      context.inflarFragment(toolPersonFragment);
                                      progressDialog.dismiss();

                                  } catch (Exception e) { e.printStackTrace(); }
                              }
                          }, new Response.ErrorListener() {
                      @Override
                      public void onErrorResponse(VolleyError error) {

                          try {
                              System.out.println("error: onErrorResponse");
                              context.inflarFragmentError();
                              progressDialog.dismiss();

                         } catch (Exception e) { e.printStackTrace(); }
                      }
                  });

                  RequestQueue queue = Volley.newRequestQueue(context);
                 queue.add(json);
             }

    public void cambiarEstado(final ProfileActivity context,final short idEmployee, final String estadoNuevo) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Realizando despido...");
        progressDialog.show();

        RequestQueue queue = Volley.newRequestQueue(context);
        try{

            HashMap<String, String> params = new HashMap();
            params.put("id", String.valueOf(idEmployee));
            params.put("state", estadoNuevo);
            params.put("Content-Type","application/json");

            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.PUT,
                    "https://softpig.herokuapp.com/api/change_state",
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

    public void eliminarArticuloPersona(final ProfileActivity context,final int idTool, final String articlePerson) {
        System.out.println("idArt: " + String.valueOf(idTool));
        System.out.println("\n tabla: " + articlePerson);
        RequestQueue queue = Volley.newRequestQueue(context);
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Eliminando articulo...");
        progressDialog.show();
        try{

            HashMap<String, String> params = new HashMap();
            params.put("article", String.valueOf(idTool));
            params.put("table", articlePerson);
            params.put("Content-Type","application/json");

            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    "https://softpig.herokuapp.com/api/remove_article",
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

    public void addHoursWorked(final ProfileActivity context,final int idEmployee, final int hours){
        RequestQueue queue = Volley.newRequestQueue(context);
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Agregando horas...");
        progressDialog.show();
        try{

            HashMap<String, String> params = new HashMap();
            params.put("person", String.valueOf(idEmployee));
            params.put("hours", String.valueOf(hours));
            params.put("Content-Type","application/json");

            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    "https://softpig.herokuapp.com/api/hours_employee",
                    new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int respo = response.getInt("status");
                                System.out.println("respo: "+ respo);
                                progressDialog.dismiss();
                                if (respo !=200)
                                    Toast.makeText(context, "Error, intentalo mas tarde...", Toast.LENGTH_LONG).show();
                                Toast.makeText(context, "Horas agregadas con exito", Toast.LENGTH_LONG).show();

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
