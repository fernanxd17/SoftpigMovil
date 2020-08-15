package com.softpig.presenter.adapters;

import android.content.Context;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.softpig.model.Female;
import com.softpig.R;
import com.softpig.View.MainMenuActivity;
import java.util.ArrayList;
import java.util.List;

public class FemaleAdapter extends RecyclerView.Adapter<FemaleAdapter.ViewHolderFemale> implements Filterable {

    private List<Female> listFemale;
    private List<Female> listFemaleFull;
    private Context context;

    public FemaleAdapter(List<Female> listFemale, Context context) {
        this.listFemale = listFemale;
        listFemaleFull  = new ArrayList<>(listFemale);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderFemale onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_female,parent, false);
        return new ViewHolderFemale(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFemale holder, int position) {
        final Female female = listFemale.get(position);
        holder.tvIdFemale.setText("ID: "+female.getIdFemale());
        holder.tvNulipara.setText(female.getVirgin());
        holder.tvGestation.setText(female.getGestation());
        holder.tvPesoFemale.setText(female.getWeigth()+" Kg");

        holder.llCardviewFemale.setOnClickListener(view -> ((MainMenuActivity) context).iniciarPigActivityFemale(female));
    }

    @Override
    public int getItemCount() {
        return listFemale.size();
    }

    @Override
    public Filter getFilter() {
        return femaleFilter;
    }

    private Filter femaleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Female> listaFiltrada = new ArrayList<>();
            if(charSequence == null || charSequence.length()==0){
                listaFiltrada.addAll(listFemaleFull);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(Female female: listFemaleFull){
                    if(String.valueOf(female.getIdFemale()).toLowerCase().contains(filterPattern)
                        || female.getRace().toLowerCase().contains(filterPattern)){
                        listaFiltrada.add(female);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = listaFiltrada;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listFemale.clear();
            listFemale.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolderFemale extends RecyclerView.ViewHolder {

        TextView tvIdFemale;
        TextView tvNulipara;
        TextView tvPesoFemale;
        TextView tvGestation;
        LinearLayout llCardviewFemale;

        public ViewHolderFemale(@NonNull View itemView) {
            super(itemView);
            tvIdFemale = itemView.findViewById(R.id.tv_idFemale);
            tvNulipara = itemView.findViewById(R.id.tv_nulipara);
            tvPesoFemale = itemView.findViewById(R.id.tv_pesoFemale);
            tvGestation =  itemView.findViewById(R.id.tv_gestation);
            llCardviewFemale = itemView.findViewById(R.id.ll_cardview_female);
        }
    }

}