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


}
