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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Softpig.Model.Employee;
import com.Softpig.Model.Male;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;
import com.Softpig.View.fragment.MaleFragment;

import java.util.ArrayList;
import java.util.List;

public class MaleAdapter  extends RecyclerView.Adapter<MaleAdapter.ViewHolderMale> implements Filterable {

    private List<Male> listMale;
    private List<Male> listMaleFull;
    private Context context;

    public MaleAdapter(List<Male> listMale, Context context) {
        this.listMale = listMale;
        listMaleFull = new ArrayList<>(this.listMale);
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
        holder.llCardviewMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainMenuActivity)context).iniciarPigActivityMale(male);
            }
        });
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

        TextView tv_idMale, tv_raceMale, tv_conformacionFisica;
        LinearLayout llCardviewMale;

        public ViewHolderMale(@NonNull View itemView) {
            super(itemView);
            tv_idMale =  itemView.findViewById(R.id.tv_idMale);
            tv_raceMale =  itemView.findViewById(R.id.tv_raceMale);
            tv_conformacionFisica = itemView.findViewById(R.id.tv_conformacionFisica);
            llCardviewMale = itemView.findViewById(R.id.ll_cardview_male);
        }
    }
}
