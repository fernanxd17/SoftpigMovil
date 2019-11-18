package com.Softpig.View.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.Softpig.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddToolDialog extends AppCompatDialogFragment implements AdapterView.OnItemSelectedListener{
    private EditText etNameTool;
    private Spinner spTypeTool;
    private EditText etCopias;
    private List<String> listNameArticle;
    private HashMap<String, Short> hmTypeTool;
    private AddToolListerner listener;
    private String typeArticle;

    public AddToolDialog(){ }

    public AddToolDialog(HashMap<String, Short> hmTypeTool){
        this.hmTypeTool = hmTypeTool;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_tool, null);

        llenarListaConTipos();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listNameArticle);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etNameTool = view.findViewById(R.id.et_name_add_tool);
        etCopias = view.findViewById(R.id.tv_num_copias);
        spTypeTool = view.findViewById(R.id.sp_article);
        spTypeTool.setAdapter(adapter);

        spTypeTool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeArticle = "";
                if(position > 0)
                    typeArticle = (String) parent.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                typeArticle="";
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
                        String nameTool = etNameTool.getText().toString();
                        String copias =  etCopias.getText().toString();
                        if(nameTool.isEmpty() || copias.isEmpty() || typeArticle.isEmpty())
                            Toast.makeText(AddToolDialog.this.getContext(), "Complete o seleccione todos los campos...", Toast.LENGTH_SHORT).show();
                        else if(Integer.parseInt(copias) < 1) {
                            Toast.makeText(AddToolDialog.this.getContext(), "Cantidad debe ser mayor a 0...", Toast.LENGTH_SHORT).show();
                        }else{
                            agregarArticulo(nameTool, copias);

                        }
                    }


                });


        return builder.create();
    }

    private void agregarArticulo(String nameTool, String copias) {
        listener.addTool(nameTool, copias, typeArticle);
    }

    private void llenarListaConTipos() {
        listNameArticle = new ArrayList<>();
        listNameArticle.add("Seleccione categoria");
        Iterator<Map.Entry<String,Short>> it = hmTypeTool.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String,Short> e = it.next();
            listNameArticle.add(e.getKey());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (AddToolListerner) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public interface AddToolListerner {
        void addTool(String nameTool, String copias, String typeTool);
    }




}