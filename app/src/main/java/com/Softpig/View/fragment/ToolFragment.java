package com.Softpig.View.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    private RecyclerView recyclerArticle;
    public  ToolAdapter toolAdapter;
    private boolean toolEmployee = false;
    private FloatingActionButton fbAddArticle;
    private ArrayList<Tool> listTool;
    private View viewTool;
    private TextView tv_noTool;

    public ToolFragment(ArrayList<Tool> listTool) {
        this.listTool = listTool;
    }

    public ToolFragment(boolean toolEmployee){
        this.toolEmployee = toolEmployee;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewTool =  inflater.inflate(R.layout.fragment_list_tools, container, false);
        ((MainMenuActivity) getActivity()).setTitleTolbar("Herramientas de la granja");

        if (listTool.isEmpty()){
            tv_noTool = viewTool.findViewById(R.id.tv_noTools);
            tv_noTool.setText("No hay herramientas en el invetario");
        }
        toolAdapter = new ToolAdapter(listTool, toolEmployee);
        recyclerArticle = viewTool.findViewById(R.id.recyclerArticle);
        recyclerArticle.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerArticle.setAdapter(toolAdapter);


        fbAddArticle = viewTool.findViewById(R.id.fb_add_tool_employee);
        if(!toolEmployee) {
            fbAddArticle.hide();
            ((MainMenuActivity) getActivity()).modificar("tool");
        }else{
            ((ProfileActivity)getActivity()).modificar("tool");
            fbAddArticle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openDialog();
                }
            });

        }

        return viewTool;
    }

    public void openDialog() {
        AddToolEmployeeDialog addToolEmployeeDialog = new AddToolEmployeeDialog();
        addToolEmployeeDialog.show(getFragmentManager(), "Tool Dialog");
    }



    @Override
    public void agregarArticulo(String nameArticle) {
        ((ProfileActivity) getActivity()).agregarArticle(nameArticle);
    }
}
