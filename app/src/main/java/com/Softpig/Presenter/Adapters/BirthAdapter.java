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
import com.Softpig.Model.Birth;
import com.Softpig.Model.Male;
import com.Softpig.R;

import java.util.ArrayList;
import java.util.List;

public class BirthAdapter extends RecyclerView.Adapter<BirthAdapter.ViewHolderBirth> implements Filterable {

    private List<Birth> listBirth;
    private List<Birth> listBirthFull;
    private Context context;

    public BirthAdapter(List<Birth> listBirth, Context context) {
        this.listBirth = new ArrayList<>(listBirth);
        this.context = context;
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
                            String.valueOf(birth.getIdFemale()).toLowerCase().contains(filterPattern)){
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

        private LinearLayout llBirth;
        private TextView tvIdBirth,tvMaleDad, tvDate;
        private TextView tvNumBabies, tvNumMummy, tvNumDead;
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
