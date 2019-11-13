package com.Softpig.View.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softpig.Model.Female;
import com.Softpig.Presenter.Adapters.FemaleAdapter;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FemaleFragment extends Fragment {

    private RecyclerView recyclerFemale;
    private FemaleAdapter femaleAdapter;
    private ArrayList<Female> listFemale;
    private TextView tv_noFemales;
    private  View viewFemale;

    public FemaleFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewFemale =  inflater.inflate(R.layout.fragment_list_female, container, false);

        ((MainMenuActivity)getActivity()).setTitleTolbar("Reproductoras");
        if (listFemale.isEmpty()){
            tv_noFemales = viewFemale.findViewById(R.id.tv_noFemales);
            tv_noFemales.setText("NO existen reproductoras");
            return viewFemale;
        }
        femaleAdapter = new FemaleAdapter(this.listFemale);
        recyclerFemale = viewFemale.findViewById(R.id.recyclerFemale);
        recyclerFemale.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerFemale.setAdapter(femaleAdapter);

        return viewFemale;
    }

    public void setListFemale(ArrayList<Female> listFemales) {
        this.listFemale = listFemales;
    }
}
