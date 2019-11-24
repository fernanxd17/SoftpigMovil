package com.Softpig.View.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softpig.Model.PeriodGestation;
import com.Softpig.Presenter.Adapters.BirthAdapter;
import com.Softpig.Presenter.Adapters.GestationAdapter;
import com.Softpig.R;
import com.Softpig.View.PigActivity;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GestationFragment extends Fragment {

    private List<PeriodGestation> listPeriodGestation;
    private RecyclerView recyclerGestation;
    private GestationAdapter gestationAdapter;
    private TextView noGestation;

    public GestationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewGestation =  inflater.inflate(R.layout.fragment_list_gestation, container, false);
        ((PigActivity)getActivity()).setSearch("Gestation");
        noGestation = viewGestation.findViewById(R.id.tv_noGestationPeriod);

        if(listPeriodGestation.isEmpty()){
            noGestation.setText("No existen registros de Periodos de Gestación");
        }else{
            gestationAdapter = new GestationAdapter(listPeriodGestation, getContext());
            recyclerGestation = viewGestation.findViewById(R.id.recyclergestation);
            recyclerGestation.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerGestation.setAdapter(gestationAdapter);
        }

        return viewGestation;

    }

    public void setListGestation(List<PeriodGestation> listGestation) {
        this.listPeriodGestation = listGestation;
    }

    public GestationAdapter getGestationAdapter() {
        return gestationAdapter;
    }
}
