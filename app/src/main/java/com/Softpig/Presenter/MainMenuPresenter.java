package com.Softpig.Presenter;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.Softpig.Model.Alarm;
import com.Softpig.Model.Employee;
import com.Softpig.Model.Female;
import com.Softpig.Model.Installation;
import com.Softpig.Model.Male;
import com.Softpig.Model.Medicine;
import com.Softpig.Model.Pig;
import com.Softpig.Model.Race;
import com.Softpig.Model.Tool;
import com.Softpig.View.MainMenuActivity;
import com.Softpig.View.PigActivity;
import com.Softpig.View.ProfileActivity;
import com.Softpig.View.fragment.AlarmFragment;
import com.Softpig.View.fragment.DashBoardFragment;
import com.Softpig.View.fragment.EmployeeFragment;
import com.Softpig.View.fragment.ErrorFragment;
import com.Softpig.View.fragment.FemaleFragment;
import com.Softpig.View.fragment.HeatFragment;
import com.Softpig.View.fragment.InstallationFragment;
import com.Softpig.View.fragment.MaleFragment;
import com.Softpig.View.fragment.MedicineFragment;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainMenuPresenter {

    private static boolean toolsUpdate;
    private MaleFragment maleFragment;
    private ArrayList<Pig> listPig;
    private HashMap<String, Short> hmTypeTool;
    private String [] listIdMale;
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

    public boolean inflarInstallationsFragment(final MainMenuActivity context, final InstallationFragment installationFragment,
                                               final SwipeRefreshLayout refreshListInstallation, final boolean inflar) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        if(inflar){
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        String url = URLAPI+"installation_list";
        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
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
                        if(inflar){
                            context.inflarFragment( installationFragment);
                            progressDialog.dismiss();
                        }else{
                            installationFragment.notificarAdapter();
                            refreshListInstallation.setRefreshing(false);
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                        context.inflarFragment(new ErrorFragment());
                        progressDialog.dismiss();
                    }
                }, error -> {

                    try {
                        context.inflarFragment(new ErrorFragment());
                        progressDialog.dismiss();

                    } catch (Exception e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(json);
        return true;

    }

    public boolean inflarPigFragment(final MainMenuActivity context, final PigFragment pigFragment,
                                     final SwipeRefreshLayout refrescarListPig, final boolean inflar) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        if(inflar){
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

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


                            pigFragment.setListPig(listPigs);
                            if(inflar){
                                context.inflarFragment(pigFragment);
                                progressDialog.dismiss();
                            }else{
                                pigFragment.notifyAdapter();
                                refrescarListPig.setRefreshing(false);
                            }


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

    public boolean inflarToolsFragment(final MainMenuActivity context, final ToolFragment toolFragment
            , final SwipeRefreshLayout refreshListTool, final boolean inflar) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        if(inflar){
            progressDialog.setMessage("Cargando Herramientas...");
            progressDialog.show();
        }

        if(hmTypeTool == null){
            traerDatosTiposTool(context, toolFragment);
        }

        String url = URLAPI + "article_list";

        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            ContentValues contentValues = new ContentValues();

                            List<Tool> listTool = new ArrayList<>();
                            JSONArray jsonTools = response.getJSONArray("articles");
                            for(int i = 0; i < jsonTools.length(); i++) {
                                JSONObject toolObject = jsonTools.getJSONObject(i);
                                short quantity = (short) toolObject.getInt("quantity");
                                if(quantity == 0){ continue; }
                                short loan = (short)toolObject.getInt("loan");
                                short id = (short) toolObject.getInt("id");
                                String name = toolObject.getString("name");
                                String type = toolObject.getString("type");
                                listTool.add(new Tool(id, type, name, quantity, loan));

                            }

                            toolFragment.setListTool(listTool);
                            if(inflar){
                                toolFragment.setContext(context);
                                context.inflarFragment(toolFragment);
                                progressDialog.dismiss();
                            }else{
                                toolFragment.notificarAdapter();
                                refreshListTool.setRefreshing(false);
                            }


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

    public boolean inflarEmployeesFragment(final MainMenuActivity context,final EmployeeFragment employeeFragment,
                                           final SwipeRefreshLayout refreshListEmployee, final boolean inflar) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        if(inflar){
            progressDialog.setMessage("Cargando Empleados...");
            progressDialog.show();
        }

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

                            if(inflar){
                                context.inflarFragment(employeeFragment);
                                progressDialog.dismiss();
                            }else{
                                employeeFragment.notificarAdapter();
                                refreshListEmployee.setRefreshing(false);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            context.inflarFragment(new ErrorFragment());
                            progressDialog.dismiss();
                        }
                    }
                }, error -> {

                    try {
                        context.inflarFragment(new ErrorFragment());
                        progressDialog.dismiss();

                    } catch (Exception e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
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

    public boolean inflarMalesFragment(final MainMenuActivity context, final MaleFragment maleFragment, final Boolean inflar) {

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

                            if(inflar){
                                maleFragment.setListMale(listMale);
                                context.inflarFragment(maleFragment);
                            }

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

    public boolean inflarHeatsFragment(final MainMenuActivity context, final HeatFragment heatFragment) {
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

    public void inflarMedicineFragment(final MainMenuActivity context, final MedicineFragment medicineFragment
            , final SwipeRefreshLayout refrescarListMedicine, final boolean inflar) {


        final ProgressDialog progressDialog = new ProgressDialog(context);
        if(inflar){
            progressDialog.setMessage("Cargando Medicinas...");
            progressDialog.show();
        }

        String url = URLAPI+"inventary_medicine";

        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            ArrayList<Medicine> listMedicine = new ArrayList<>();
                            JSONArray jsonMedicine = response.getJSONArray("medicines");
                            for(int i = 0; i < jsonMedicine.length(); i++) {
                                JSONObject medicineObject = jsonMedicine.getJSONObject(i);
                                short quantity = (short) medicineObject.getInt("quantity");
                                if(quantity == 0)
                                    continue;
                                short id = (short) medicineObject.getInt("id");
                                String name = medicineObject.getString("name");
                                String type = medicineObject.getString("type");

                                listMedicine.add(new Medicine(id, name, type, quantity));
                            }

                            medicineFragment.setListMedicine(listMedicine);
                            if(inflar){
                                context.inflarFragment(medicineFragment);
                                progressDialog.dismiss();
                            }else{
                                medicineFragment.notificarAdapter();
                                refrescarListMedicine.setRefreshing(false);
                            }


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

    }

    public void presentarDashboard(final MainMenuActivity context, final DashBoardFragment dashBoardFragment,
                                   final SwipeRefreshLayout refrescarDashboard, final boolean inflar) {
        /*if(refrescarDashboard != null){
            refrescarDashboard.setRefreshing(true);
        }*/
        final ProgressDialog progressDialog = new ProgressDialog(context);
        if(inflar){
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }


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

                            if (inflar){
                                context.inflarFragment(dashBoardFragment);
                                progressDialog.dismiss();
                            }else{
                                dashBoardFragment.actualizarText();
                                refrescarDashboard.setRefreshing(false);
                            }
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

    private Pig buscarPig(short id) {
        for (Pig pig : listPig)
        {
            if(pig.getIdPig() == id){
                return pig;
            }
        }
        return null;
    }

    private void traerDatosTiposTool(final MainMenuActivity context, final ToolFragment toolFragment) {
        String url = URLAPI + "tool_type";

        final JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            List<String> listTypeTool = new ArrayList<>();
                            listTypeTool.add("Seleccione Categoria");
                            JSONArray jsonPig = response.getJSONArray("articles_Type");
                            short lengthType = (short) jsonPig.length();
                            HashMap<String, Short> hmTypeTool = new HashMap<>();
                            for(int i = 0; i < lengthType; i++) {
                                JSONObject typeToolObject = jsonPig.getJSONObject(i);
                                short id = (short) typeToolObject.getInt("id");
                                String type = typeToolObject.getString("type");
                                hmTypeTool.put(type, id);
                                listTypeTool.add(type);
                            }

                            toolFragment.setHsTypeTool(hmTypeTool);
                            toolFragment.setListTypeTool(listTypeTool);

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

    public void eliminarExistenciasMedicina(final MainMenuActivity context, final short idMedicine) {

        RequestQueue queue = Volley.newRequestQueue(context);
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Eliminando existencias...");
        progressDialog.show();
        try{


            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.PUT,
                    "https://softpig.herokuapp.com/api/remove_medicine/"+ idMedicine,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                int respo = response.getInt("status");
                                Toast.makeText(context, "Existencias eliminadas", Toast.LENGTH_SHORT).show();
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

    public void inflarAlarmFragment(final MainMenuActivity context, final AlarmFragment alarmFragment,
                                    final short idEmployee, final SwipeRefreshLayout refreschListMedicine,
                                    final boolean inflar) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        if(inflar){

            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }
        String url = URLAPI+"alarm_list/"+ idEmployee;
       if(idEmployee < 10)
           url = URLAPI+"alarm_list/0"+ idEmployee;

        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            ArrayList<Alarm> listAlarms = new ArrayList<>();
                            JSONArray jsonAlarm = response.getJSONArray("alarms");
                            for(int i = 0; i < jsonAlarm.length(); i++) {
                                JSONObject alarmObject = jsonAlarm.getJSONObject(i);
                                short idAlarm = (short) alarmObject.getInt("id");
                                String date = alarmObject.getString("date");
                                String hour = alarmObject.getString("hour");
                                String issue = alarmObject.getString("issue");

                                listAlarms.add(new Alarm(idAlarm, idEmployee, date, hour, issue));
                            }

                            alarmFragment.setListAlarm(listAlarms);

                            if(inflar){
                                context.inflarFragment(alarmFragment);
                                progressDialog.dismiss();
                            }else{
                                alarmFragment.notificarAdapter();
                                refreschListMedicine.setRefreshing(false);
                            }


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
    }

    public void eliminarAlarmPerson(final MainMenuActivity context, final short idEmployee) {

        RequestQueue queue = Volley.newRequestQueue(context);
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Eliminando alarm...");
        progressDialog.show();
        try{
            String url = "https://softpig.herokuapp.com/api/remove_alarm/"+idEmployee;
            if(idEmployee < 10)
                url = "https://softpig.herokuapp.com/api/remove_alarm/0"+idEmployee;


            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.DELETE,
                    url,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                int respo = response.getInt("status");

                                    Toast.makeText(context, "alarma eliminada", Toast.LENGTH_SHORT).show();

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

    public boolean iniciarPigActivityFemale(final MainMenuActivity context,final  Female female) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        String url = URLAPI + "male_list";

        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray jsonMales = response.getJSONArray("males");
                            MainMenuPresenter.this.listIdMale = new String [jsonMales.length()];
                            for(int i = 0; i < jsonMales.length(); i++) {
                                JSONObject maleObject = jsonMales.getJSONObject(i);
                                short id = (short) maleObject.getInt("id");
                                MainMenuPresenter.this.listIdMale[i] = String.valueOf(id);
                            }
                            Intent i = new Intent();
                            i.setClass(context, PigActivity.class);
                            i.putExtra("Female", female);
                            i.putExtra("fragment", "Female");
                            i.putExtra("listIdMale", MainMenuPresenter.this.listIdMale);
                            context.startActivity(i);
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

    public void addAlarm(final MainMenuActivity context, final short id_employee, final String date, final String hour, final String issue){
        RequestQueue queue = Volley.newRequestQueue(context);
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Agregando alarma...");
        progressDialog.show();
        try{

            HashMap<String, String> params = new HashMap();
            params.put("id_Employee", String.valueOf(id_employee));
            params.put("date", date);
            params.put("hour", hour);
            params.put("issue", issue);
            params.put("Content-Type","application/json");

            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    "https://softpig.herokuapp.com/api/add_alarm",
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
