package com.Softpig.Presenter.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.Softpig.Model.Installation;
import com.Softpig.R;
import java.util.ArrayList;
import java.util.List;

public class InstallationAdapter extends RecyclerView.Adapter<InstallationAdapter.ViewHolderInstallation> implements Filterable {


    private List<Installation> listInstallationsFull;
    private List<Installation> listInstallations;

    public InstallationAdapter(List<Installation> listInstallations) {
        this.listInstallations = listInstallations;
        listInstallationsFull = new ArrayList<>(listInstallations);
    }

    @NonNull
    @Override
    public ViewHolderInstallation onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_installations, parent, false);
        return new ViewHolderInstallation(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderInstallation holder, int position) {
            holder.tv_idInstallation.setText(String.valueOf(listInstallations.get(position).getIdInstalation()));
            holder.tv_nameInstallation.setText(listInstallations.get(position).getName());
            holder.tv_typeInstallation.setText(listInstallations.get(position).getTypeInstalation());
            holder.tv_measurements.setText(listInstallations.get(position).getCapacity()+" metros");
    }

    @Override
    public int getItemCount() {
        return listInstallations.size();
    }

    @Override
    public Filter getFilter() {
        return installationFilter;
    }

    private Filter installationFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Installation> listaFiltrada = new ArrayList<>();
            if(charSequence == null || charSequence.length()==0){
                listaFiltrada.addAll(listInstallationsFull);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(Installation installation: listInstallationsFull){
                    if(installation.getName().toLowerCase().contains(filterPattern) ||
                            installation.getTypeInstalation().toLowerCase().contains(filterPattern)){
                        listaFiltrada.add(installation);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = listaFiltrada;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listInstallations.clear();
            listInstallations.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolderInstallation extends RecyclerView.ViewHolder {
        TextView tv_idInstallation, tv_nameInstallation, tv_typeInstallation, tv_measurements;
        LinearLayout llCardviewInstallation;

        public ViewHolderInstallation(@NonNull View itemView) {
            super(itemView);
            tv_idInstallation = itemView.findViewById(R.id.tv_valor_id_installation);
            tv_nameInstallation = itemView.findViewById(R.id.tv_valor_name_installation);
            tv_typeInstallation = itemView.findViewById(R.id.tv_valor_type_installation);
            tv_measurements = itemView.findViewById(R.id.tv__valor_medidasInstallation);
            llCardviewInstallation = itemView.findViewById(R.id.ll_cardview_installation);
        }
    }
}
