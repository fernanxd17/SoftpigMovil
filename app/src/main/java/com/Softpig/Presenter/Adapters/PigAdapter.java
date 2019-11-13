package com.Softpig.Presenter.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Softpig.Model.Male;
import com.Softpig.Model.Pig;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;

import java.util.ArrayList;
import java.util.List;

public class PigAdapter extends RecyclerView.Adapter<PigAdapter.ViewHolderPig> implements Filterable {

    private List<Pig> listPig;
    private List<Pig> listPigFull;
    private Context context;




    public PigAdapter(List<Pig> listPig, Context context) {
            this.listPig = listPig;
            listPigFull = new ArrayList<>(this.listPig);
        }

        @NonNull
        @Override
        public ViewHolderPig onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_pig,parent, false);
            return new ViewHolderPig(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolderPig holder, final int position) {
            holder.tv_idPig.setText("ID: "+listPig.get(position).getIdPig());
            holder.tv_etapaPig.setText(listPig.get(position).getGrowthPhase());
            holder.tv_pesoPig.setText(listPig.get(position).getWeigth()+" Kg");
            holder.tv_sexoPig.setText(listPig.get(position).getSex());

            holder.rlCardviewPig.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainMenuActivity)context).iniciarPigActivity(listPig.get(position), "pig");
                }
            });
        }

        @Override
        public int getItemCount() {
            return listPig.size();
        }

    @Override
    public Filter getFilter() {
        return pigFilter;
    }

    private Filter pigFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Pig> listaFiltrada = new ArrayList<>();
            if(charSequence == null || charSequence.length()==0){
                listaFiltrada.addAll(listPigFull);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(Pig pig: listPigFull){
                    if(String.valueOf(pig.getIdPig()).toLowerCase().contains(filterPattern) ||
                       pig.getGrowthPhase().toLowerCase().contains(filterPattern)){
                        listaFiltrada.add(pig);
                    }

                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = listaFiltrada;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listPig.clear();
            listPig.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolderPig extends RecyclerView.ViewHolder {

            ImageView imagenPig;
            TextView tv_idPig,tv_etapaPig,tv_pesoPig, tv_sexoPig;
            RelativeLayout rlCardviewPig;

            public ViewHolderPig(@NonNull View itemView) {
                super(itemView);
                tv_idPig = itemView.findViewById(R.id.tv_idPig);
                tv_etapaPig = itemView.findViewById(R.id.tv_etapaPig);
                tv_pesoPig = itemView.findViewById(R.id.tv_pesoPig);
                tv_sexoPig = itemView.findViewById(R.id.tv_sexoPig);
                rlCardviewPig = itemView.findViewById(R.id.rl_cardview_pig);


            }
        }


}
