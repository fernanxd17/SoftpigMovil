package com.Softpig.View.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;


import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Softpig.Model.Tool;
import com.Softpig.Presenter.Adapters.ToolAdapter;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;
import com.Softpig.View.ProfileActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToolFragment extends Fragment {

    private TextView tvNameEmployee;
    private Spinner spNameTool;
    private EditText etCopias;
    private Context context;
    private RecyclerView recyclerArticle;
    public  ToolAdapter toolAdapter;
    private boolean toolEmployee = false;
    private FloatingActionButton fbAddArticle;
    private List<Tool> listTool;
    private String [] typeTool;
    private HashMap<String, Short> hmTypeTool;
    private Short [] idTypeTool;
    private View viewTool;
    private TextView tv_noTool;
    private List<String> listTypeTool;
    private String nameTool;
    private Button btCancelar, btAgregar;
    private ArrayAdapter<String> adapter;
    private View viewDialog;
    private HashMap<String, Short> hmNameTool;
    private List<String> listNameTool;
    public ToolFragment(boolean toolEmployee){
        this.toolEmployee = toolEmployee;
        this.hmTypeTool = new HashMap<>();
        this.listTool = new ArrayList<>();
        this.hmNameTool = new HashMap<>();
        this.listNameTool = new ArrayList<>();
    }

    public void setContext(Context context){
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewTool =  inflater.inflate(R.layout.fragment_list_tools, container, false);


        if (listTool.isEmpty()){
            tv_noTool = viewTool.findViewById(R.id.tv_noTools);
            tv_noTool.setText("No hay herramientas en el inventario");
        }else {
            toolAdapter = new ToolAdapter(listTool, toolEmployee, context);
            recyclerArticle = viewTool.findViewById(R.id.recyclerArticle);
            recyclerArticle.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerArticle.setAdapter(toolAdapter);
        }

        if(toolEmployee){
            fbAddArticle = viewTool.findViewById(R.id.fb_add_tool_employee);
            fbAddArticle.show();
            fbAddArticle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                    View viewDialog = getLayoutInflater().inflate(R.layout.add_tool_employee, null);
                    llenarListName();
                    adapter = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_spinner_item, listNameTool);

                    tvNameEmployee = viewDialog.findViewById(R.id.et_name_employee);
                    String nameEmployee = ((ProfileActivity)getContext()).getEmployee().getFirstName() + " "
                                            + ((ProfileActivity)getContext()).getEmployee().getLastName();
                    tvNameEmployee.setText(nameEmployee);
                    spNameTool = viewDialog.findViewById(R.id.sp_article);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    etCopias = viewDialog.findViewById(R.id.tv_num_copias);
                    btAgregar = viewDialog.findViewById(R.id.bt_agregar);
                    btCancelar = viewDialog.findViewById(R.id.bt_cancelar);

                    spNameTool.setAdapter(adapter);
                    spNameTool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            nameTool = "";
                            if(position > 0)
                                nameTool = (String) parent.getSelectedItem();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            nameTool="";
                        }
                    });

                    alert.setView(viewDialog);

                    final AlertDialog alertDialog = alert.create();
                    alertDialog.setCanceledOnTouchOutside(false);

                    btCancelar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });

                    btAgregar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            String copias =  etCopias.getText().toString();
                            if(copias.isEmpty() || nameTool.isEmpty() || nameTool.equalsIgnoreCase("Seleccione una herramienta"))
                                Toast.makeText(getContext(), "Complete o seleccione todos los campos...", Toast.LENGTH_SHORT).show();
                            else if(Integer.parseInt(copias) < 1) {
                                Toast.makeText(getContext(), "Cantidad debe ser mayor a 0...", Toast.LENGTH_SHORT).show();
                            }else{
                                agregarTool(hmNameTool.get(nameTool), copias);
                            }

                            alertDialog.dismiss();
                    }
                });

                alertDialog.show();

            }
        });
        }

        return viewTool;
    }



    private void agregarTool(short idTool, String copias) {

        ((ProfileActivity)getActivity()).agregarTool(idTool, copias);
    }

    public void setListTypeTool(List <String> listTypeTool){
        this.listTypeTool = listTypeTool;
    }

    private void llenarListName(){

        listNameTool.add("Seleccione una herramienta");
        for (Map.Entry<String, Short> entry : hmNameTool.entrySet()) {
            listNameTool.add(entry.getKey());
        }
    }


    public void setListTool(List<Tool> listTool){
        this.listTool = listTool;
    }

    public void setHsTypeTool(HashMap<String, Short> hmTypeTool){
        this.hmTypeTool = hmTypeTool; }

    public ToolAdapter getToolAdapter() {
        return toolAdapter;
    }

    public void setHmListNameTool(HashMap<String, Short> hmNameTool) {
        this.hmNameTool = hmNameTool;
    }
}
