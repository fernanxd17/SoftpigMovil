package com.Softpig.Presenter.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Softpig.Model.Medicine;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;

import java.util.ArrayList;
import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.ViewHolderMedicine> implements Filterable {

    private List<Medicine> listMedicine;
    private List<Medicine> listMedicineFull;
    private Context context;

    public MedicineAdapter(List<Medicine> listMedicine, Context context) {
        this.listMedicine = listMedicine;
        listMedicineFull = new ArrayList<>(listMedicine);
        this.context = context;
    }

    @NonNull
    @Override
    public MedicineAdapter.ViewHolderMedicine onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_medicine,parent, false);
        return new ViewHolderMedicine(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineAdapter.ViewHolderMedicine holder, int position) {
        final Medicine medicine = listMedicine.get(position);
        holder.tvIdMedicine.setText("ID: "+ medicine.getIdMedicine());
        holder.tvNameMedicine.setText(medicine.getName());
        holder.tvTypeMedicine.setText(medicine.getTypeMedicine());
        holder.tvCantidadMedicine.setText(String.valueOf(medicine.getQuantity()));
        holder.ivEliminarMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainMenuActivity)context).eliminarExistenciaMedicina(medicine.getIdMedicine());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMedicine.size();
    }

    @Override
    public Filter getFilter() {
        return medicineFilter;
    }

    private Filter medicineFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Medicine> listaFiltrada = new ArrayList<>();
            if(charSequence == null || charSequence.length()==0){
                listaFiltrada.addAll(listMedicineFull);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(Medicine medicine: listMedicineFull){
                    if(String.valueOf(medicine.getIdMedicine()).toLowerCase().contains(filterPattern) ||
                            medicine.getName().toLowerCase().contains(filterPattern)  ||
                            medicine.getQuantity() >= Integer.parseInt(filterPattern)){
                        listaFiltrada.add(medicine);
                    }

                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = listaFiltrada;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listMedicine.clear();
            listMedicine.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolderMedicine extends RecyclerView.ViewHolder {

        private TextView tvIdMedicine, tvNameMedicine, tvTypeMedicine, tvCantidadMedicine;
        private ImageView ivEliminarMedicine;
        private LinearLayout llCardviewMedicine;

        public ViewHolderMedicine(@NonNull View itemView) {
            super(itemView);
            tvIdMedicine = itemView.findViewById(R.id.tv_idMedicine);
            tvNameMedicine = itemView.findViewById(R.id.tv_valor_nameMedicine);
            tvTypeMedicine = itemView.findViewById(R.id.tv_valor_typeMedicine);
            tvCantidadMedicine = itemView.findViewById(R.id.tv_valor_cantidad_medicina);
            ivEliminarMedicine = itemView.findViewById(R.id.icon_remove_medicine);
            llCardviewMedicine = itemView.findViewById(R.id.ll_cardview_medicine);

        }
    }
}
