package com.Softpig.View.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softpig.Presenter.Adapters.InstallationAdapter;
import com.Softpig.Presenter.InstallationPresenter;
import com.Softpig.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InstallationFragment extends Fragment {


    private RecyclerView recyclerInstallations;
    private InstallationPresenter installationPresenter;
    private InstallationAdapter installationAdapter;

    public InstallationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_installations, container, false);
        installationPresenter = new InstallationPresenter();
        recyclerInstallations = view.findViewById(R.id.recyclerInstallations);
        recyclerInstallations.setLayoutManager(new LinearLayoutManager(getContext()));
        installationAdapter = new InstallationAdapter(InstallationPresenter.getInstallations());
        recyclerInstallations.setAdapter(installationAdapter);

        return view;
    }

}
