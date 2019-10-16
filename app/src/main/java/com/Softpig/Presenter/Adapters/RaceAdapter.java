package com.Softpig.Presenter.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Softpig.Model.Race;
import com.Softpig.R;

import java.util.ArrayList;

public class RaceAdapter extends RecyclerView.Adapter<RaceAdapter.ViewHolderRace> {

    ArrayList<Race> listRaces;

    public RaceAdapter(ArrayList<Race> listRace) {
        this.listRaces = listRace;
    }

    @NonNull
    @Override
    public ViewHolderRace onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_race,null, false);
        return new ViewHolderRace(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRace holder, int position) {
        holder.tv_idRace.setText(listRaces.get(position).getIdRace());
        holder.tv_nameRace.setText(listRaces.get(position).getRace());
        holder.tv_description.setText(listRaces.get(position).getDescription());
        //holder.imagenEmployee.setImageResource('@drawable/');
    }

    @Override
    public int getItemCount() {
        return listRaces.size();
    }

    public class ViewHolderRace extends RecyclerView.ViewHolder {

        ImageView imagenRace;
        TextView tv_idRace, tv_nameRace, tv_description;

        public ViewHolderRace(@NonNull View itemView) {
            super(itemView);
            tv_idRace = (TextView) itemView.findViewById(R.id.tv_idRace);
            tv_nameRace = (TextView) itemView.findViewById(R.id.tv_nameRace);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
        }
    }
}
