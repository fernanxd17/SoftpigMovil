package com.Softpig.View.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Softpig.Model.Heat;
import com.Softpig.Presenter.Adapters.BirthAdapter;
import com.Softpig.Presenter.Adapters.HeatAdapter;
import com.Softpig.R;
import com.Softpig.View.PigActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HeatFragment extends Fragment {

    private RecyclerView recyclerHeat;
    private HeatAdapter heatAdapter;
    private List<Heat> listHeat;
    private TextView tvNoHeat;
    private FloatingActionButton fbAddHeat;

    public HeatFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewHeat = inflater.inflate(R.layout.fragment_list_heat, container, false);
        ((PigActivity)getActivity()).setSearch("Heat");
        tvNoHeat = viewHeat.findViewById(R.id.tv_noheats);
        if(listHeat.isEmpty()){
            tvNoHeat.setText("No hay celos registrados");
        }else{
            heatAdapter = new HeatAdapter(listHeat, getContext());
            recyclerHeat = viewHeat.findViewById(R.id.recyclerHeat);
            recyclerHeat.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerHeat.setAdapter(heatAdapter);
        }
        fbAddHeat = viewHeat.findViewById(R.id.fb_add_heat_female);
        fbAddHeat.setOnClickListener(view -> {



        });

        return viewHeat;
    }

    public HeatAdapter getHeatAdapter(){
        return heatAdapter;
    }
    public void setListHeat(List<Heat> listHeat) {
        this.listHeat = listHeat;
    }
}
