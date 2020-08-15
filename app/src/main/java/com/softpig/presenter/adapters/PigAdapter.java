package com.softpig.presenter.adapters;

import android.content.Context;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.softpig.model.Pig;
import com.softpig.R;
import com.softpig.View.MainMenuActivity;
import java.util.ArrayList;
import java.util.List;

public class PigAdapter extends RecyclerView.Adapter<PigAdapter.ViewHolderPig> implements Filterable {

    private List<Pig> listPig;
    private List<Pig> listPigFull;
    private Context context;




    public PigAdapter(List<Pig> listPig, Context context) {
            this.listPig = listPig;
            listPigFull = new ArrayList<>(listPig);
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolderPig onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_pig,parent, false);
            return new ViewHolderPig(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolderPig holder, final int position) {
            final Pig pig = listPig.get(position);
            holder.tvIdPig.setText("ID: "+listPig.get(position).getIdPig());
            holder.tvEtapaPig.setText(listPig.get(position).getGrowthPhase());
            holder.tvPesoPig.setText(listPig.get(position).getWeigth()+" Kg");
            holder.tvSexoPig.setText(listPig.get(position).getSex());

            holder.rlCardviewPig.setOnClickListener(view -> ((MainMenuActivity)context).iniciarPigActivityPig(pig));
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
            TextView tvIdPig;
            TextView tvEtapaPig;
            TextView tvPesoPig;
            TextView tvSexoPig;
            RelativeLayout rlCardviewPig;

            public ViewHolderPig(@NonNull View itemView) {
                super(itemView);
                tvIdPig = itemView.findViewById(R.id.tv_idPig);
                tvEtapaPig = itemView.findViewById(R.id.tv_etapaPig);
                tvPesoPig = itemView.findViewById(R.id.tv_pesoPig);
                tvSexoPig = itemView.findViewById(R.id.tv_sexoPig);
                rlCardviewPig = itemView.findViewById(R.id.rl_cardview_pig);


            }
        }


}
