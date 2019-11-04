package com.Softpig.Presenter.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Softpig.Model.Employee;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;
import com.Softpig.View.ProfileActivity;
import com.Softpig.View.fragment.Employee.EmployeeFragment;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolderEmployee> {

    ArrayList<Employee> listEmployee;
    private Context context;

    public EmployeeAdapter(ArrayList<Employee> listEmployee, Context context) {
        this.listEmployee = listEmployee;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderEmployee onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_employee,null, false);
        return new ViewHolderEmployee(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderEmployee holder, int position) {
        holder.tv_idEmployee.setText("ID: "+listEmployee.get(position).getIdEmployee());
        holder.tv_nameEmployee.setText(listEmployee.get(position).getName());
        holder.tv_typeEmployee.setText(listEmployee.get(position).getRole());
    }

    @Override
    public int getItemCount() {
        return this.listEmployee.size();
    }

    public class ViewHolderEmployee extends RecyclerView.ViewHolder {

        TextView tv_idEmployee, tv_nameEmployee, tv_typeEmployee;
        private TextView tvInfoEmployee;

        public ViewHolderEmployee(@NonNull View itemView) {
            super(itemView);
            tv_idEmployee =  itemView.findViewById(R.id.tv_idEmployee);
            tv_nameEmployee =  itemView.findViewById(R.id.tv_nameEmployee);
            tv_typeEmployee =  itemView.findViewById(R.id.tv_typeEmployee);
            tvInfoEmployee = itemView.findViewById(R.id.tv_infoEmployee);

            tvInfoEmployee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent();
                    i.setClass(EmployeeAdapter.this.context, ProfileActivity.class);
                    context.startActivity(i);
                }
            });
        }

        public void iniciarPerfilActivity(View view){

        }
    }
}
