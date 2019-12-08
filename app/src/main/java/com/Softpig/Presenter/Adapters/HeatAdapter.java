package com.Softpig.Presenter.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.Softpig.Model.Heat;
import com.Softpig.R;
import java.util.ArrayList;
import java.util.List;

public class HeatAdapter extends RecyclerView.Adapter<HeatAdapter.ViewHolderHeat> implements Filterable {

    private List<Heat> listHeat;
    private List<Heat> listHeatFull;

    public HeatAdapter(List<Heat> listHeat) {
        this.listHeat = listHeat;
        listHeatFull = new ArrayList<>(listHeat);
    }

    @NonNull
    @Override
    public ViewHolderHeat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_heat,parent, false);
        return new HeatAdapter.ViewHolderHeat(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHeat holder, int position) {
        Heat heat = listHeat.get(position);
        holder.tvIdHeat.setText(String.valueOf(heat.getIdHeat()));
        holder.tvType.setText(heat.getTypeMating());
        holder.tvSincrony.setText(heat.isSincrony() ? "Si": "No");
        holder.tvDateStart.setText(heat.getDateStart());
        holder.tvDateEnd.setText(heat.getDateEnd());
    }

    @Override
    public int getItemCount() {
        return listHeat.size();
    }

    @Override
    public Filter getFilter() {
        return heatFilter;
    }

    private Filter heatFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Heat> listaFiltrada = new ArrayList<>();
            if(charSequence == null || charSequence.length()==0){
                listaFiltrada.addAll(listHeatFull);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(Heat heat: listHeatFull){
                    if(String.valueOf(heat.getIdHeat()).toLowerCase().contains(filterPattern) ||
                            String.valueOf(heat.getTypeMating()).toLowerCase().contains(filterPattern) ){
                        listaFiltrada.add(heat);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = listaFiltrada;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listHeat.clear();
            listHeat.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolderHeat extends RecyclerView.ViewHolder {

        private TextView tvIdHeat, tvType, tvSincrony, tvDateStart, tvDateEnd;

        public ViewHolderHeat(@NonNull View itemView) {
            super(itemView);
            tvIdHeat = itemView.findViewById(R.id.tv_idHeat);
            tvType = itemView.findViewById(R.id.tv_type);
            tvSincrony = itemView.findViewById(R.id.tv_sincrony);
            tvDateStart = itemView.findViewById(R.id.tv_date_start);
            tvDateEnd = itemView.findViewById(R.id.tv_date_end);
        }
    }
}