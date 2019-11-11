package com.Softpig.Presenter.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Softpig.Model.Female;
import com.Softpig.R;

import java.util.ArrayList;

public class FemaleAdapter extends RecyclerView.Adapter<FemaleAdapter.ViewHolderFemale> {

    private ArrayList<Female> listFemale;

    public FemaleAdapter(ArrayList<Female> listFemale) {
        this.listFemale = listFemale;
    }

    @NonNull
    @Override
    public ViewHolderFemale onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_female,parent, false);
        return new ViewHolderFemale(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFemale holder, int position) {

        holder.tv_idFemale.setText("ID: "+listFemale.get(position).getIdFemale());
        holder.tv_nulipara.setText(listFemale.get(position).getVirgin());
        holder.tv_gestation.setText(listFemale.get(position).getGestation());
        holder.tv_pesoFemale.setText(listFemale.get(position).getWeigth()+" Kg");
    }

    @Override
    public int getItemCount() {
        return listFemale.size();
    }

    public class ViewHolderFemale extends RecyclerView.ViewHolder {

        ImageView imagenFemale;
        TextView tv_idFemale, tv_nulipara, tv_pesoFemale,tv_gestation;
        LinearLayout llCardviewFemale;

        public ViewHolderFemale(@NonNull View itemView) {
            super(itemView);
            tv_idFemale = itemView.findViewById(R.id.tv_idFemale);
            tv_nulipara = itemView.findViewById(R.id.tv_nulipara);
            tv_pesoFemale = itemView.findViewById(R.id.tv_pesoFemale);
            tv_gestation =  itemView.findViewById(R.id.tv_gestation);
            llCardviewFemale = itemView.findViewById(R.id.ll_cardview_female);

            llCardviewFemale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
