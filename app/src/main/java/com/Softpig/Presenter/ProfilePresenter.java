package com.Softpig.Presenter;

import android.app.ProgressDialog;
import android.widget.Toast;

import com.Softpig.Model.Employee;
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
                                          toolEmployee.add(new Tool(idTool, name));
                                      }

                                      toolPersonFragment.setListTool(toolEmployee);
                                      context.inflarFragment("ToolPerson");
                                      progressDialog.dismiss();

                                  } catch (Exception e) { e.printStackTrace(); }
                              }
                          }, new Response.ErrorListener() {
                      @Override
                      public void onErrorResponse(VolleyError error) {

                          try {
                              System.out.println("error: onErrorResponse");
                              context.inflarFragment("Error");
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
}
