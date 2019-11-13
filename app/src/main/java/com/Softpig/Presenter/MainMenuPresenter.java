package com.Softpig.Presenter;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.Softpig.ConexionBD.ConexionSqlHelper;
import com.Softpig.Model.Employee;
import com.Softpig.Model.Female;
import com.Softpig.Model.Installation;
import com.Softpig.Model.Male;
import com.Softpig.Model.Pig;
import com.Softpig.Model.Race;
import com.Softpig.Model.Tool;
import com.Softpig.Utilidades.Util;
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
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MainMenuPresenter {

    private ConexionSqlHelper conn;
    private static boolean toolsUpdate;
    private RaceFragment raceFragment;
    private InstallationFragment installationFragment;
    private FemaleFragment femaleFragment;
    private MaleFragment maleFragment;
    private PigFragment pigFragment;
    private ToolFragment toolFragment;
    private EmployeeFragment employeeFragment;
    private ArrayList<Pig> listPig;
    private SimpleDateFormat simpleDateFormat;
    private static final String URLAPI = "https://softpig.herokuapp.com/api/";

    public MainMenuPresenter(Context context){
        conn = new ConexionSqlHelper(context, Util.NAME_BD, null,1);
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
                            simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
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
                                Date birthDate = simpleDateFormat.parse(birth);
                                String acquisition= pigObject.getString("acquisitionDate");
                                Date acquisitionDate = simpleDateFormat.parse(acquisition);
                                System.out.println(birthDate.toString()+" "+ acquisitionDate.toString());
                                listPigs.add(new Pig(id, state, sex, weigth, race, growthPhase, pigState,
                                        health,installation, birthDate, acquisitionDate));
                            }
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

        if(!toolsUpdate){
            String url = URLAPI+ "article_list";

            JsonObjectRequest json = new JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                SQLiteDatabase softporc = conn.getWritableDatabase();
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

                                    contentValues.put("ID_ARTICLE", id);
                                    contentValues.put("type", type);
                                    contentValues.put("name", name);
                                    contentValues.put("quantity", quantity);

                                    softporc.insert("Article", "ID_ARTICLE",contentValues);
                                }

                                softporc.close();
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
        }else{
            SQLiteDatabase softporc = conn.getWritableDatabase();

        }



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
                             simpleDateFormat = new SimpleDateFormat();
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
                                if (listPig.get(i).getIdPig() == id) {
                                    Pig pig = listPig.get(i);
                                   listFemales.add(new Female(id, virgin, gestation, pig.getSex(), pig.getWeigth(), pig.getRace(), pig.getGrowthPhase(),
                                           pig.getPigState(), pig.getHealth(), pig.getInstallation(), pig.getBirthDate(), pig.getAcquisitionDate()));
                                }
                            }
                            femaleFragment = new FemaleFragment(listFemales);
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

                            simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
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
                                Date birthDate = simpleDateFormat.parse(birth);
                                String acquisition= pigObject.getString("acquisitionDate");
                                Date acquisitionDate = simpleDateFormat.parse(acquisition);
                                System.out.println(birthDate.toString()+" "+ acquisitionDate.toString());
                                MainMenuPresenter.this.listPig.add(new Pig(id, state, sex, weigth, race, growthPhase, pigState,
                                        health,installation, birthDate, acquisitionDate));
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
                    context.inflarFragment(new ErrorFragment());


                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(json);
    }

    public boolean inflarMalesFragment(final MainMenuActivity context) {
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
                            ArrayList<Male> listMale = new ArrayList<>();
                            JSONArray jsonMales = response.getJSONArray("males");

                            for(int i = 0; i < jsonMales.length(); i++) {
                                JSONObject maleObject = jsonMales.getJSONObject(i);
                                short id = (short) maleObject.getInt("id");
                                String conformation = maleObject.getString("conformation");
                                if (listPig.get(i).getIdPig() == id) {
                                    Pig pigMale = listPig.get(i);
                                    /*listMale.add(new Male(id, conformation,pigMale.getSex(),pigMale.getWeigth() , pigMale.getRace(), pigMale.getGrowthPhase(),
                                            pigMale.getPigState(), pigMale.getHealth(), pigMale.getInstallation(),pigMale.getBirthDate(), pigMale.getAcquisitionDate()));*/
                                }
                            }
                            maleFragment = new MaleFragment(listMale);
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

                            short [] valores = {adminNum, operNum, numToolPerson, toolInventario, numInstallation, typeInstallation};
                            dashBoardFragment.setValores(valores);
                            context.inflarFragment(dashBoardFragment);
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
        //Se hace loader
        //volley
        /*Para tarer los datos de la instalación*/

        //employee
        //articles
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
