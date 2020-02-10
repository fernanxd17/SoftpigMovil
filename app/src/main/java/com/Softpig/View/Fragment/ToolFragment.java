package com.Softpig.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
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
    private SwipeRefreshLayout refreshListTool;

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
        refreshListTool = viewTool.findViewById(R.id.refresh_list_tool);
        refreshListTool.setOnRefreshListener(() -> {
            if(!toolEmployee)
                ((MainMenuActivity)getContext()).actualizarListaTool(refreshListTool);
            else
                ((ProfileActivity)getContext()).actualizarListaTool(refreshListTool);
        });


        tv_noTool = viewTool.findViewById(R.id.tv_noTools);
        tv_noTool.setText(listTool.size() + " Herramienta(s) Encontrada(s)");

        toolAdapter = new ToolAdapter(listTool, toolEmployee, context);
        recyclerArticle = viewTool.findViewById(R.id.recyclerArticle);
        recyclerArticle.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerArticle.setAdapter(toolAdapter);


        if(toolEmployee){
            fbAddArticle = viewTool.findViewById(R.id.fb_add_tool_employee);
            fbAddArticle.show();
            fbAddArticle.setOnClickListener(view -> {

                final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                View viewDialog = getLayoutInflater().inflate(R.layout.add_tool_employee, null);
                llenarListName();
                adapter = new ArrayAdapter<>(getContext(),
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

                btCancelar.setOnClickListener(view12 -> alertDialog.dismiss());

                btAgregar.setOnClickListener(view1 -> {

                    String copias =  etCopias.getText().toString();
                    if(copias.isEmpty() || nameTool.isEmpty() || nameTool.equalsIgnoreCase("Seleccione una herramienta"))
                        Toast.makeText(getContext(), "Complete o seleccione todos los campos...", Toast.LENGTH_SHORT).show();
                    else if(Integer.parseInt(copias) < 1) {
                        Toast.makeText(getContext(), "Cantidad debe ser mayor a 0...", Toast.LENGTH_SHORT).show();
                    }else{
                        Tool tool = new Tool(hmNameTool.get(nameTool),nameTool, Short.parseShort(copias));

                        agregarTool(ToolFragment.this, tool);
                    }

                    alertDialog.dismiss();
            });

            alertDialog.show();

        });
        }

        return viewTool;
    }



    private void agregarTool(final ToolFragment toolFragment, final Tool tool) {
        ((ProfileActivity)getActivity()).agregarTool(toolFragment,tool);
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

    public List<Tool> getListTool(){
        return listTool;
    }

    public void setHsTypeTool(HashMap<String, Short> hmTypeTool){
        this.hmTypeTool = hmTypeTool; }

    public ToolAdapter getToolAdapter() {
        return toolAdapter;
    }

    public void setHmListNameTool(HashMap<String, Short> hmNameTool) {
        this.hmNameTool = hmNameTool;
    }

    public void notificarAdapter() {

        tv_noTool = viewTool.findViewById(R.id.tv_noTools);
        tv_noTool.setText(listTool.size() + " Herramienta(s) Encontrada(s)");

        toolAdapter = new ToolAdapter(listTool, toolEmployee, context);
        recyclerArticle = viewTool.findViewById(R.id.recyclerArticle);
        recyclerArticle.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerArticle.setAdapter(toolAdapter);

    }

    public void eliminarToolList(final int idTool) {
        for (int i = 0; i< listTool.size(); i++){
            if(idTool == listTool.get(i).getIdArticle())
            listTool.remove(i);
        }
    }
}
