package com.Softpig.Presenter.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Softpig.Model.Installation;
import com.Softpig.R;

import java.util.ArrayList;

public class InstallationAdapter extends RecyclerView.Adapter<InstallationAdapter.ViewHolderInstallation> {

    private ArrayList <Installation> listInstallations;

    public InstallationAdapter(ArrayList<Installation> listInstallations) {
        this.listInstallations = listInstallations;
    }

    @NonNull
    @Override
    public ViewHolderInstallation onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_installations, parent, false);
        return new ViewHolderInstallation(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderInstallation holder, int position) {
            holder.tv_idInstallation.setText("ID: "+listInstallations.get(position).getIdInstalation());
            holder.tv_nameInstallation.setText(listInstallations.get(position).getName());
            holder.tv_typeInstallation.setText(listInstallations.get(position).getTypeInstalation());
            holder.tv_measurements.setText(listInstallations.get(position).getCapacity()+" metros");
    }

    @Override
    public int getItemCount() {
        return listInstallations.size();
    }

    public class ViewHolderInstallation extends RecyclerView.ViewHolder {
        TextView tv_idInstallation, tv_nameInstallation, tv_typeInstallation, tv_measurements;
        LinearLayout llCardviewInstallation;

        public ViewHolderInstallation(@NonNull View itemView) {
            super(itemView);
            tv_idInstallation = itemView.findViewById(R.id.tv_valor_id_installation);
            tv_nameInstallation = itemView.findViewById(R.id.tv_valor_name_installation);
            tv_typeInstallation = itemView.findViewById(R.id.tv_valor_type_installation);
            tv_measurements = itemView.findViewById(R.id.tv__valor_medidasInstallation);
            llCardviewInstallation = itemView.findViewById(R.id.ll_cardview_installation);

            llCardviewInstallation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
