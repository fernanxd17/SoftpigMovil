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

    private RecyclerView recyclerInstallations;
    private InstallationPresenter installationPresenter;
    private InstallationAdapter installationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.installations);
        installationPresenter = new InstallationPresenter();
        recyclerInstallations = findViewById(R.id.recyclerInstallations);
        recyclerInstallations.setLayoutManager(new LinearLayoutManager(this));
        installationAdapter = new InstallationAdapter(InstallationPresenter.getInstallations());
        recyclerInstallations.setAdapter(installationAdapter);

    }
}
