package com.softpig.presenter.adapters;

import android.content.Context;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.softpig.model.Male;
import com.softpig.R;
import com.softpig.View.MainMenuActivity;
import java.util.ArrayList;
import java.util.List;

public class MaleAdapter  extends RecyclerView.Adapter<MaleAdapter.ViewHolderMale> implements Filterable {

    private List<Male> listMale;
    private List<Male> listMaleFull;
    private Context context;

    public MaleAdapter(List<Male> listMale, Context context) {
        this.listMale = listMale;
        listMaleFull = new ArrayList<>(listMale);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderMale onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_male,parent, false);
        return new ViewHolderMale(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMale holder, final int position) {
        final Male male = listMale.get(position);
        holder.tv_idMale.setText("ID: "+male.getIdMale());
        holder.tv_raceMale.setText(""+male.getRace());
        holder.tv_conformacionFisica.setText(male.getConformacionFisica());
        holder.llCardviewMale.setOnClickListener(view -> ((MainMenuActivity)context).iniciarPigActivityMale(male));
    }

    @Override
    public int getItemCount() {
        return listMale.size();
    }

    @Override
    public Filter getFilter() {
        return maleFilter;
    }

    private Filter maleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Male> listaFiltrada = new ArrayList<>();
            if(charSequence == null || charSequence.length()==0){
                listaFiltrada.addAll(listMaleFull);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(Male male: listMaleFull){
                    if(String.valueOf(male.getIdMale()).toLowerCase().contains(filterPattern) ||
                            male.getRace().toLowerCase().contains(filterPattern)){
                        listaFiltrada.add(male);
                    }

                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = listaFiltrada;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listMale.clear();
            listMale.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolderMale extends RecyclerView.ViewHolder {

        private TextView tv_idMale, tv_raceMale, tv_conformacionFisica;
        private LinearLayout llCardviewMale;

        public ViewHolderMale(@NonNull View itemView) {
            super(itemView);
            tv_idMale =  itemView.findViewById(R.id.tv_idMale);
            tv_raceMale =  itemView.findViewById(R.id.tv_raceMale);
            tv_conformacionFisica = itemView.findViewById(R.id.tv_conformacionFisica);
            llCardviewMale = itemView.findViewById(R.id.ll_cardview_male);
        }
    }
}
