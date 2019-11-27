package com.Softpig.Presenter;

import android.app.ProgressDialog;
import android.widget.Toast;

import com.Softpig.Model.Birth;
import com.Softpig.Model.ExamMale;
import com.Softpig.Model.Heat;
import com.Softpig.Model.PeriodGestation;
import com.Softpig.View.PigActivity;
import com.android.volley.DefaultRetryPolicy;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PigPresenter {

    private static final String URLAPI = "https://softpig.herokuapp.com/api/";
    private String [] listIdMale;
    public static final int MY_DEFAULT_TIMEOUT = 15000;

    public PigPresenter (){ }

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

    public void presentarBirthFragment(final PigActivity context, final short idFemale) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Cargando Partos...");
        progressDialog.show();


        String url = URLAPI+"birth_list/"+ idFemale;

        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            List<Birth> listBirth = new ArrayList<>();
                            JSONArray jsonBirth = response.getJSONArray("births");
                            for(int i = 0; i < jsonBirth.length(); i++) {
                                JSONObject birthObject = jsonBirth.getJSONObject(i);
                                short idBirth = (short) birthObject.getInt("id");
                                short idMale = (short) birthObject.getInt("male");
                                String dateBirth = birthObject.getString("date");
                                short babies = (short)birthObject.getInt("babies");
                                short mummy = (short)birthObject.getInt("mummy");
                                short dead = (short)birthObject.getInt("dead");


                                listBirth.add(new Birth(idBirth, idFemale, idMale, dateBirth, babies, mummy, dead));
                            }
                            context.setListBirth(listBirth);
                            context.inflarFragment("Birth");
                            progressDialog.dismiss();

                        } catch (Exception e) {
                            e.printStackTrace();
                            context.inflarFragment("Error");
                            progressDialog.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                try {
                    context.inflarFragment("Error");
                    progressDialog.dismiss();

                } catch (Exception e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(json);
    }

    public void presentarGestacionFragment(final PigActivity context, final short idFemale) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Cargando Celos...");
        progressDialog.show();

        String url = URLAPI+"period_gestation_list/"+ idFemale;



        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            List<PeriodGestation> listGestation = new ArrayList<>();
                            JSONArray jsonGestation = response.getJSONArray("gestations");
                            for(int i = 0; i < jsonGestation.length(); i++) {
                                JSONObject gestationObject = jsonGestation.getJSONObject(i);
                                short idGestation = (short) gestationObject.getInt("id");
                                short idMale = (short) gestationObject.getInt("male");
                                String dateStart = gestationObject.getString("date_start");

                                listGestation.add(new PeriodGestation(idGestation, idFemale, idMale, dateStart));
                            }
                            context.setListGestation(listGestation);
                            context.inflarFragment("Gestation");
                            progressDialog.dismiss();

                        } catch (Exception e) {
                            e.printStackTrace();
                            context.inflarFragment("Error");
                            progressDialog.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                try {
                    context.inflarFragment("Error");
                    progressDialog.dismiss();

                } catch (Exception e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(json);
    }

    public void presentarCelosFragment(final PigActivity context, final short idFemale) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Cargando Partos...");
        progressDialog.show();


        String url = URLAPI+"heat_list/"+ idFemale;

        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            List<Heat> listHeat = new ArrayList<>();
                            JSONArray jsonHeat = response.getJSONArray("heats");
                            for(int i = 0; i < jsonHeat.length(); i++) {
                                JSONObject heatObject = jsonHeat.getJSONObject(i);
                                short idHeat = (short) heatObject.getInt("id");
                                String typeMating = heatObject.getString("type");
                                String sincrony = heatObject.getString("sincrony");
                                String dateStart = heatObject.getString("dateStart");
                                String dateEnd = heatObject.getString("dateEnd");
                                boolean isSincrony = sincrony.equalsIgnoreCase("Si");
                                listHeat.add(new Heat(idHeat, idFemale, typeMating, isSincrony, dateStart, dateEnd));
                            }
                            context.setListHeat(listHeat);
                            context.inflarFragment("Heat");
                            progressDialog.dismiss();

                        } catch (Exception e) {
                            e.printStackTrace();
                            context.inflarFragment("Error");
                            progressDialog.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                try {
                    context.inflarFragment("Error");
                    progressDialog.dismiss();

                } catch (Exception e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(json);
    }

    public void presentarExamanesFragment(final PigActivity context, final  short idMale) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Cargando Examenes...");
        progressDialog.show();


        String url = URLAPI+"male_exam_list/"+ idMale;

        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            List<ExamMale> listExamMale = new ArrayList<>();
                            JSONArray jsonExamMale = response.getJSONArray("exams");
                            for(int i = 0; i < jsonExamMale.length(); i++) {
                                JSONObject examMaleObject = jsonExamMale.getJSONObject(i);
                                short idExam = (short) examMaleObject.getInt("id");
                                String date = examMaleObject.getString("date");
                                String name = examMaleObject.getString("name");
                                String result = examMaleObject.getString("result");
                                String descripcion = examMaleObject.getString("description");

                                listExamMale.add(new ExamMale(idMale, idExam, date, name, descripcion, result));
                            }
                            context.setListExamMale(listExamMale);
                            context.inflarFragment("ExamMaleList");
                            progressDialog.dismiss();

                        } catch (Exception e) {
                            e.printStackTrace();
                            context.inflarFragment("Error");
                            progressDialog.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                try {
                    context.inflarFragment("Error");
                    progressDialog.dismiss();

                } catch (Exception e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(json);
    }

    public void agregarBirth(final PigActivity context, final short idFemale, final short idMale, final Date dateBirth, final short noBabies,final short noMummy, final short noDead){
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Registrando parto...");
        progressDialog.show();

        RequestQueue queue = Volley.newRequestQueue(context);
        try{

            HashMap<String, String> params = new HashMap();
            params.put("ID_FEMALE", String.valueOf(idFemale));
            params.put("idMale", String.valueOf(idMale));
            params.put("DATE_BIRTH", String.valueOf(dateBirth));
            params.put("noBabies", String.valueOf(noBabies));
            params.put("noMummy", String.valueOf(noMummy));
            params.put("noDead", String.valueOf(noDead));
            params.put("Content-Type","application/json");

            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    "https://softpig.herokuapp.com/api/add_birth",
                    new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int respo = response.getInt("status");
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

    public void agregarGestation(final PigActivity context, final short idFemale, final short idMale, final String fechaGestacion){
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Registrando gestación...");
        progressDialog.show();

        RequestQueue queue = Volley.newRequestQueue(context);
        try{
            System.out.println("idfemale:" + idFemale);
            System.out.println("idMale: "+idMale);
            System.out.println("date:" +fechaGestacion);
            HashMap<String, String> params = new HashMap();
            params.put("ID_FEMALE", String.valueOf(idFemale));
            params.put("idMale", String.valueOf(idMale));
            params.put("DATE_START", fechaGestacion);
            params.put("Content-Type","application/json");

            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    "https://softpig.herokuapp.com/api/add_gestation",
                    new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int respo = response.getInt("status");
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

            arrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                    MY_DEFAULT_TIMEOUT,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(arrayRequest);
        }catch(Exception e){
            Toast.makeText(context, "Error interno, Intentelo mas tarde", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }
    }

    public void agregarHeat(final PigActivity context, final short idFemale, final String typeMating, final String sincrony, final Date dateStart, final Date dateEnd){
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Registrando celo...");
        progressDialog.show();

        RequestQueue queue = Volley.newRequestQueue(context);
        try{

            HashMap<String, String> params = new HashMap();
            params.put("ID_FEMALE", String.valueOf(idFemale));
            params.put("typeMating", typeMating);
            params.put("sincrony", sincrony);
            params.put("DATE_START", String.valueOf(dateStart));
            params.put("dateEnd", String.valueOf(dateEnd));
            params.put("Content-Type","application/json");

            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    "https://softpig.herokuapp.com/api/add_gestation",
                    new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int respo = response.getInt("status");
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

    public void addExamReport(final PigActivity context, final short idMale, final short idExam, final String date){
        RequestQueue queue = Volley.newRequestQueue(context);
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Subiendo reporte...");
        progressDialog.show();
        try{

            HashMap<String, String> params = new HashMap();
            params.put("ID_MALE", String.valueOf(idMale));
            params.put("ID_EXAM", String.valueOf(idExam));
            params.put("examResult", date);
            params.put("Content-Type","application/json");

            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.PUT,
                    "https://softpig.herokuapp.com/api/report_exam",
                    new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                int respo = response.getInt("status");

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

