package com.Softpig.Presenter.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Softpig.Model.Male;
import com.Softpig.R;

import java.util.ArrayList;

public class MaleAdapter  extends RecyclerView.Adapter<MaleAdapter.ViewHolderMale> {

    private ArrayList<Male> listMales;

    public MaleAdapter(ArrayList<Male> listMales) {
        this.listMales = listMales;
    }

    @NonNull
    @Override
    public ViewHolderMale onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_male,parent, false);
        return new ViewHolderMale(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMale holder, int position) {
        holder.tv_idMale.setText("ID: "+listMales.get(position).getIdMale());
        holder.tv_raceMale.setText(""+listMales.get(position).getRace());
        holder.tv_conformacionFisica.setText(listMales.get(position).getConformacionFisica());
    }

    @Override
    public int getItemCount() {
        return listMales.size();
    }

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
