package com.Softpig.Presenter;

import android.app.ProgressDialog;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.Softpig.Model.Birth;
import com.Softpig.Model.ExamMale;
import com.Softpig.Model.Heat;
import com.Softpig.Model.PeriodGestation;
import com.Softpig.View.Fragment.InfoFemaleFragment;
import com.Softpig.View.Fragment.InfoMaleFragment;
import com.Softpig.View.PigActivity;
import com.Softpig.View.Fragment.BirthFragment;
import com.Softpig.View.Fragment.ExamMaleListFragment;
import com.Softpig.View.Fragment.GestationFragment;
import com.Softpig.View.Fragment.HeatFragment;
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
import java.util.HashMap;
import java.util.List;

public class PigPresenter {

    private static final String URLAPI = "https://softpig.herokuapp.com/api/";
    private String[] listIdMale;
    public static final int MY_DEFAULT_TIMEOUT = 15000;

    public PigPresenter() {
    }

    public void darDeBajaPig(final short idPig, final PigActivity context) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Realizando Baja...");
        progressDialog.show();

        RequestQueue queue = Volley.newRequestQueue(context);
        try {

            HashMap<String, String> params = new HashMap();
            params.put("id", String.valueOf(idPig));
            params.put("Content-Type", "application/json");

            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.PUT,
                    "https://softpig.herokuapp.com/api/inactivate_pig/" + idPig,
                    new JSONObject(params),
                    response -> {

                        try {
                            int respo = response.getInt("status");
                            System.out.println("respo: " + respo);


                            progressDialog.dismiss();


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Error en la APP, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    },
                    error -> {
                        error.printStackTrace();
                        progressDialog.dismiss();
                        Toast.makeText(context, "Error obteniendo datos, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                    });
            queue.add(arrayRequest);
        } catch (Exception e) {
            Toast.makeText(context, "Error interno, Intentelo mas tarde", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }
    }

    public void desasignarFemale(final short idFemale, final InfoFemaleFragment infoFemaleFragment) {

        final ProgressDialog progressDialog = new ProgressDialog(infoFemaleFragment.getContext());
        progressDialog.setMessage("Des-asignando reporductora...");

        progressDialog.show();
        RequestQueue queue = Volley.newRequestQueue(infoFemaleFragment.getContext());

        try {

            HashMap<String, String> params = new HashMap();
            params.put("id", String.valueOf(idFemale));
            params.put("Content-Type", "application/json");

            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.PUT,
                    "https://softpig.herokuapp.com/api/remove_female/" + idFemale,
                    new JSONObject(params),
                    response -> {

                        try {
                            int respo = response.getInt("status");
                            System.out.println("respo: " + respo);
                            progressDialog.dismiss();
                            infoFemaleFragment.volver();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(infoFemaleFragment.getContext(), "Error en la APP, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    },
                    error -> {
                        error.printStackTrace();
                        progressDialog.dismiss();
                        Toast.makeText(infoFemaleFragment.getContext(), "Error obteniendo datos, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                    });
            queue.add(arrayRequest);
        } catch (Exception e) {
            Toast.makeText(infoFemaleFragment.getContext(), "Error interno, Intentelo mas tarde", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }
    }

    public void desasignarMale(final short idMale, final InfoMaleFragment infoMaleFragment) {

        final ProgressDialog progressDialog = new ProgressDialog(infoMaleFragment.getContext());
        progressDialog.setMessage("Des-asignado Reproductor...");
        progressDialog.show();

        RequestQueue queue = Volley.newRequestQueue(infoMaleFragment.getContext());
        try {
            HashMap<String, String> params = new HashMap();
            params.put("id", String.valueOf(idMale));
            params.put("Content-Type", "application/json");

            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.PUT,
                    "https://softpig.herokuapp.com/api/remove_male/" + idMale,
                    new JSONObject(params),
                    response -> {

                        try {
                            infoMaleFragment.volverActivy();
                            int respo = response.getInt("status");
                            System.out.println("respo: " + respo);
                            progressDialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(infoMaleFragment.getContext(), "Error en la APP, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    },
                    error -> {
                        error.printStackTrace();
                        progressDialog.dismiss();
                        Toast.makeText(infoMaleFragment.getContext(), "Error obteniendo datos, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                    });
            queue.add(arrayRequest);
        } catch (Exception e) {
            Toast.makeText(infoMaleFragment.getContext(), "Error interno, Intentelo mas tarde", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }
    }

    public void presentarBirthFragment(final PigActivity context, final BirthFragment birthFragment,
                                       final short idFemale, final SwipeRefreshLayout refreshBirth) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        if(refreshBirth == null){
            progressDialog.setMessage("Cargando Partos...");
            progressDialog.show();
        }

        String url = URLAPI + "birth_list/" + idFemale;

        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        List<Birth> listBirth = new ArrayList<>();
                        JSONArray jsonBirth = response.getJSONArray("births");
                        for (int i = 0; i < jsonBirth.length(); i++) {
                            JSONObject birthObject = jsonBirth.getJSONObject(i);
                            short idBirth = (short) birthObject.getInt("id");
                            short idMale = (short) birthObject.getInt("male");
                            String dateBirth = birthObject.getString("date");
                            short babies = (short) birthObject.getInt("babies");
                            short mummy = (short) birthObject.getInt("mummy");
                            short dead = (short) birthObject.getInt("dead");

                            listBirth.add(new Birth(idBirth, idFemale, idMale, dateBirth, babies, mummy, dead));
                        }
                        birthFragment.setListBirth(listBirth);
                        if(refreshBirth == null){
                            context.inflarFragment("Birth");
                            progressDialog.dismiss();
                        }else{
                            birthFragment.notificarAdapter();
                            refreshBirth.setRefreshing(false);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        context.inflarFragment("Error");
                        progressDialog.dismiss();
                    }
                }, error -> {

                    try {
                        context.inflarFragment("Error");
                        progressDialog.dismiss();

                    } catch (Exception e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(json);
    }

    public void presentarGestacionFragment(final PigActivity context, final GestationFragment gestationFragment,
                                           final short idFemale, final SwipeRefreshLayout refreshGestation) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        if (refreshGestation == null) {
            progressDialog.setMessage("Cargando Celos...");
            progressDialog.show();
        }

        String url = URLAPI + "period_gestation_list/" + idFemale;

        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        List<PeriodGestation> listGestation = new ArrayList<>();
                        JSONArray jsonGestation = response.getJSONArray("gestations");
                        for (int i = 0; i < jsonGestation.length(); i++) {
                            JSONObject gestationObject = jsonGestation.getJSONObject(i);
                            short idGestation = (short) gestationObject.getInt("id");
                            short idMale = (short) gestationObject.getInt("male");
                            String dateStart = gestationObject.getString("date_start");

                            listGestation.add(new PeriodGestation(idGestation, idFemale, idMale, dateStart));
                        }
                        gestationFragment.setListGestation(listGestation);

                        if(refreshGestation == null){
                            context.inflarFragment("Gestation");
                            progressDialog.dismiss();
                        }else{
                            gestationFragment.notificarAdapter();
                            refreshGestation.setRefreshing(false);
                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                        context.inflarFragment("Error");
                        progressDialog.dismiss();
                    }
                }, error -> {

                    try {
                        context.inflarFragment("Error");
                        progressDialog.dismiss();

                    } catch (Exception e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(json);
    }

    public void presentarCelosFragment(final PigActivity context, final HeatFragment heatFragment,
                                       final short idFemale, final SwipeRefreshLayout refreshListHeat) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        if(refreshListHeat == null){
            progressDialog.setMessage("Cargando Partos...");
            progressDialog.show();
        }

        String url = URLAPI + "heat_list/" + idFemale;
        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        List<Heat> listHeat = new ArrayList<>();
                        JSONArray jsonHeat = response.getJSONArray("heats");
                        for (int i = 0; i < jsonHeat.length(); i++) {
                            JSONObject heatObject = jsonHeat.getJSONObject(i);
                            short idHeat = (short) heatObject.getInt("id");
                            String typeMating = heatObject.getString("type");
                            String sincrony = heatObject.getString("sincrony");
                            String dateStart = heatObject.getString("dateStart");
                            String dateEnd = heatObject.getString("dateEnd");
                            boolean isSincrony = sincrony.equalsIgnoreCase("Si");
                            listHeat.add(new Heat(idHeat, idFemale, typeMating, isSincrony, dateStart, dateEnd));
                        }
                        heatFragment.setListHeat(listHeat);

                        if(refreshListHeat == null){
                            context.inflarFragment("Heat");
                            progressDialog.dismiss();
                        }else{
                            heatFragment.notificarAdapter();
                            refreshListHeat.setRefreshing(false);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        context.inflarFragment("Error");
                        progressDialog.dismiss();
                    }
                }, error -> {

                    try {
                        context.inflarFragment("Error");
                        progressDialog.dismiss();

                    } catch (Exception e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(json);
    }

    public void presentarExamanesFragment(final PigActivity context, final ExamMaleListFragment examMaleListFragment,
                                          final short idMale, final SwipeRefreshLayout refreshListExamMale) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        if(refreshListExamMale == null){
            progressDialog.setMessage("Cargando Examenes...");
            progressDialog.show();
        }

        String url = URLAPI + "male_exam_list/" + idMale;

        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        List<ExamMale> listExamMale = new ArrayList<>();
                        JSONArray jsonExamMale = response.getJSONArray("exams");
                        for (int i = 0; i < jsonExamMale.length(); i++) {
                            JSONObject examMaleObject = jsonExamMale.getJSONObject(i);
                            short idExam = (short) examMaleObject.getInt("id");
                            String date = examMaleObject.getString("date");
                            String name = examMaleObject.getString("name");
                            String result = examMaleObject.getString("result");
                            String descripcion = examMaleObject.getString("description");

                            listExamMale.add(new ExamMale(idMale, idExam, date, name, descripcion, result));
                        }
                        examMaleListFragment.setListExamMale(listExamMale);
                        if(refreshListExamMale == null){
                            context.inflarFragment("ExamMaleList");
                            progressDialog.dismiss();
                        }else{
                            examMaleListFragment.notificarAdapter();
                            refreshListExamMale.setRefreshing(false);
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                        context.inflarFragment("Error");
                        progressDialog.dismiss();
                    }
                }, error -> {

                    try {
                        context.inflarFragment("Error");
                        progressDialog.dismiss();

                    } catch (Exception e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(json);
    }

    public void agregarBirth(final PigActivity context, final Birth birth, final AlertDialog alertDialog) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Registrando parto...");
        progressDialog.show();

        RequestQueue queue = Volley.newRequestQueue(context);
        try {

            HashMap<String, String> params = new HashMap();
            params.put("ID_FEMALE", String.valueOf(birth.getIdFemale()));
            params.put("idMale", String.valueOf(birth.getIdMale()));
            params.put("DATE_BIRTH", birth.getDataBirth());
            params.put("noBabies", String.valueOf(birth.getNoBabies()));
            params.put("noMummy", String.valueOf(birth.getNoMummy()));
            params.put("noDead", String.valueOf(birth.getNoDead()));
            params.put("Content-Type", "application/json");

            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    "https://softpig.herokuapp.com/api/add_birth",
                    new JSONObject(params),
                    response -> {
                        try {
                            int respo = response.getInt("status");
                            progressDialog.dismiss();
                            alertDialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Error en la APP, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    },
                    error -> {
                        error.printStackTrace();
                        progressDialog.dismiss();
                        Toast.makeText(context, "Error obteniendo datos, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                    });
            queue.add(arrayRequest);
        } catch (Exception e) {
            Toast.makeText(context, "Error interno, Intentelo mas tarde", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }

    }

    public void agregarGestation(final GestationFragment gestationFragment, final PeriodGestation periodGestation, final AlertDialog alertDialog) {
        final ProgressDialog progressDialog = new ProgressDialog(gestationFragment.getContext());
        progressDialog.setMessage("Registrando gestación...");
        progressDialog.show();

        RequestQueue queue = Volley.newRequestQueue(gestationFragment.getContext());
        try {
            HashMap<String, String> params = new HashMap();
            params.put("ID_FEMALE", String.valueOf(periodGestation.getIdFemale()));
            params.put("idMale", String.valueOf(periodGestation.getIdMale()));
            params.put("DATE_START", periodGestation.getDateStart());
            params.put("Content-Type", "application/json");

            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    "https://softpig.herokuapp.com/api/add_gestation",
                    new JSONObject(params),
                    response -> {
                        try {
                            int respo = response.getInt("status");
                            gestationFragment.addGestacion(periodGestation);
                            gestationFragment.notificarAdapter();
                            progressDialog.dismiss();
                            alertDialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(gestationFragment.getContext(), "Error en la APP, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    },
                    error -> {
                        error.printStackTrace();
                        progressDialog.dismiss();
                        Toast.makeText(gestationFragment.getContext(), "Error obteniendo datos, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                    });

            arrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                    MY_DEFAULT_TIMEOUT,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(arrayRequest);
        } catch (Exception e) {
            Toast.makeText(gestationFragment.getContext(), "Error interno, Intentelo mas tarde", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }
    }

    public void agregarHeat(final HeatFragment heatFragment, final Heat heat, final AlertDialog alertDialog) {
        final ProgressDialog progressDialog = new ProgressDialog(heatFragment.getContext());
        progressDialog.setMessage("Registrando celo...");
        progressDialog.show();

        RequestQueue queue = Volley.newRequestQueue(heatFragment.getContext());
        try {

            HashMap<String, String> params = new HashMap();
            params.put("ID_FEMALE", String.valueOf(heat.getIdFemale()));
            params.put("typeMating", heat.getTypeMating());
            params.put("sincrony", heat.isSincrony() ? "Si" : "No");
            params.put("DATE_START", heat.getDateStart());
            params.put("dateEnd", heat.getDateEnd());
            params.put("Content-Type", "application/json");

            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    "https://softpig.herokuapp.com/api/add_heat",
                    new JSONObject(params),
                    response -> {
                        try {
                            int respo = response.getInt("status");
                            heatFragment.addHeat(heat);
                            heatFragment.notificarAdapter();
                            progressDialog.dismiss();
                            alertDialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(heatFragment.getContext(), "Error en la APP, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                            alertDialog.dismiss();
                        }
                    },
                    error -> {
                        error.printStackTrace();
                        progressDialog.dismiss();
                        alertDialog.dismiss();
                        Toast.makeText(heatFragment.getContext(), "Error obteniendo datos, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                    });
            queue.add(arrayRequest);
        } catch (Exception e) {
            Toast.makeText(heatFragment.getContext(), "Error interno, Intentelo mas tarde", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }
    }

    public void addExamReport(final PigActivity context, final short idMale, final short idExam, final String result, final String date) {
        RequestQueue queue = Volley.newRequestQueue(context);
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Subiendo reporte...");
        progressDialog.show();
        try {

            String [] datosFecha = date.split("/");
            String fechaEditada = datosFecha[2] + "-" + datosFecha[1] + "-" + datosFecha[0];
            HashMap<String, String> params = new HashMap();
            params.put("ID_MALE", String.valueOf(idMale));
            params.put("ID_EXAM", String.valueOf(idExam));
            params.put("EXAM_DATE", fechaEditada);
            params.put("examResult", result);
            params.put("Content-Type", "application/json");

            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.PUT,
                    "https://softpig.herokuapp.com/api/report_exam",
                    new JSONObject(params),
                    response -> {

                        try {
                            int respo = response.getInt("status");
                            System.out.println("respo :" + respo);
                            progressDialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Error en la APP, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    },
                    error -> {
                        error.printStackTrace();
                        progressDialog.dismiss();
                        Toast.makeText(context, "Error obteniendo datos, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                    });

            arrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                    MY_DEFAULT_TIMEOUT,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(arrayRequest);
        } catch (Exception e) {
            Toast.makeText(context, "Error interno, Intentelo mas tarde", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }
    }

}
