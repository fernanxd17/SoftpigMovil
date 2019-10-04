package com.Softpig.Service.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Softpig.Models.Installation;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_installations, null, false);
        return new ViewHolderInstallation(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderInstallation holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listInstallations.size();
    }

    public class ViewHolderInstallation extends RecyclerView.ViewHolder {



        public ViewHolderInstallation(@NonNull View itemView) {
            super(itemView);
        }
    }
}
