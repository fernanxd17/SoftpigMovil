package com.softpig.presenter.adapters;

import android.content.Context;
import android.view.*;
import android.widget.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.softpig.model.Employee;
import com.softpig.R;
import com.softpig.View.MainMenuActivity;
import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolderEmployee> implements Filterable {

    private List<Employee> listEmployeeFull;
    private List<Employee> listEmployee;
    private Context context;

    public EmployeeAdapter(List<Employee> listEmployee, Context context) {
        this.listEmployee = listEmployee;
        listEmployeeFull = new ArrayList<>(listEmployee);
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
        final Employee employee = listEmployee.get(position);

        if(employee.getGender().equalsIgnoreCase("Mujer"))
            holder.icSex.setImageResource(R.drawable.gender_female);
        holder.tvIdEmployee.setText("ID: "+employee.getIdEmployee());
        holder.tvNameEmployee.setText(employee.getFirstName()+ " "+employee.getLastName());
        holder.tvTypeEmployee.setText(employee.getRole());
        holder.stateEmployee.setText(employee.getStatus());
        holder.llCardViewEmployee.setOnClickListener(v -> ((MainMenuActivity) context).iniciarProfileActivity(employee));
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
                    if(employee.getFirstName().toLowerCase().contains(filterPattern) ||
                        employee.getSecondName().toLowerCase().contains((filterPattern)) ||
                        employee.getLastName().toLowerCase().contains((filterPattern)) ||
                        employee.getMotherLastName().toLowerCase().contains(filterPattern)){
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

        private TextView tvIdEmployee;
        private TextView tvNameEmployee;
        private TextView tvTypeEmployee;
        private TextView stateEmployee;
        private ImageView icSex;
        private LinearLayout llCardViewEmployee;

        public ViewHolderEmployee(@NonNull View itemView) {
            super(itemView);
            tvIdEmployee =  itemView.findViewById(R.id.tv_idEmployee);
            tvNameEmployee =  itemView.findViewById(R.id.tv_nameEmployee);
            tvTypeEmployee =  itemView.findViewById(R.id.tv_typeEmployee);
            llCardViewEmployee = itemView.findViewById(R.id.ll_cardview_employee);
            stateEmployee = itemView.findViewById(R.id.tv_state_Employee);
            icSex = itemView.findViewById(R.id.icon_sex);
        }
    }
}