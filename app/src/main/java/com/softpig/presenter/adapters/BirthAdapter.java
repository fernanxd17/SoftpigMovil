package com.softpig.presenter.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.softpig.R;
import com.softpig.model.Birth;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BirthAdapter extends RecyclerView.Adapter<BirthAdapter.ViewHolderBirth> implements Filterable {

    private List<Birth> listBirth;
    private List<Birth> listBirthFull;

    public BirthAdapter(List<Birth> listBirth) {
        this.listBirth = listBirth;
        listBirthFull = new ArrayList<>(listBirth);
    }

    @NonNull
    @Override
    public ViewHolderBirth onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_birth,parent, false);
        return new ViewHolderBirth(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderBirth holder, int position) {
        final Birth birth = listBirth.get(position);
        holder.tvNumDead.setText(String.valueOf(birth.getNoDead()));
        holder.tvNumMummy.setText(String.valueOf(birth.getNoMummy()));
        holder.tvNumBabies.setText(String.valueOf(birth.getNoBabies()));
        holder.tvMaleDad.setText(String.valueOf(birth.getIdMale()));
        holder.tvIdBirth.setText(String.valueOf(birth.getIdBirth()));
        holder.tvDate.setText(birth.getDataBirth());
    }

    @Override
    public int getItemCount() {
        return listBirth.size();
    }

    @Override
    public Filter getFilter() {
        return birthFilter;
    }

    private Filter birthFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Birth> listaFiltrada = new ArrayList<>();
            if(charSequence == null || charSequence.length()==0){
                listaFiltrada.addAll(listBirthFull);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(Birth birth: listBirthFull){
                    if(String.valueOf(birth.getIdBirth()).toLowerCase().contains(filterPattern) ||
                            String.valueOf(birth.getIdMale()).toLowerCase().contains(filterPattern)){
                        listaFiltrada.add(birth);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = listaFiltrada;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listBirth.clear();
            listBirth.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolderBirth extends RecyclerView.ViewHolder {

        private TextView tvIdBirth;
        private TextView tvMaleDad;
        private TextView tvDate;
        private TextView tvNumBabies;
        private TextView tvNumMummy;
        private TextView tvNumDead;
        public ViewHolderBirth(@NonNull View itemView) {
            super(itemView);
            tvIdBirth = itemView.findViewById(R.id.tv_idBirth);
            tvMaleDad = itemView.findViewById(R.id.tv_male_dad);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvNumBabies = itemView.findViewById(R.id.tv_num_babies);
            tvNumMummy = itemView.findViewById(R.id.tv_num_mummy);
            tvNumDead = itemView.findViewById(R.id.tv_num_dead);
        }
    }
}