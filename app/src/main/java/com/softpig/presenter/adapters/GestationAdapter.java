package com.softpig.presenter.adapters;

import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.softpig.model.PeriodGestation;
import com.softpig.R;
import java.util.ArrayList;
import java.util.List;

public class GestationAdapter extends  RecyclerView.Adapter<GestationAdapter.ViewHolderGestation> implements Filterable {

    private List<PeriodGestation> listGestation;
    private List<PeriodGestation> listGestationFull;

    public GestationAdapter(List<PeriodGestation> listGestation) {
        this.listGestation = listGestation;
        listGestationFull = new ArrayList<>(listGestation);
    }

    @NonNull
    @Override
    public ViewHolderGestation onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_gestation,parent, false);
        return new ViewHolderGestation(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderGestation holder, int position) {
        PeriodGestation gestation = listGestation.get(position);
        holder.tvIdGestation.setText(String.valueOf(gestation.getIdPeriodGestation()));
        holder.tvMaleGestation.setText(String.valueOf(gestation.getIdMale()));
        holder.tvDateStartGestation.setText(gestation.getDateStart());
    }

    @Override
    public int getItemCount() {
        return listGestation.size();
    }

    @Override
    public Filter getFilter() {
        return gestationFilter;
    }

    private Filter gestationFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<PeriodGestation> listaFiltrada = new ArrayList<>();
            if(charSequence == null || charSequence.length()==0){
                listaFiltrada.addAll(listGestationFull);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(PeriodGestation gestation: listGestationFull){
                    if(String.valueOf(gestation.getIdPeriodGestation()).toLowerCase().contains(filterPattern) ||
                            String.valueOf(gestation.getIdMale()).toLowerCase().contains(filterPattern)){
                        listaFiltrada.add(gestation);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = listaFiltrada;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listGestation.clear();
            listGestation.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolderGestation extends RecyclerView.ViewHolder {

        private TextView tvIdGestation;
        private TextView tvMaleGestation;
        private TextView tvDateStartGestation;

        public ViewHolderGestation(@NonNull View itemView) {
            super(itemView);
            tvIdGestation = itemView.findViewById(R.id.tv_idGestation);
            tvMaleGestation = itemView.findViewById(R.id.tv_male_gestation);
            tvDateStartGestation = itemView.findViewById(R.id.tv_date_start_gestation);
        }
    }
}