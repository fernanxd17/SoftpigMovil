package com.Softpig.Presenter.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Softpig.Model.Employee;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolderEmployee> implements Filterable {


    List<Employee> listEmployeeFull;
    List<Employee> listEmployee;
    private Context context;

    public EmployeeAdapter(List<Employee> listEmployee, Context context) {
        this.listEmployee = listEmployee;
        this.listEmployeeFull = new ArrayList<>(listEmployee);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderEmployee onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_employee,parent, false);

        return new ViewHolderEmployee(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderEmployee holder, int position) {
        Employee employee = listEmployee.get(position);
        holder.tv_idEmployee.setText("ID: "+employee.getIdEmployee());
        holder.tv_nameEmployee.setText(employee.getFirstName()+ " "+employee.getLastName());
        holder.tv_typeEmployee.setText(employee.getRole());
    }

    @Override
    public int getItemCount() {
        return this.listEmployee.size();
    }

    @Override
    public Filter getFilter() {
        return employeeFilter;
    }

    private Filter employeeFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Employee> listaFiltrada = new ArrayList<>();
            if(charSequence == null || charSequence.length()==0){
                listaFiltrada.addAll(listEmployeeFull);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(Employee employee: listEmployeeFull){
                    if(employee.getFirstName().toLowerCase().contains(filterPattern)){
                        listaFiltrada.add(employee);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = listaFiltrada;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listEmployee.clear();
                listEmployee.addAll((List)filterResults.values);
                notifyDataSetChanged();
        }
    };

    public class ViewHolderEmployee extends RecyclerView.ViewHolder {

        TextView tv_idEmployee, tv_nameEmployee, tv_typeEmployee;
        private TextView tvInfoEmployee;
        private LinearLayout llCardViewEmployee;

        public ViewHolderEmployee(@NonNull View itemView) {
            super(itemView);
            tv_idEmployee =  itemView.findViewById(R.id.tv_idEmployee);
            tv_nameEmployee =  itemView.findViewById(R.id.tv_nameEmployee);
            tv_typeEmployee =  itemView.findViewById(R.id.tv_typeEmployee);
            llCardViewEmployee = itemView.findViewById(R.id.ll_cardview_employee);

            llCardViewEmployee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainMenuActivity) context).iniciarProfileActivity();
                }
            });


        }
    }
}
