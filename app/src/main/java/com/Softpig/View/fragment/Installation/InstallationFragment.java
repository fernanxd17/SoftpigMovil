package com.Softpig.View.fragment.Installation;


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

/**
 * A simple {@link Fragment} subclass.
 */
public class InstallationFragment extends Fragment {


    private RecyclerView recyclerInstallations;
    private InstallationAdapter installationAdapter;
    private ArrayList<Installation> listInstallations;
    private static View viewInstallations;
    private TextView tv_noInstallations;


    public InstallationFragment(ArrayList<Installation> listInstallations) {
        this.listInstallations = listInstallations;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewInstallations =  inflater.inflate(R.layout.fragment_installations, container, false);
        ((MainMenuActivity) getActivity()).setTitleTolbar("Instalaciones");
        if(listInstallations.isEmpty()) {
            tv_noInstallations = viewInstallations.findViewById(R.id.tv_noInstallations);
            tv_noInstallations.setText("No hay instalaciones registradas, entra a la web y crea una");
            return  viewInstallations;
        }
        installationAdapter = new InstallationAdapter(listInstallations);
        recyclerInstallations = viewInstallations.findViewById(R.id.recyclerInstallations);
        recyclerInstallations.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerInstallations.setAdapter(installationAdapter);

        return viewInstallations;
    }

}
