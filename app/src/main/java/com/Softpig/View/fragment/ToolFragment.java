package com.Softpig.View.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softpig.Model.Employee;
import com.Softpig.Model.Tool;
import com.Softpig.Presenter.Adapters.ToolAdapter;
import com.Softpig.R;
import com.Softpig.View.ProfileActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ToolFragment extends Fragment  implements AddToolDialog.AddToolListerner{

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
                    if(toolEmployee)
                        openDialogAddArticlePerson();
                    else
                        openDialogAddTool();
                }
            });
        }

        return viewTool;
    }

    public void openDialogAddArticlePerson() {
        Employee empleado = ((ProfileActivity)getActivity()).getEmployee();
        String nombreEmpleado = empleado.getFirstName() + " " + empleado.getLastName();
        AddToolDialog addToolDialog = new AddToolDialog();
        addToolDialog.show(getActivity().getSupportFragmentManager(), "Tool Dialog");
    }

    public void openDialogAddTool(){
        AddToolDialog addToolDialog = new AddToolDialog(hmTypeTool);
        addToolDialog.show(getActivity().getSupportFragmentManager(), "Tool Dialog");
    }

    @Override
    public void addTool(String nameTool, String copias, String typeArticle) {
        ((ProfileActivity) getActivity()).agregarArticle(nameTool, copias, Integer.parseInt(typeArticle));
    }

    public void setListTool(ArrayList<Tool> listTool){
        this.listTool = listTool;
    }

    public void setHsTypeTool(HashMap<String, Short> hmTypeTool){ this.hmTypeTool = hmTypeTool; }

    public ToolAdapter getToolAdapter() {
        return toolAdapter;
    }
}
