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
import com.Softpig.View.MainMenuActivity;
import com.Softpig.View.ProfileActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToolFragment extends Fragment  implements AddToolEmployeeDialog.AddToolEmployeeListerner{

    private Context context;
    private RecyclerView recyclerArticle;
    public  ToolAdapter toolAdapter;
    private boolean toolEmployee = false;
    private FloatingActionButton fbAddArticle;
    private ArrayList<Tool> listTool;
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
                    if(toolEmployee){
                        openDialogAddArticlePerson();
                    }
                }
            });
        }


        return viewTool;
    }

    public void openDialogAddArticlePerson() {
        Employee empleado = ((ProfileActivity)getActivity()).getEmployee();
        String nombreEmpleado = empleado.getFirstName() + " " + empleado.getLastName();
        AddToolEmployeeDialog addToolEmployeeDialog = new AddToolEmployeeDialog(nombreEmpleado);
        addToolEmployeeDialog.show(getFragmentManager(), "Tool Dialog");
    }



    @Override
    public void agregarArticulo(String nameArticle) {
        ((ProfileActivity) getActivity()).agregarArticle(nameArticle);
    }


    public void setListTool(ArrayList<Tool> listTool){
        this.listTool = listTool;
    }

    public ToolAdapter getToolAdapter() {
        return toolAdapter;
    }
}
