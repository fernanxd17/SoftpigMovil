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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ToolFragment extends Fragment {

    private TextView tvNameTool;
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
    private HashMap<String, Tool> hmListTool;
    private Short [] idTypeTool;
    private View viewTool;
    private TextView tv_noTool;
    private List<String> listTypeTool;
    private List<String> listNameTool;
    private String typeArticle;
    private Button btCancelar, btAgregar;
    private ArrayAdapter<String> adapter;
    private View viewDialog;

    public ToolFragment(boolean toolEmployee){
        this.toolEmployee = toolEmployee;
        this.hmTypeTool = new HashMap<>();
        this.hmListTool = new HashMap<>();
        this.listTool = new ArrayList<>();
    }

    public void setContext(Context context){
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewTool =  inflater.inflate(R.layout.fragment_list_tools, container, false);

        if(!toolEmployee){
            if (listTool.isEmpty()){
                tv_noTool = viewTool.findViewById(R.id.tv_noTools);
                tv_noTool.setText("No hay herramientas en el inventario");
            }else {
                toolAdapter = new ToolAdapter(listTool, toolEmployee, context);
                recyclerArticle = viewTool.findViewById(R.id.recyclerArticle);
                recyclerArticle.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerArticle.setAdapter(toolAdapter);
            }
        }else {
            fbAddArticle = viewTool.findViewById(R.id.fb_add_tool_employee);
            fbAddArticle.show();
            fbAddArticle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                    View viewDialog = getLayoutInflater().inflate(R.layout.add_tool_employee, null);
                    adapter = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_spinner_item, listNameTool);
                    tvNameTool = viewDialog.findViewById(R.id.et_name_add_tool);
                    spNameTool = viewDialog.findViewById(R.id.sp_article);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    etCopias = viewDialog.findViewById(R.id.tv_num_copias);
                    btAgregar = viewDialog.findViewById(R.id.bt_agregar);
                    btCancelar = viewDialog.findViewById(R.id.bt_cancelar);

                    spNameTool.setAdapter(adapter);
                    spNameTool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                            String nameTool = tvNameTool.getText().toString();
                            String copias =  etCopias.getText().toString();
                            if(nameTool.isEmpty() || copias.isEmpty() || typeArticle.isEmpty())
                                Toast.makeText(getContext(), "Complete o seleccione todos los campos...", Toast.LENGTH_SHORT).show();
                            else if(Integer.parseInt(copias) < 1) {
                                Toast.makeText(getContext(), "Cantidad debe ser mayor a 0...", Toast.LENGTH_SHORT).show();
                            }else{
                                agregarTool(nameTool, copias);
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



    private void agregarTool(String nameTool, String copias) {

        ((MainMenuActivity)getActivity()).agregarTool(nameTool, (short) hmTypeTool.get(nameTool), copias);
    }

    public void setListTypeTool(List <String> listTypeTool){
        this.listTypeTool = listTypeTool;
    }

    public void setListNameTool(List <String> listNameTool){
        this.listNameTool = listNameTool;
    }


    public void setListTool(List<Tool> listTool){
        this.listTool = listTool;
    }

    public void setHsTypeTool(HashMap<String, Short> hmTypeTool){
        this.hmTypeTool = hmTypeTool; }
    public void setHmListTool(HashMap<String, Tool> hmListTool){
        this.hmListTool = hmListTool;
    }
    public ToolAdapter getToolAdapter() {
        return toolAdapter;
    }
}
