package com.Softpig.View.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.Softpig.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AddHoursWorkedDialog extends AppCompatDialogFragment implements AdapterView.OnItemSelectedListener {

    private TextView tvNameEmployee;
    //private AddHoursWorkedListerner listener;
    private ArrayAdapter<String> comboAdapterArticle;
    private String nameArticle;
    private String nameEmpleado;

    public AddHoursWorkedDialog(String nameEmpleado){
        this.nameEmpleado = nameEmpleado;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_hours_worked, null);


        tvNameEmployee = view.findViewById(R.id.tv_name_add_tool_employee);
        tvNameEmployee.setText(nameEmpleado);

        builder.setView(view)
                .setTitle("Agregar horas trabajadas")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(nameArticle.isEmpty())
                            Toast.makeText(AddHoursWorkedDialog.this.getContext(), "Ingresa por favor las horas", Toast.LENGTH_SHORT).show();
                        else{
                            //listener.agregarArticulo(nameArticle);
                        }


                    }
                });


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            //listener = (AddToolEmployeeListerner) context;
        } catch (ClassCastException e) {

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public interface AddToolEmployeeListerner {
        void agregarArticulo(String nameArticle);
    }

    private boolean addHoursWorked(Context context, String person, String hours){
        RequestQueue queue = Volley.newRequestQueue(context);
       // progressDialog.setMessage("Obteniendo datos...");
        try{

            HashMap<String, String> params = new HashMap();
            params.put("id", person);
            params.put("hours", hours);
            params.put("Content-Type","application/json");

            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    "https://softpig.herokuapp.com/api/hours_employee",
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

                                //Employee employee = new Employee(id, role,contract, hoursWorked, status, null, null, document,
                                  //      sex, firstName, secondName, fatherLastName, motherLastName, email, phone, celPhone, instalation, salary );
                                //context.startApp(employee);



                         //       progressDialog.dismiss();



                            } catch (JSONException e) {
                                e.printStackTrace();
                               // Toast.makeText(context, "Error en la APP, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                               // progressDialog.dismiss();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            //progressDialog.dismiss();
                           // Toast.makeText(context, "Error obteniendo datos, Intentelo mas tarde", Toast.LENGTH_LONG).show();
                        }
                    });
            queue.add(arrayRequest);
        }catch(Exception e){
            Toast.makeText(context, "Error interno, Intentelo mas tarde", Toast.LENGTH_LONG).show();
           // progressDialog.dismiss();
        }

        return true;
    }

}
