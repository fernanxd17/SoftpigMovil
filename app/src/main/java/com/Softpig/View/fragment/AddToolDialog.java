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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.Softpig.Model.Tool;
import com.Softpig.R;

import java.util.ArrayList;
import java.util.List;

public class AddToolDialog extends AppCompatDialogFragment implements AdapterView.OnItemSelectedListener{
    private TextView tvNameEmployee;
    private Spinner spArticle;
    private EditText etCopias;
    private List<Tool> listTool;
    private List<String> listNameArticle;
    private AddToolEmployeeListerner listener;
    private ArrayAdapter<String> comboAdapterArticle;
    private String nameArticle;
    private String nameEmpleado;

    public AddToolDialog(String nameEmpleado){
        this.nameEmpleado = nameEmpleado;
    }

    public AddToolDialog(List<Tool> listTool){
        this.listTool = listTool;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_tool, null);

        llenarListaConNombres();



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listNameArticle);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        etCopias = view.findViewById(R.id.tv_num_copias);
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
                        if(nameArticle.isEmpty() || etCopias.getText().toString().isEmpty())
                            Toast.makeText(AddToolDialog.this.getContext(), "Complete los campos...", Toast.LENGTH_SHORT).show();
                        else if(Integer.parseInt(etCopias.getText().toString()) < 0) {
                            Toast.makeText(AddToolDialog.this.getContext(), "Cantidad debe ser mayor a 0...", Toast.LENGTH_SHORT).show();
                        }else{
                                listener.agregarArticulo(nameArticle);
                        }
                    }
                });


        return builder.create();
    }

    private void llenarListaConNombres() {
        listNameArticle = new ArrayList<>();
        listNameArticle.add("Seleccione un article");
        for(Tool tool: listTool){
            listNameArticle.add(tool.getName());
        }

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