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

import java.util.ArrayList;
import java.util.List;

public class AddToolEmployeeDialog extends AppCompatDialogFragment implements AdapterView.OnItemSelectedListener{
    private TextView tvNameEmployee;
    private Spinner spArticle;
    private List<String> listNameArticle;
    private String [] stArticle;
    private AddToolEmployeeListerner listener;
    private ArrayAdapter<String> comboAdapterArticle;
    private String nameArticle;
    private String nameEmpleado;

    public AddToolEmployeeDialog(String nameEmpleado){
        this.nameEmpleado = nameEmpleado;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_article_employee, null);


        listNameArticle = new ArrayList<>();
        listNameArticle.add("Seleccione un article");
        listNameArticle.add("Escoba");
        listNameArticle.add("Trapero");
        listNameArticle.add("Recogedor");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listNameArticle);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        tvNameEmployee = view.findViewById(R.id.tv_name_add_tool_employee);
        tvNameEmployee.setText(nameEmpleado);
        spArticle = view.findViewById(R.id.sp_article);
        spArticle.setAdapter(adapter);

        spArticle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nameArticle = "";
                if(position > 0)
                    nameArticle = (String) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                nameArticle="";
            }
        });

        builder.setView(view)
                .setTitle("Agregar Articulo")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(nameArticle.isEmpty())
                            Toast.makeText(AddToolEmployeeDialog.this.getContext(), "Debe seleccionar una herramienta para agregar...", Toast.LENGTH_SHORT).show();
                        else{
                            listener.agregarArticulo(nameArticle);
                        }


                    }
                });


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (AddToolEmployeeListerner) context;
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