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
        holder.tv_idRace.setText("ID: "+listRaces.get(position).getIdRace());
        holder.tv_nameRace.setText(listRaces.get(position).getRace());
        holder.tv_description.setText(listRaces.get(position).getDescription());
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
                    if(race.getRace().toLowerCase().contains(filterPattern)){
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

        TextView tv_idRace, tv_nameRace, tv_description;
        LinearLayout llCardviewRace;

        public ViewHolderRace(@NonNull View itemView) {
            super(itemView);
            tv_idRace =  itemView.findViewById(R.id.tv_idRace);
            tv_nameRace =  itemView.findViewById(R.id.tv_nameRace);
            tv_description =  itemView.findViewById(R.id.tv_description);

            llCardviewRace = itemView.findViewById(R.id.ll_cardview_race);
        }
    }
}
