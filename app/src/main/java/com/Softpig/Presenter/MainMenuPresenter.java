package com.Softpig.Presenter;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.widget.Toast;

import com.Softpig.Model.Employee;
import com.Softpig.Model.Female;
import com.Softpig.Model.Installation;
import com.Softpig.Model.Male;
import com.Softpig.Model.Pig;
import com.Softpig.Model.Race;
import com.Softpig.Model.Tool;
import com.Softpig.View.MainMenuActivity;
import com.Softpig.View.fragment.DashBoardFragment;
import com.Softpig.View.fragment.EmployeeFragment;
import com.Softpig.View.fragment.ErrorFragment;
import com.Softpig.View.fragment.FemaleFragment;
import com.Softpig.View.fragment.InstallationFragment;
import com.Softpig.View.fragment.MaleFragment;
import com.Softpig.View.fragment.PigFragment;
import com.Softpig.View.fragment.RaceFragment;
import com.Softpig.View.fragment.ToolFragment;
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

public class MainMenuPresenter {

    private static boolean toolsUpdate;


    private MaleFragment maleFragment;

    private ArrayList<Pig> listPig;
    public static final int MY_DEFAULT_TIMEOUT = 15000;

    private static final String URLAPI = "https://softpig.herokuapp.com/api/";

    public MainMenuPresenter(){
        toolsUpdate = false;
    }



    public boolean inflarRacesFragment(final MainMenuActivity context, final RaceFragment raceFragment) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        String url = URLAPI + "race_list";

        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            ArrayList<Race> listRaces = new ArrayList<>();
                            JSONArray jsonRaces = response.getJSONArray("races");

                                for(int i = 0; i < jsonRaces.length(); i++) {
                                    JSONObject raceObject = jsonRaces.getJSONObject(i);
                                    short idRace = (short) raceObject.getInt("id");
                                    String race = raceObject.getString("name");
                                    String description = raceObject.getString("description");
                                    listRaces.add(new Race(idRace, race, description));
                                }
                                raceFragment.setListRace(listRaces);
                                context.inflarFragment(raceFragment);
                                progressDialog.dismiss();

                        } catch (Exception e) {
                            e.printStackTrace();
                            context.inflarFragment(new ErrorFragment());
                            progressDialog.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                try {
                    context.inflarFragment(new ErrorFragment());
                    progressDialog.dismiss();

                } catch (Exception e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(json);
        return true;

    }

    public boolean inflarInstallationsFragment(final MainMenuActivity context, final InstallationFragment installationFragment) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        String url = URLAPI+"installation_list";
        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            ArrayList<Installation> listInstallations = new ArrayList<>();
                            JSONArray jsonInstallations = response.getJSONArray("installations");


                            for(int i = 0; i < jsonInstallations.length(); i++) {
                                JSONObject installationObject = jsonInstallations.getJSONObject(i);
                                short id = (short) installationObject.getInt("id");
                                String name = installationObject.getString("name");
                                String type = installationObject.getString("type");
                                short capacity = (short) installationObject.getInt("capacity");
                                listInstallations.add(new Installation(id, type, name, capacity));
                            }
                            installationFragment.setListInstallations(listInstallations);
                            context.inflarFragment( installationFragment);
                            progressDialog.dismiss();

                        } catch (Exception e) {
                            e.printStackTrace();
                            context.inflarFragment(new ErrorFragment());
                            progressDialog.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                try {
                    context.inflarFragment(new ErrorFragment());
                    progressDialog.dismiss();

                } catch (Exception e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(json);
        return true;

    }

    public boolean inflarPigFragment(final MainMenuActivity context, final PigFragment pigFragment) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        if(listPig != null){
            pigFragment.setListPig(listPig);
            context.inflarFragment(pigFragment);
            progressDialog.dismiss();
            return  true;
        }

        String url = URLAPI+"pig_list";

        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            ArrayList<Pig> listPigs = new ArrayList<>();
                            JSONArray jsonPig = response.getJSONArray("pigs");
                            for(int i = 0; i < jsonPig.length(); i++) {
                                JSONObject pigObject = jsonPig.getJSONObject(i);
                                short id = (short) pigObject.getInt("id");
                                String state = pigObject.getString("state");
                                String sex = pigObject.getString("sex");
                                short weigth = (short) pigObject.getInt("weigth");
                                String race = pigObject.getString("race");
                                String growthPhase = pigObject.getString("growthPhase");
                                String pigState = pigObject.getString("pigStage");
                                String health = pigObject.getString("health");
                                String installation = pigObject.getString("installation");
                                String birth = pigObject.getString("birthDate");
                                String acquisition= pigObject.getString("acquisitionDate");

                                listPigs.add(new Pig(id, state, sex, weigth, race, growthPhase, pigState,
                                        health,installation, birth, acquisition));
                            }
                            System.out.println("Size: "+listPigs.size());
                            pigFragment.setListPig(listPigs);
                            context.inflarFragment(pigFragment);
                            progressDialog.dismiss();

                        } catch (Exception e) {
                            e.printStackTrace();
                            context.inflarFragment(new ErrorFragment());
                            progressDialog.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                try {
                    context.inflarFragment(new ErrorFragment());
                    progressDialog.dismiss();

                } catch (Exception e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(json);
        return true;

    }

    public boolean inflarArticlesFragment(final MainMenuActivity context, final ToolFragment toolFragment) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


            String url = URLAPI+ "article_list";

            JsonObjectRequest json = new JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                ContentValues contentValues = new ContentValues();

                                ArrayList<Tool> listTool = new ArrayList<>();
                                JSONArray jsonTools = response.getJSONArray("articles");
                                for(int i = 0; i < jsonTools.length(); i++) {
                                    JSONObject toolObject = jsonTools.getJSONObject(i);
                                    short quantity = (short) toolObject.getInt("quantity");
                                    if(quantity == 0){
                                        continue;
                                    }
                                    short id = (short) toolObject.getInt("id");
                                    String name = toolObject.getString("name");

                                    String type = toolObject.getString("type");
                                    listTool.add(new Tool(id, type,name, quantity));

                                }

                                toolFragment.setListTool(listTool);
                                toolFragment.setContext(context);
                                context.inflarFragment(toolFragment);
                                toolsUpdate = true;
                                progressDialog.dismiss();

                            } catch (Exception e) {
                                e.printStackTrace();
                                context.inflarFragment(new ErrorFragment());
                                progressDialog.dismiss();
                            }
                        }

                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    try {
                        context.inflarFragment(new ErrorFragment());
                        progressDialog.dismiss();

                    } catch (Exception e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
            });

            RequestQueue queue = Volley.newRequestQueue(context);
            queue.add(json);




        return true;

    }

    public boolean inflarEmployeesFragment(final MainMenuActivity context,final EmployeeFragment employeeFragment) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Cargando Empleados...");
        progressDialog.show();

        String url = URLAPI + "employee_list";

        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            ArrayList<Employee> listEmployee = new ArrayList<>();
                            JSONArray jsonEmployee = response.getJSONArray("employees");

                            for(int i = 0; i < jsonEmployee.length(); i++) {
                                JSONObject employeeObject = jsonEmployee.getJSONObject(i);
                                String role = employeeObject.getString("role");
                                if(role.equalsIgnoreCase("Administrador")){
                                    continue;
                                }
                                short id = (short) employeeObject.getInt("id");
                                String status = employeeObject.getString("state");
                                String contract = employeeObject.getString("contract");
                                String hoursWorked = employeeObject.getString("hoursWorked");
                                String admission = employeeObject.getString("dateAdmission");
                                //Date admissionDate =  simpleDateFormat.parse(admission);

                                String off = employeeObject.getString("dateOff");
                                //Date dateOff = simpleDateFormat.parse(off);

                                int salary = employeeObject.getInt("salary");
                                String document = employeeObject.getString("document");
                                String firstName = employeeObject.getString("firstName");
                                String secondName = employeeObject.getString("secondName");
                                String fatherLastName = employeeObject.getString("fatherLastName");
                                String motherLastName = employeeObject.getString("motherLastName");
                                String sex = employeeObject.getString("sex");
                                String email = employeeObject.getString("email");
                                String phone = employeeObject.getString("phone");
                                String celPhone = employeeObject.getString("celPhone");

                                String instalation = employeeObject.getString("instalation");

                                listEmployee.add(new Employee(id, role,contract, hoursWorked, status, admission, off, document,
                                        sex, firstName, secondName, fatherLastName, motherLastName, email, phone, celPhone, instalation, salary ));
                            }
                            employeeFragment.setListEmployees(listEmployee);
                            context.inflarFragment(employeeFragment);
                            progressDialog.dismiss();

                        } catch (Exception e) {
                            e.printStackTrace();
                            context.inflarFragment(new ErrorFragment());
                            progressDialog.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                try {
                    context.inflarFragment(new ErrorFragment());
                    progressDialog.dismiss();

                } catch (Exception e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(json);
        return true;

    }

    public boolean inflarFemalesFragment(final MainMenuActivity context, final FemaleFragment femaleFragment) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        if(listPig == null){
            traerDatosPorcinos(context);
        }

        String url = URLAPI + "female_list";

        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            ArrayList<Female> listFemales = new ArrayList<>();
                            JSONArray jsonFemale = response.getJSONArray("females");


                            for(int i = 0; i < jsonFemale.length(); i++) {
                                JSONObject femaleObject = jsonFemale.getJSONObject(i);
                                short id = (short) femaleObject.getInt("id");
                                String virgin = femaleObject.getString("virgin");
                                String gestation = femaleObject.getString("gestation");
                                String stateFemale = femaleObject.getString("state");
                                Pig pig = MainMenuPresenter.this.buscarPig(id);
                                listFemales.add(new Female(id, virgin, gestation,stateFemale,pig.getState(), pig.getSex(), pig.getWeigth(), pig.getRace(), pig.getGrowthPhase(),
                                        pig.getPigState(), pig.getHealth(), pig.getInstallation(), pig.getBirthDate(), pig.getAcquisitionDate()));

                            }
                            femaleFragment.setListFemale(listFemales);
                            context.inflarFragment(femaleFragment);
                            progressDialog.dismiss();

                        } catch (Exception e) {
                            e.printStackTrace();
                            context.inflarFragment(new ErrorFragment());
                            progressDialog.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                try {
                    context.inflarFragment(new ErrorFragment());
                    progressDialog.dismiss();

                } catch (Exception e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(json);
        return true;

    }

    private Pig buscarPig(short id) {
        for (Pig pig : listPig)
        {
          if(pig.getIdPig() == id){
              return pig;
          }
        }

        return null;
    }

    private void traerDatosPorcinos(final MainMenuActivity context) {


        String url = URLAPI+"pig_list";

        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            MainMenuPresenter.this.listPig = new ArrayList<>();
                            JSONArray jsonPig = response.getJSONArray("pigs");
                            for(int i = 0; i < jsonPig.length(); i++) {
                                JSONObject pigObject = jsonPig.getJSONObject(i);
                                short id = (short) pigObject.getInt("id");
                                String state = pigObject.getString("state");
                                String sex = pigObject.getString("sex");
                                short weigth = (short) pigObject.getInt("weigth");
                                String race = pigObject.getString("race");
                                String growthPhase = pigObject.getString("growthPhase");
                                String pigState = pigObject.getString("pigStage");
                                String health = pigObject.getString("health");
                                String installation = pigObject.getString("installation");
                                String birth = pigObject.getString("birthDate");
                                String acquisition= pigObject.getString("acquisitionDate");

                                MainMenuPresenter.this.listPig.add(new Pig(id, state, sex, weigth, race, growthPhase, pigState,
                                        health,installation, birth, acquisition));
                            }
                            System.out.println("Listando porcinos");
                            for(Pig pig : MainMenuPresenter.this.listPig){
                                System.out.println("id: " + pig.getIdPig());
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                            context.inflarFragment(new ErrorFragment());

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                try {
                    error.printStackTrace();
                    context.inflarFragment(new ErrorFragment());


                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(json);
    }

    public boolean inflarMalesFragment(final MainMenuActivity context, final MaleFragment maleFragment) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        if(listPig == null){
            traerDatosPorcinos(context);
        }

        String url = URLAPI + "male_list";

        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            ArrayList<Male> listMale = new ArrayList<>();
                            JSONArray jsonMales = response.getJSONArray("males");

                            for(int i = 0; i < jsonMales.length(); i++) {
                                JSONObject maleObject = jsonMales.getJSONObject(i);
                                short id = (short) maleObject.getInt("id");
                                String conformation = maleObject.getString("conformation");
                                String stateMale = maleObject.getString("state");
                                Pig pig = buscarPig(id);

                                listMale.add(new Male(id, conformation,stateMale, pig.getState(),pig.getSex(),pig.getWeigth() , pig.getRace(), pig.getGrowthPhase(),
                                        pig.getPigState(), pig.getHealth(), pig.getInstallation(),pig.getBirthDate(), pig.getAcquisitionDate()));
                                }


                            maleFragment.setListMale(listMale);
                            context.inflarFragment(maleFragment);
                            progressDialog.dismiss();

                        } catch (Exception e) {
                            e.printStackTrace();
                            context.inflarFragment(new ErrorFragment());
                            progressDialog.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                try {
                    context.inflarFragment(new ErrorFragment());
                    progressDialog.dismiss();

                } catch (Exception e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(json);
        return true;

    }

    public void presentarDashboard(final MainMenuActivity context, final DashBoardFragment dashBoardFragment) {


        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        String url = URLAPI + "dasboard";

        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray jsonDataEmployee = response.getJSONArray("dashboard");
                            JSONObject adminObject = jsonDataEmployee.getJSONObject(0);
                            short adminNum = (short) adminObject.getInt("administrativos");
                            JSONObject operaObject = jsonDataEmployee.getJSONObject(1);
                            short operNum = (short) operaObject.getInt("operativos");
                            JSONObject toolPersonObject = jsonDataEmployee.getJSONObject(2);
                            short numToolPerson = (short)toolPersonObject.getInt("article_person");
                            JSONObject toolInventaryObject = jsonDataEmployee.getJSONObject(3);
                            short toolInventario =  (short) toolInventaryObject.getInt("items_inventory");
                            JSONObject numInstallationObject = jsonDataEmployee.getJSONObject(4);
                            short numInstallation = (short)numInstallationObject.getInt("number_installations");
                            JSONObject typeInstallationObject = jsonDataEmployee.getJSONObject(5);
                            short typeInstallation = (short)typeInstallationObject.getInt("installations_type");

                            short [] valores = new short [6];
                            valores[0] = adminNum;
                            valores[1] = operNum;
                            valores[2] = numToolPerson;
                            valores[3] = toolInventario;
                            valores[4] = numInstallation;
                            valores[5] = typeInstallation;

                            dashBoardFragment.setValores(valores);
                            context.inflarFragment(dashBoardFragment);
                            progressDialog.dismiss();

                        } catch (Exception e) {
                            System.out.println("entra 1");
                            e.printStackTrace();
                            context.inflarFragment(new ErrorFragment());
                            progressDialog.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                try { error.printStackTrace();

                    context.inflarFragment(new ErrorFragment());

                    progressDialog.dismiss();

                } catch (Exception e) {
                    System.out.println("Entra 2");
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        json.setRetryPolicy(new DefaultRetryPolicy(
                MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(json);

    }

    public void eliminarArticulo(final MainMenuActivity context,final int idTool, final String table) {

        RequestQueue queue = Volley.newRequestQueue(context);
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Eliminando articulo...");
        progressDialog.show();
        try{

            HashMap<String, String> params = new HashMap();
            params.put("article", String.valueOf(idTool));
            params.put("table", table);
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
}
