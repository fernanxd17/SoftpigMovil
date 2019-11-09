package com.Softpig.View.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.Softpig.Model.Employee;
import com.Softpig.R;
import com.Softpig.View.ProfileActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private TextView tvAssignedItems;
    private Button btInhabilitar, btDespedir;
    private View viewProfile;
    private Employee employee;
    private TextView tvName, tvNumIdentification, tvGender;
    private TextView tvRole, tvState, tvContract, tvSalary, tvInstallation, tvDateAdmission, tvDateOff;
    private TextView tvEmail, tvNumPhone, tvNumMobile;


    public ProfileFragment() {
        // Required empty public constructor
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


        this.tvAssignedItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ProfileActivity)getContext()).inflarToolsEmployeeFragment();
            }
        });
        return viewProfile;
    }

    private void modificarTextCampos() {
        String name = employee.getFirstName() + " " + employee.getSecondName() + " " + employee.getLastName() + " " + employee.getMotherLastName();
        tvName.setText(name);
        tvNumIdentification.setText(employee.getDocument());
        tvGender.setText(employee.getGender());
        tvRole.setText(employee.getRole());
        tvState.setText(employee.getStatus());
        tvContract.setText(employee.getContract());
        tvSalary.setText(String.valueOf(employee.getSalary()));
        tvInstallation.setText(employee.getInstallation());
        tvDateAdmission.setText(employee.getAdmissionDate().toString());
        tvDateOff.setText(employee.getDateOff().toString());
        tvEmail.setText(employee.getEmail());
        tvNumPhone.setText(employee.getTelephone());
        tvNumMobile.setText(employee.getMobile());
    }

    private void capturarCampos() {
        tvName = viewProfile.findViewById(R.id.tv_name);
        tvNumIdentification = viewProfile.findViewById(R.id.tv_num_identification);
        tvGender = viewProfile.findViewById(R.id.tv_gender);
        tvRole = viewProfile.findViewById(R.id.tv_role);
        tvState = viewProfile.findViewById(R.id.tv_state);
        tvContract = viewProfile.findViewById(R.id.tv_contract);
        tvSalary = viewProfile.findViewById(R.id.tv_salary);
        tvInstallation = viewProfile.findViewById(R.id.tv_installation);
        tvDateAdmission = viewProfile.findViewById(R.id.tv_date_admission);
        tvDateOff = viewProfile.findViewById(R.id.tv_date_off);
        tvEmail = viewProfile.findViewById(R.id.tv_email);
        tvNumPhone = viewProfile.findViewById(R.id.tv_num_phone);
        tvNumMobile = viewProfile.findViewById(R.id.tv_num_mobile);
        tvAssignedItems = viewProfile.findViewById(R.id.tv_assigned_items);
    }


}
