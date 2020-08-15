package com.softpig;

import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.softpig.model.Employee;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

public  class  ControllerMaster {

    private static  final String  TAG = "ControllerMaster";

    public ControllerMaster(){

    }



    public  void login(final IndexActivity context,final FirebaseAuth mAuth, final boolean loginFirebase,  final String email, final String password) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Autenticando...");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {


                                loginEmployee(context,email,password, progressDialog);

                            }

                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            //updateUI(null);
                            progressDialog.dismiss();
                            Toast.makeText(context, "¡Verifica tus datos!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

   public  boolean loginEmployee( final IndexActivity context, final String email, final String password, final ProgressDialog progressDialog) {
       RequestQueue queue = Volley.newRequestQueue(context);
       progressDialog.setMessage("Obteniendo datos...");
        try{

            HashMap<String, String> params = new HashMap();
            params.put("user", email);
            params.put("password", password);
            params.put("Content-Type","application/json");

            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    "https://softpig.herokuapp.com/api",
                    new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            JSONArray jsonDataEmployee = null;
                            try {
                                jsonDataEmployee = response.getJSONArray("employee_data");
                                JSONObject employeeObject = jsonDataEmployee.getJSONObject(0);
                                short id = (short) employeeObject.getInt("id");
                                String status = employeeObject.getString("state");
                                String contract = employeeObject.getString("contract");
                                String hoursWorked = employeeObject.getString("hoursWorked");
                                String admission = employeeObject.getString("dateAdmission");
                                /*Date admissionDate;
                                if(!admission.equalsIgnoreCase("null")){
                                    admissionDate = simpleDateFormat.parse(admission);
                                }

                                String off = employeeObject.getString("dateOff");
                                Date dateOff = new Date();
                                if(!off.equalsIgnoreCase("null")){
                                    dateOff = simpleDateFormat.parse(off);
                                }
                                */
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

                                Employee employee = new Employee(id, role,contract, hoursWorked, status, null, null, document,
                                        sex, firstName, secondName, fatherLastName, motherLastName, email, phone, celPhone, instalation, salary );
                                context.startApp(employee);

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

        return true;
    }

    public  boolean  changePassword(){

        try{

            //Realiza la petion post de fragment_login a la API en Python, retorna el jsonObject

        }catch(Exception e){

            //Hubo un error conectandose al servidor
        }

       return true;
    }
}
