package com.Softpig.View.Fragment;

import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.Softpig.Model.Employee;
import com.Softpig.R;
import com.Softpig.View.ProfileActivity;
import java.text.DecimalFormat;

public class ProfileFragment extends Fragment {

    private TextView tvAssignedItems;
    private Button btInhabilitar, btDespedir;
    private View viewProfile;
    private Employee employee;
    private TextView tvName, tvNumIdentification, tvGender, tvLastName;
    private TextView tvRole, tvState, tvContract, tvSalary, tvInstallation, tvDateAdmission, tvDateOff;
    private TextView tvEmail, tvNumPhone, tvNumMobile;
    private TextView tvAddHoursWorked;


    public ProfileFragment() {
    }

    public void setTvState(String estadoNuevo){
        tvState.setText(estadoNuevo);
        if(estadoNuevo.equalsIgnoreCase("Inhabilitado"))
            btInhabilitar.setText("Habilitar");
        else if(estadoNuevo.equalsIgnoreCase("En Funciones"))
            btInhabilitar.setText("Inhabilitar");
        else if(estadoNuevo.equalsIgnoreCase("Despedido")){
            btInhabilitar.setVisibility(View.INVISIBLE);
            btDespedir.setVisibility(View.INVISIBLE);
        }
    }

    public ProfileFragment(Employee employee){
        this.employee = employee;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewProfile =  inflater.inflate(R.layout.fragment_profile, container, false);
        capturarCampos();
        modificarTextCampos();


        tvAssignedItems.setOnClickListener(view -> ((ProfileActivity)getContext()).presentarFragment("ToolsPerson"));

        btDespedir.setOnClickListener(view -> ((ProfileActivity)getContext()).cambiarEstado(employee,"Despedido"));

        btInhabilitar.setOnClickListener(view -> {
            if(employee.getStatus().equalsIgnoreCase("En Funciones") && btInhabilitar.getText().toString().equalsIgnoreCase("Inhabilitar")){
                ((ProfileActivity)getContext()).cambiarEstado(employee,"Inhabilitado");
            }else if(employee.getStatus().equalsIgnoreCase("Inhabilitado") && btInhabilitar.getText().toString().equalsIgnoreCase("Habilitar")){
                ((ProfileActivity)getContext()).cambiarEstado(employee,"En Funciones");
            }
        });




        tvAddHoursWorked.setOnClickListener(view -> {
            final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
            View viewDialog = getLayoutInflater().inflate(R.layout.add_hours_worked, null);

            TextView tvNameEmployeeAddHour = viewDialog.findViewById(R.id.tv_name_employee_add_hour);
            String nameEmployee = employee.getFirstName() + " " + employee.getLastName();
            tvNameEmployeeAddHour.setText(nameEmployee);

            final EditText etHourWorked = viewDialog.findViewById(R.id.etHourWorked);
            Button btAgregar =  viewDialog.findViewById(R.id.btAgregar);
            Button btCancelar = viewDialog.findViewById(R.id.btCancelar);

            alert.setView(viewDialog);
            final AlertDialog alertDialog = alert.create();
            alertDialog.setCanceledOnTouchOutside(false);

            btAgregar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   String hoursAdd = etHourWorked.getText().toString();
                   if(hoursAdd.isEmpty() || Integer.parseInt(hoursAdd) == 0){
                       Toast.makeText(getContext(), "Debe agregar un numero de horas", Toast.LENGTH_SHORT);
                   }else{
                       ((ProfileActivity)getContext()).addWorkedHours(hoursAdd);


                       int salario = Integer.parseInt(hoursAdd) * 3450;
                       DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
                       String valorFormateado = decimalFormat.format(salario);
                       ProfileFragment.this.tvSalary.setText("$ "+valorFormateado);
                   }

                    alertDialog.dismiss();

                }
            });

            btCancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });

           alertDialog.show();
        });



        return viewProfile;
    }

    private void modificarTextCampos() {
        String name = employee.getFirstName() + " " + employee.getSecondName();
                String apellidos = employee.getLastName() + " " + employee.getMotherLastName();
        tvName.setText(name);
        tvLastName.setText(apellidos);
        tvNumIdentification.setText(employee.getDocument());
        tvGender.setText(employee.getGender());
        tvRole.setText(employee.getRole());
        tvState.setText(employee.getStatus());
        tvContract.setText(employee.getContract());
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        String valorFormateado = decimalFormat.format(employee.getSalary());
        tvSalary.setText("$ " + valorFormateado);
        tvInstallation.setText(employee.getInstallation());
        tvDateAdmission.setText(employee.getAdmissionDate());
        tvDateOff.setText(employee.getDateOff());
        tvEmail.setText(employee.getEmail());
        tvNumPhone.setText(employee.getTelephone());
        tvNumMobile.setText(employee.getMobile());
        if(employee.getStatus().equalsIgnoreCase("Despedido")){
            btInhabilitar.setVisibility(View.INVISIBLE);
            btInhabilitar.setEnabled(false);
            btDespedir.setVisibility(View.INVISIBLE);
            btDespedir.setEnabled(false);
        }else if(employee.getStatus().equalsIgnoreCase("Inhabilitado")){
            btInhabilitar.setText("HABILITAR");
        }

        if(!employee.getContract().equalsIgnoreCase("Servicio"))
            tvAddHoursWorked.setVisibility(View.INVISIBLE);

    }

    private void capturarCampos() {
        tvName = viewProfile.findViewById(R.id.tv_name);
        tvLastName = viewProfile.findViewById(R.id.tv_lastName);
        tvNumIdentification = viewProfile.findViewById(R.id.tv_num_identification);
        tvGender = viewProfile.findViewById(R.id.tv_gender);
        tvRole = viewProfile.findViewById(R.id.tv_role);
        tvState = viewProfile.findViewById(R.id.tv_state);
        tvContract = viewProfile.findViewById(R.id.tv_contract);
        tvSalary = viewProfile.findViewById(R.id.tv_salary);
        tvInstallation = viewProfile.findViewById(R.id.tv_installation);
        tvDateAdmission = viewProfile.findViewById(R.id.tv_date_admission);
        tvDateOff = viewProfile.findViewById(R.id.tv_date_off);
        tvEmail = viewProfile.findViewById(R.id.tv_valor_email);
        tvNumPhone = viewProfile.findViewById(R.id.tv_valor_num_phone);
        tvNumMobile = viewProfile.findViewById(R.id.tv_valor_num_mobile);
        tvAssignedItems = viewProfile.findViewById(R.id.tv_assigned_items);
        btDespedir = viewProfile.findViewById(R.id.btt_dissmis);
        btInhabilitar = viewProfile.findViewById(R.id.btt_disable);
        tvAddHoursWorked = viewProfile.findViewById(R.id.addHoursWorked);
    }


}
