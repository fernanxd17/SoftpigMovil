package com.softpig.presenter.adapters;

import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.softpig.model.Race;
import com.softpig.R;
import java.util.ArrayList;
import java.util.List;

public class RaceAdapter extends RecyclerView.Adapter<RaceAdapter.ViewHolderRace> implements Filterable {

    private List<Race> listRaces;
    private List<Race> listRacesFull;


    public RaceAdapter(List<Race> listRace) {
        this.listRaces = listRace;
        listRacesFull = new ArrayList<>(listRace);
    }

    @NonNull
    @Override
    public ViewHolderRace onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_race,parent, false);
        return new ViewHolderRace(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRace holder, int position) {
        holder.tvIdRace.setText("ID: "+listRaces.get(position).getIdRace());
        holder.tvNameRace.setText(listRaces.get(position).getNameRace());
        holder.tvDescription.setText(listRaces.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return listRaces.size();
    }

    @Override
    public Filter getFilter() {
        return raceFilter;
    }

    private Filter raceFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Race> listaFiltrada = new ArrayList<>();
            if(charSequence == null || charSequence.length()==0){
                listaFiltrada.addAll(listRacesFull);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(Race race: listRacesFull){
                    if(race.getNameRace().toLowerCase().contains(filterPattern)){
                        listaFiltrada.add(race);
                    }

                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = listaFiltrada;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listRaces.clear();
            listRaces.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolderRace extends RecyclerView.ViewHolder {

        TextView tvIdRace;
        TextView tvNameRace;
        TextView tvDescription;
        LinearLayout llCardviewRace;

        public ViewHolderRace(@NonNull View itemView) {
            super(itemView);
            tvIdRace =  itemView.findViewById(R.id.tv_idRace);
            tvNameRace =  itemView.findViewById(R.id.tv_nameRace);
            tvDescription =  itemView.findViewById(R.id.tv_description);

            llCardviewRace = itemView.findViewById(R.id.ll_cardview_race);
        }
    }
}
