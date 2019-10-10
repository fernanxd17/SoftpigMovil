package com.Softpig.Presenter.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_installations, null, false);
        return new ViewHolderInstallation(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderInstallation holder, int position) {
            holder.tvIdInstallation.setText(listInstallations.get(position).getIdInstalation());
            holder.tvNameInstallation.setText(listInstallations.get(position).getName());
            holder.tvTypeInstallation.setText(listInstallations.get(position).getTypeInstalation());
            holder.tvOtherDat.setText(listInstallations.get(position).getCapacity());

    }

    @Override
    public int getItemCount() {
        return listInstallations.size();
    }

    public class ViewHolderInstallation extends RecyclerView.ViewHolder {

        TextView tvIdInstallation, tvNameInstallation, tvTypeInstallation, tvOtherDat;


        public ViewHolderInstallation(@NonNull View itemView) {
            super(itemView);
            this.tvIdInstallation = itemView.findViewById(R.id.tvIdInstallation);
            this.tvNameInstallation = itemView.findViewById(R.id.tvNameInstallation);
            this.tvTypeInstallation = itemView.findViewById(R.id.tvTypeInstallation);
            this.tvOtherDat = itemView.findViewById(R.id.tvOtherDat);
        }
    }
}
