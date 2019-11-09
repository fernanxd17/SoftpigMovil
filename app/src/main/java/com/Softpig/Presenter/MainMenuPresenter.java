package com.Softpig.Presenter;

import android.app.ProgressDialog;

import com.Softpig.Model.Employee;
import com.Softpig.Model.Female;
import com.Softpig.Model.Installation;
import com.Softpig.Model.Male;
import com.Softpig.Model.Pig;
import com.Softpig.Model.Race;
import com.Softpig.Model.Tool;
import com.Softpig.View.MainMenuActivity;
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
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainMenuPresenter {

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

    public MainMenuPresenter(){

    }

    public boolean inflarRacesFragment(final MainMenuActivity context) {

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
                                raceFragment = new RaceFragment(listRaces);
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

    public boolean inflarInstallationsFragment(final MainMenuActivity context) {

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
                            installationFragment = new InstallationFragment(listInstallations);
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

    public boolean inflarPigFragment(final MainMenuActivity context) {

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
                            simpleDateFormat = new SimpleDateFormat();
                            JSONArray jsonPig = response.getJSONArray("pigs");
                            for(int i = 0; i < jsonPig.length(); i++) {
                                JSONObject pigObject = jsonPig.getJSONObject(i);
                                short id = (short) pigObject.getInt("id");
                                String state = pigObject.getString("state");
                                String sex = pigObject.getString("sex");
                                short weigth = (short) pigObject.getInt("weigth");
                                String race = pigObject.getString("race");
                                String growthPhase = pigObject.getString("growthPhase");
                                String pigState = pigObject.getString("pigState");
                                String health = pigObject.getString("health");
                                String installation = pigObject.getString("installation");
                                String birth = pigObject.getString("birthDate");
                                Date birthDate = simpleDateFormat.parse(birth);
                                String acquisition= pigObject.getString("acquisitionDate");
                                Date acquisitionDate = simpleDateFormat.parse(acquisition);
                                listPigs.add(new Pig(id, sex, weigth, race, growthPhase, pigState,
                                        health,installation, birthDate, acquisitionDate));
                            }
                            pigFragment = new PigFragment(listPigs);
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

    public boolean inflarArticlesFragment(final MainMenuActivity context) {

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
                            ArrayList<Tool> listTool = new ArrayList<>();
                            JSONArray jsonTools = response.getJSONArray("articles");
                            for(int i = 0; i < jsonTools.length(); i++) {
                                JSONObject toolObject = jsonTools.getJSONObject(i);
                                short id = (short) toolObject.getInt("id");
                                String type = toolObject.getString("type");
                                String name = toolObject.getString("name");
                                short quantity = (short) toolObject.getInt("quantity");
                                short available = (short) toolObject.getInt("available");
                                short loan = (short) toolObject.getInt("loan");
                                listTool.add(new Tool(id, type,name, quantity, available, loan));
                            }
                            toolFragment = new ToolFragment(listTool);
                            context.inflarFragment(toolFragment);
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

    public boolean inflarEmployeesFragment(final MainMenuActivity context) {

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
                                short id = (short) employeeObject.getInt("id");
                                String status = employeeObject.getString("state");
                                String contract = employeeObject.getString("contract");
                                String hoursWorked = employeeObject.getString("hoursWorked");
                                String admission = employeeObject.getString("dateAdmission");
                                Date admissionDate = new Date();
                                if(!admission.equalsIgnoreCase("null")){
                                    admissionDate = simpleDateFormat.parse(admission);
                                }



                                String off = employeeObject.getString("dateOff");
                                Date dateOff = new Date();
                                if(!off.equalsIgnoreCase("null")){
                                    dateOff = simpleDateFormat.parse(off);
                                }

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
                                String role = employeeObject.getString("role");
                                String instalation = employeeObject.getString("instalation");

                                System.out.println("fecha :"+ admissionDate);
                                listEmployee.add(new Employee(id, role,contract, hoursWorked, status, admissionDate, dateOff, document,
                                        sex, firstName, secondName, fatherLastName, motherLastName, email, phone, celPhone, instalation, salary ));
                            }
                            employeeFragment = new EmployeeFragment(listEmployee);
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

    public boolean inflarFemalesFragment(final MainMenuActivity context) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        String url = URLAPI + "female_lis";

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
                                    Pig pigFemale = listPig.get(i);
                                    listFemales.add(new Female(id, virgin, gestation, pigFemale.getSex(), pigFemale.getWeigth(), pigFemale.getRace(), pigFemale.getGrowthPhase(),
                                            pigFemale.getPigState(), pigFemale.getHealth(), pigFemale.getInstallation(), pigFemale.getBirthDate(), pigFemale.getAcquisitionDate()));
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
                                    listMale.add(new Male(id, conformation,pigMale.getSex(),pigMale.getWeigth() , pigMale.getRace(), pigMale.getGrowthPhase(),
                                            pigMale.getPigState(), pigMale.getHealth(), pigMale.getInstallation(),pigMale.getBirthDate(), pigMale.getAcquisitionDate()));
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


}
