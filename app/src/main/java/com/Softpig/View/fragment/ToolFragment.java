package com.Softpig.View.fragment;


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

import com.Softpig.Model.Employee;
import com.Softpig.Model.Tool;
import com.Softpig.Presenter.Adapters.ToolAdapter;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;
import com.Softpig.View.ProfileActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ToolFragment extends Fragment {

    private EditText etNameTool;
    private Spinner spTypeTool;
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
    private List<String> listNameArticle;
    private String typeArticle;
    private Button btCancelar, btAgregar;

    public ToolFragment() {
        listTool = new ArrayList<>();
    }

    public void setContext(Context context){
        this.context = context;
    }


    public ToolFragment(boolean toolEmployee){
        this.toolEmployee = toolEmployee;
        this.hmTypeTool = new HashMap<String, Short>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewTool =  inflater.inflate(R.layout.fragment_list_tools, container, false);

        if (listTool.isEmpty()){
            tv_noTool = viewTool.findViewById(R.id.tv_noTools);
            tv_noTool.setText("No hay herramientas en el invetario");
        }else{
            toolAdapter = new ToolAdapter(listTool, toolEmployee, context);
            recyclerArticle = viewTool.findViewById(R.id.recyclerArticle);
            recyclerArticle.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerArticle.setAdapter(toolAdapter);
            fbAddArticle = viewTool.findViewById(R.id.fb_add_tool_employee);
            fbAddArticle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*if(toolEmployee)
                        openDialogAddArticlePerson();
                    else
                        openDialogAddTool();*/

                    final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                    View mView = getLayoutInflater().inflate(R.layout.add_tool, null);
                    llenarListaConTipos();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_spinner_item, listNameArticle);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    etNameTool = mView.findViewById(R.id.et_name_add_tool);
                    etCopias = mView.findViewById(R.id.tv_num_copias);
                    spTypeTool = mView.findViewById(R.id.sp_article);
                    btAgregar = mView.findViewById(R.id.bt_agregar);
                    btCancelar = mView.findViewById(R.id.bt_cancelar);


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

                    alert.setView(mView);

                    final AlertDialog alertDialog = alert.create();
                    alertDialog.setCanceledOnTouchOutside(false);

                    btCancelar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            System.out.println("No agregado");
                        }
                    });

                    btAgregar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            System.out.println("Agregado");
                        }
                    });


                }
            });
        }

        return viewTool;
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

    public void openDialogAddArticlePerson() {
        Employee empleado = ((ProfileActivity)getActivity()).getEmployee();
        String nombreEmpleado = empleado.getFirstName() + " " + empleado.getLastName();
        AddToolDialog addToolDialog = new AddToolDialog();
    }

    public void openDialogAddTool(){
        AddToolDialog addToolDialog = new AddToolDialog(hmTypeTool);
       // addToolDialog.show(this, "sss");
    }

   /* @Override
    public void addTool(String nameTool, String copias, String typeArticle) {
        ((ProfileActivity) getActivity()).agregarArticle(nameTool, copias, Integer.parseInt(typeArticle));
    }*/

    public void setListTool(ArrayList<Tool> listTool){
        this.listTool = listTool;
    }

    public void setHsTypeTool(HashMap<String, Short> hmTypeTool){ this.hmTypeTool = hmTypeTool; }

    public ToolAdapter getToolAdapter() {
        return toolAdapter;
    }
}
