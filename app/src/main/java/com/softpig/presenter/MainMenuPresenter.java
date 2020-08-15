package com.softpig.presenter;

import org.json.*;
import java.util.*;
import com.softpig.model.*;
import com.android.volley.*;
import android.widget.Toast;
import android.content.Intent;
import android.app.ProgressDialog;
import com.softpig.View.Fragment.*;
import com.softpig.View.PigActivity;
import com.softpig.View.MainMenuActivity;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.JsonObjectRequest;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainMenuPresenter {

    private static boolean toolsUpdate;
    private MaleFragment maleFragment;
    private  ArrayList<Pig> listPig;
    private HashMap<String, Short> hmTypeTool;
    private String [] listIdMale;
    public static final int MY_DEFAULT_TIMEOUT = 10000;
    private static final String URLAPI = "https://softpig.herokuapp.com/api/";

    public MainMenuPresenter(){
        toolsUpdate = false;
    }

    public boolean inflarRacesFragment(final MainMenuActivity context, final RaceFragment raceFragment,
                                       final SwipeRefreshLayout refreshRace) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        if(refreshRace == null){
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }


        String url = URLAPI + "race_list";

        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {

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
                        if(refreshRace == null){
                            context.inflarFragment(raceFragment);
                            progressDialog.dismiss();
                        }else{
                            raceFragment.notificarAdapter();
                            refreshRace.setRefreshing(false);
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
                response -> {
                    try {
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

    public boolean inflarFemalesFragment(final MainMenuActivity context, final FemaleFragment femaleFragment,
                                         final SwipeRefreshLayout refreshListFemale) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        if(refreshListFemale == null){
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        if(listPig == null){
            System.out.println("listPig");
            traerDatosPorcinos(context);
        }

        String url = URLAPI + "female_list";

        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {

                        ArrayList<Female> listFemales = new ArrayList<>();
                        JSONArray jsonFemale = response.getJSONArray("females");

                        for(int i = 0; i < jsonFemale.length(); i++) {
                            JSONObject femaleObject = jsonFemale.getJSONObject(i);
                            String stateFemale = femaleObject.getString("state");
                            if(stateFemale.equalsIgnoreCase("Baja"))
                                continue;
                            short id = (short) femaleObject.getInt("id");
                            String virgin = femaleObject.getString("virgin");
                            String gestation = femaleObject.getString("gestation");
                            Pig pig = MainMenuPresenter.this.buscarPig(id);
                            listFemales.add(new Female(id, virgin, gestation,stateFemale,pig.getState(), pig.getSex(), pig.getWeigth(), pig.getRace(), pig.getGrowthPhase(),
                                    pig.getPigState(), pig.getHealth(), pig.getInstallation(), pig.getBirthDate(), pig.getAcquisitionDate()));

                        }

                        femaleFragment.setListFemale(listFemales);

                        if(refreshListFemale == null){
                            context.inflarFragment(femaleFragment);
                            progressDialog.dismiss();
                        }else{
                            femaleFragment.notificarAdapter();
                            refreshListFemale.setRefreshing(false);
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

    public boolean inflarMalesFragment(final MainMenuActivity context, final MaleFragment maleFragment,
                                       final SwipeRefreshLayout refreshListMale, final Boolean inflar) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        if(inflar){
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }


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
                            if(inflar){
                                context.inflarFragment(maleFragment);
                                progressDialog.dismiss();
                            }else{
                                maleFragment.notificarAdapter();
                                refreshListMale.setRefreshing(false);
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
                response -> {

                    try {
                        JSONArray jsonDataEmployee = response.getJSONArray("dashboard");
                        JSONObject adminObject = jsonDataEmployee.getJSONObject(0);
                        short adminNum = (short) adminObject.getInt("administrativos");
                        JSONObject operaObject = jsonDataEmployee.getJSONObject(1);
                        short operNum = (short) operaObject.getInt("operativos");
                        JSONObject toolInventaryObject = jsonDataEmployee.getJSONObject(2);
                        short toolInventario =  (short) toolInventaryObject.getInt("items_inventory");
                        JSONObject toolPersonObject = jsonDataEmployee.getJSONObject(3);
                        short numToolPerson = (short)toolPersonObject.getInt("article_person");
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
                }, error -> {

            try { error.printStackTrace();
                context.inflarFragment(new ErrorFragment());
                progressDialog.dismiss();

            } catch (Exception e) {
                e.printStackTrace();
                progressDialog.dismiss();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        json.setRetryPolicy(new DefaultRetryPolicy(
                MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(json);

    }

    public void eliminarArticulo(final ToolFragment toolFragment,final int idTool, final String table) {

        RequestQueue queue = Volley.newRequestQueue(toolFragment.getContext());
        final ProgressDialog progressDialog = new ProgressDialog(toolFragment.getContext());
        progressDialog.setMessage("Eliminando Herramienta...");
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
                    response -> {
                        try {
                            response.getInt("status");
                            toolFragment.eliminarToolList(idTool);
                            toolFragment.notificarAdapter();
                            progressDialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(toolFragment.getContext(), "Error en la APP", Toast.LENGTH_LONG).show();
                        }
                    },
                    error -> {
                        error.printStackTrace();
                        progressDialog.dismiss();
                        Toast.makeText(toolFragment.getContext(), "Error obteniendo datos, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                    });

           /* arrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                    MY_DEFAULT_TIMEOUT,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/

            queue.add(arrayRequest);

        }catch(Exception e){
            Toast.makeText(toolFragment.getContext(), "Error interno, Intentelo mas tarde", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }

    }

    private void traerDatosPorcinos(final MainMenuActivity context) {

        String url = URLAPI+"pig_list";

        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {

                    try {
                        listPig = new ArrayList<>();
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

                            listPig.add(new Pig(id, state, sex, weigth, race, growthPhase, pigState,
                                    health,installation, birth, acquisition));

                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                        context.inflarFragment(new ErrorFragment());

                    }
                }, error -> {

            try {
                error.printStackTrace();
                context.inflarFragment(new ErrorFragment());


            } catch (Exception e) {
                e.printStackTrace();

            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(json);
    }

    private Pig buscarPig(short id) {
        if(listPig != null){
            for (Pig pig : listPig)
            {
                if(pig.getIdPig() == id){
                    return pig;
                }
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

    public void eliminarExistenciasMedicina(final MedicineFragment medicineFragment, final short idMedicine) {

        RequestQueue queue = Volley.newRequestQueue(medicineFragment.getContext());
        final ProgressDialog progressDialog = new ProgressDialog(medicineFragment.getContext());
        progressDialog.setMessage("Eliminando existencias...");
        progressDialog.show();
        try{


            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.PUT,
                    "https://softpig.herokuapp.com/api/remove_medicine/"+ idMedicine,
                    null,
                    response -> {

                        try {
                            response.getInt("status");
                            medicineFragment.eliminarMedicina(idMedicine);
                            medicineFragment.notificarAdapter();
                            Toast.makeText(medicineFragment.getContext(), "Existencias eliminadas", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(medicineFragment.getContext(), "Error en la APP, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    },
                    error -> {
                        error.printStackTrace();
                        progressDialog.dismiss();
                        Toast.makeText(medicineFragment.getContext(), "Error obteniendo datos, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                    });
            queue.add(arrayRequest);
        }catch(Exception e){
            Toast.makeText(medicineFragment.getContext(), "Error interno, Intentelo mas tarde", Toast.LENGTH_LONG).show();
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
                response -> {

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
    }

    public void eliminarAlarmPerson(final AlarmFragment alarmFragment, final short idEmployee){

        RequestQueue queue = Volley.newRequestQueue(alarmFragment.getContext());
        final ProgressDialog progressDialog = new ProgressDialog(alarmFragment.getContext());
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
                    response -> {

                        try {
                            int respo = response.getInt("status");
                            alarmFragment.eliminarAlarma(idEmployee);
                            alarmFragment.notificarAdapter();
                            Toast.makeText(alarmFragment.getContext(), "alarma eliminada", Toast.LENGTH_SHORT).show();
                            System.out.println("respo: "+ respo);
                            progressDialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(alarmFragment.getContext(), "Error en la APP, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    },
                    error -> {
                        error.printStackTrace();
                        progressDialog.dismiss();
                        Toast.makeText(alarmFragment.getContext(), "Error obteniendo datos, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                    });
            queue.add(arrayRequest);
        }catch(Exception e){
            Toast.makeText(alarmFragment.getContext(), "Error interno, Intentelo mas tarde", Toast.LENGTH_LONG).show();
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
                response -> {

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

    public void addAlarm(final AlarmFragment alarmFragment, final Alarm alarm){
        RequestQueue queue = Volley.newRequestQueue(alarmFragment.getContext());
        final ProgressDialog progressDialog = new ProgressDialog(alarmFragment.getContext());
        progressDialog.setMessage("Agregando alarma...");
        progressDialog.show();
        try{

            HashMap<String, String> params = new HashMap();
            params.put("id_Employee", String.valueOf(alarm.getIdEmployee()));
            params.put("date", alarm.getDate());
            params.put("hour", alarm.getHour());
            params.put("issue", alarm.getIssue());
            params.put("Content-Type","application/json");

            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    "https://softpig.herokuapp.com/api/add_alarm",
                    new JSONObject(params),
                    response -> {
                        try {
                            response.getInt("status");
                            alarmFragment.addAlarm(alarm);
                            alarmFragment.notificarAdapter();
                            Toast.makeText(alarmFragment.getContext(), "Alarma agregada con exito", Toast.LENGTH_SHORT);
                            progressDialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(alarmFragment.getContext(), "Error en la APP, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    },
                    error -> {
                        error.printStackTrace();
                        progressDialog.dismiss();
                        Toast.makeText(alarmFragment.getContext(), "Error obteniendo datos, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                    });

            queue.add(arrayRequest);
        }catch(Exception e){
            Toast.makeText(alarmFragment.getContext(), "Error interno, Intentelo mas tarde", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }
    }

    public void presentarInformeGeneral(final MainMenuActivity context, final InformeGeneralFragment informeGeneralFragment) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Generando informe...");
        progressDialog.show();

        String url = URLAPI + "general_report";

        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {

                        JSONArray jsonGeneralReport = response.getJSONArray("general-report");

                        JSONObject datosPorcinos = jsonGeneralReport.getJSONObject(0);
                        short totalPigs = (short) datosPorcinos.getInt("pigs_farm");
                        short totalMales = (short) datosPorcinos.getInt("males_farm");
                        short totalFemales = (short)datosPorcinos.getInt("females_farm");
                        short malesRpd = (short) datosPorcinos.getInt("active_males");
                        short femalesRpd = (short) datosPorcinos.getInt("active_females");
                        short promNaci = (short) datosPorcinos.getInt("births");
                        short promGest = (short) datosPorcinos.getInt("gestations");
                        short promCelos = (short) datosPorcinos.getInt("heats");

                        JSONObject lechonesObject = jsonGeneralReport.getJSONObject(1);
                        short totalLechones = (short) lechonesObject.getInt("Lechon");

                        JSONObject marranosObject = jsonGeneralReport.getJSONObject(2);
                        short totalMarranos = (short) marranosObject.getInt("Marrano");

                        GeneralReport generalReport = new GeneralReport(totalPigs, totalMales, totalFemales,
                                malesRpd, femalesRpd, totalLechones, totalMarranos, promNaci, promCelos, promGest);

                        informeGeneralFragment.setReport(generalReport);
                        context.inflarFragment(informeGeneralFragment);
                        progressDialog.dismiss();

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
    }

    public void presentarInformeFertilidad( final MainMenuActivity context, final InformeFertilidadFragment informeFertilidadFragment) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Creando informe...");
        progressDialog.show();

        String url = URLAPI + "fertility_report";

        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {

                        JSONArray jsonGeneralReport = response.getJSONArray("fertility-report");

                        JSONObject datosPorcinos = jsonGeneralReport.getJSONObject(0);
                        short prombabies = (short) datosPorcinos.getInt("noBabies");
                        short promMommy = (short) datosPorcinos.getInt("noMommy");
                        short promDead = (short)datosPorcinos.getInt("noDead");
                        short promWeigth = (short) datosPorcinos.getInt("weigth");
                        short promBirthsFemale = (short) datosPorcinos.getInt("birth-female");
                        short promBirthsMale = (short) datosPorcinos.getInt("birth-male");

                        FertilityReport fertilityReport = new FertilityReport(prombabies, promMommy, promDead,
                                promWeigth, promBirthsFemale, promBirthsMale);

                        informeFertilidadFragment.setReport(fertilityReport);
                        context.inflarFragment(informeFertilidadFragment);
                        progressDialog.dismiss();

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
    }

    public void inflarReportFragment(final MainMenuActivity context,final  ReportFragment reportFragment) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Generando informe...");
        progressDialog.show();

        String url = URLAPI + "report_data";

        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {

                        JSONArray jsonGeneralReport = response.getJSONArray("report-data");

                        JSONObject datosBebesDead = jsonGeneralReport.getJSONObject(0);
                        short noBabies = (short) datosBebesDead.getInt("noBabies");
                        short noDead = (short) datosBebesDead.getInt("noDead");

                        JSONObject fertilidad = jsonGeneralReport.getJSONObject(1);
                        short births = (short) fertilidad.getInt("births");
                        short gestations = (short) fertilidad.getInt("gestations");

                        short [] valores = {noBabies, noDead, births, gestations};

                        reportFragment.setValores(valores);
                        context.inflarFragment(reportFragment);
                        progressDialog.dismiss();

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
    }

}
