package com.Softpig.View.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;

public class DashBoardFragment extends Fragment {

    private  TextView tvNumAdmins, tvNumOperators, tvNumEmployees;
    private  TextView tvNumInstallations, tvNumTypeInstallations;
    private  TextView tvNumTools, tvNumToolsDispo, tvToolsPrestamo;
    private  LinearLayout llEmployeeDashboard, llInstallationsDashboard, llToalsDashboard;
    private  short [] valores;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View view;


public DashBoardFragment (){
    this.valores = new short [6];
}

    public void setValores(short [] valores){

        this.valores = valores;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_dash_board, container, false);
        llEmployeeDashboard = view.findViewById(R.id.ll_empleyees_dashboard);
        swipeRefreshLayout = view.findViewById(R.id.refreshDashboard);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ((MainMenuActivity)getContext()).actualizarValoresDashboard(swipeRefreshLayout);
            }
        });


        if(!MainMenuActivity.rol.equalsIgnoreCase("Empleado Operativo")){
            tvNumAdmins = view.findViewById(R.id.tv_num_admins);
            tvNumAdmins.setText("Empleados Administrativos: "+ valores[0]);
            tvNumOperators = view.findViewById(R.id.tv_num_operators);
            tvNumOperators.setText("Empleados Operativos: "+ valores[1]);
            tvNumEmployees = view.findViewById(R.id.tv_num_employees);
            tvNumEmployees.setText("Empleados Registrados: "+ (valores[0] + valores[1]));
        }else{
            llEmployeeDashboard.setVisibility(View.INVISIBLE);
        }


        tvNumInstallations = view.findViewById(R.id.tv_num_installations);
        tvNumInstallations.setText("Instalaciones Registradas: " + valores[4]);
        tvNumTypeInstallations = view.findViewById(R.id.tv_num_type_installations);
        tvNumTypeInstallations.setText("Tipos de Instalaciones: "+ valores[5]);
        tvNumTools = view.findViewById(R.id.tv_num_tools);
        tvNumTools.setText("Herramientas de Trabajo: " + valores[3]);
        tvNumToolsDispo = view.findViewById(R.id.tv_num_tools_disponibles);
        tvNumToolsDispo.setText("Disponibles: " + (valores[3] - valores[2]));
        tvToolsPrestamo = view.findViewById(R.id.tv_num_tools_prestamo);
        tvToolsPrestamo.setText("En prestamo: " + valores[2]);

        llInstallationsDashboard = view.findViewById(R.id.ll_installations_dashboard);
        llToalsDashboard = view.findViewById(R.id.ll_tools_dashboard);


        this.llEmployeeDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainMenuActivity)getActivity()).controllerFragment("Employees");
            }
        });

        this.llInstallationsDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainMenuActivity)getActivity()).controllerFragment("Installations");
            }
        });

        this.llToalsDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainMenuActivity)getActivity()).controllerFragment("Tools");
            }
        });

        return view;

    }

    public void actualizarText(){

        if(!MainMenuActivity.rol.equalsIgnoreCase("Empleado Operativo")){
            tvNumAdmins = view.findViewById(R.id.tv_num_admins);
            tvNumAdmins.setText("Empleados Administrativos: "+ valores[0]);
            tvNumOperators = view.findViewById(R.id.tv_num_operators);
            tvNumOperators.setText("Empleados Operativos: "+ valores[1]);
            tvNumEmployees = view.findViewById(R.id.tv_num_employees);
            tvNumEmployees.setText("Empleados Registrados: "+ (valores[0] + valores[1]));
        }else{
            llEmployeeDashboard.setVisibility(View.INVISIBLE);
        }


        tvNumInstallations = view.findViewById(R.id.tv_num_installations);
        tvNumInstallations.setText("Instalaciones Registradas: " + valores[4]);
        tvNumTypeInstallations = view.findViewById(R.id.tv_num_type_installations);
        tvNumTypeInstallations.setText("Tipos de Instalaciones: "+ valores[5]);
        tvNumTools = view.findViewById(R.id.tv_num_tools);
        tvNumTools.setText("Herramientas de Trabajo: " + valores[3]);
        tvNumToolsDispo = view.findViewById(R.id.tv_num_tools_disponibles);
        tvNumToolsDispo.setText("Disponibles: " + (valores[3] - valores[2]));
        tvToolsPrestamo = view.findViewById(R.id.tv_num_tools_prestamo);
        tvToolsPrestamo.setText("En prestamo: " + valores[2]);
    }


}
