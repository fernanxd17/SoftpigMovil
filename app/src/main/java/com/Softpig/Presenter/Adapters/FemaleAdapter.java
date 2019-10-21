package com.Softpig.Presenter.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Softpig.Model.Female;
import com.Softpig.R;

import java.util.ArrayList;

public class FemaleAdapter extends RecyclerView.Adapter<FemaleAdapter.ViewHolderFemale> {

    ArrayList<Female> listFemale;

    public FemaleAdapter(ArrayList<Female> listFemale) {
        this.listFemale = listFemale;
    }

    @NonNull
    @Override
    public ViewHolderFemale onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_female,null, false);
        return new ViewHolderFemale(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFemale holder, int position) {
        String r= "";
        holder.tv_idFemale.setText("ID: "+listFemale.get(position).getIdFemale());
        if(listFemale.get(position).isVirgin() || listFemale.get(position).isGestation() ){
            r="si";
        }else {
            r="no";
        }
        holder.tv_nulipara.setText(r);
        holder.tv_gestation.setText(r);
        holder.tv_pesoFemale.setText(listFemale.get(position).getWeigth()+" Kg");
    }

    @Override
    public int getItemCount() {
        return listFemale.size();
    }

    public class ViewHolderFemale extends RecyclerView.ViewHolder {

        ImageView imagenFemale;
        TextView tv_idFemale, tv_nulipara, tv_pesoFemale,tv_gestation;

        public ViewHolderFemale(@NonNull View itemView) {
            super(itemView);
            tv_idFemale = (TextView) itemView.findViewById(R.id.tv_idFemale);
            tv_nulipara = (TextView) itemView.findViewById(R.id.tv_nulipara);
            tv_pesoFemale = (TextView) itemView.findViewById(R.id.tv_pesoFemale);
            tv_gestation = (TextView) itemView.findViewById(R.id.tv_gestation);
        }
    }
}
