package com.Softpig.View.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softpig.Model.Installation;
import com.Softpig.Presenter.Adapters.InstallationAdapter;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;

import java.util.ArrayList;

public class InstallationFragment extends Fragment {


    private RecyclerView recyclerInstallations;
    private InstallationAdapter installationAdapter;
    private ArrayList<Installation> listInstallations;
    private  View viewInstallations;
    private  TextView tv_noInstallations;


    public InstallationAdapter getInstallationAdapter(){
        return installationAdapter;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewInstallations =  inflater.inflate(R.layout.fragment_list_installations, container, false);
        ((MainMenuActivity) getActivity()).setTitleTolbar("Instalaciones");
        if(listInstallations.isEmpty()) {
            tv_noInstallations = viewInstallations.findViewById(R.id.tv_noInstallations);
            tv_noInstallations.setText("No hay instalaciones registradas, entra a la web y crea una");
            return  viewInstallations;
        }
        ((MainMenuActivity)getActivity()).setSearch("Installation");
        installationAdapter = new InstallationAdapter(listInstallations,getContext());
        recyclerInstallations = viewInstallations.findViewById(R.id.recyclerInstallations);
        recyclerInstallations.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerInstallations.setAdapter(installationAdapter);

        return viewInstallations;
    }

    public void setListInstallations(ArrayList<Installation> listInstallations) {

        this.listInstallations = listInstallations;
    }
}
