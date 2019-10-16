package com.Softpig.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.Softpig.Model.Installation;
import com.Softpig.Presenter.Adapters.InstallationAdapter;
import com.Softpig.Presenter.InstallationPresenter;
import com.Softpig.R;

import java.util.ArrayList;

public class InstallationActivity extends AppCompatActivity {

    private ArrayList<Installation> listInstallations;
    private RecyclerView recyclerInstallations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.installations);

        listInstallations = new ArrayList<Installation>();
        recyclerInstallations = findViewById(R.id.recyclerInstallations);
        recyclerInstallations.setLayoutManager(new LinearLayoutManager(this));
        showInstallations();

    }

    /**
     * Comunica al Presenter que haga la peticion de consultar las instalaciones
     * luego, envia la listInstallations a el Adapter correspondiente
     */
    private void showInstallations() {
        listInstallations = InstallationPresenter.getInstallations();
        InstallationAdapter installationAdapter = new InstallationAdapter(listInstallations);
        this.recyclerInstallations.setAdapter(installationAdapter);
    }
}
