package com.Softpig.View.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Softpig.Model.Female;
import com.Softpig.Presenter.Adapters.FemaleAdapter;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;

import java.util.ArrayList;

public class FemaleFragment extends Fragment {

    private RecyclerView recyclerFemale;
    private FemaleAdapter femaleAdapter;
    private ArrayList<Female> listFemale;
    private TextView tv_noFemales;
    private  View viewFemale;
    private LinearLayout ll_heats_female;
    private String[] listIdMale;
    private SwipeRefreshLayout refreshListFemale;

    public FemaleFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewFemale =  inflater.inflate(R.layout.fragment_list_female, container, false);
        refreshListFemale = viewFemale.findViewById(R.id.refresh_list_female);
        refreshListFemale.setOnRefreshListener(() -> {
            ((MainMenuActivity)getActivity()).actualizarListaFemale(refreshListFemale);
        });

        ll_heats_female = viewFemale.findViewById(R.id.ll_heats_female);
        ((MainMenuActivity)getActivity()).setTitleTolbar("Reproductoras");
        ((MainMenuActivity)getActivity()).setSearch("Female");

        tv_noFemales = viewFemale.findViewById(R.id.tv_noFemales);
        tv_noFemales.setText("No existen reproductoras");

        if (!listFemale.isEmpty())
            tv_noFemales.setText(listFemale.size() + " Reproductora(s) encontrada(s)");

        System.out.println("ListFemalesize:" +listFemale.size());
        femaleAdapter = new FemaleAdapter(this.listFemale, getContext());
        recyclerFemale = viewFemale.findViewById(R.id.recyclerFemale);
        recyclerFemale.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerFemale.setAdapter(femaleAdapter);



        /*this.ll_heats_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainMenuActivity)getActivity()).controllerFragment("Heats");
            }
        });*/

        return viewFemale;
    }

    public void setListFemale(ArrayList<Female> listFemales) {
        this.listFemale = listFemales;
    }

    public FemaleAdapter getFemaleAdapter() {
        return this.femaleAdapter;
    }

    public void setListIdMale(String[] listIdMale) { 
        this.listIdMale = listIdMale;
    }

    public String [] getListIdMale(){
        return listIdMale;
    }

    public void notificarAdapter() {

        tv_noFemales = viewFemale.findViewById(R.id.tv_noFemales);
        tv_noFemales.setText("No existen reproductoras");

        if (!listFemale.isEmpty())
            tv_noFemales.setText(listFemale.size() + " Reproductora(s) encontrada(s)");

        femaleAdapter = new FemaleAdapter(this.listFemale, getContext());
        recyclerFemale = viewFemale.findViewById(R.id.recyclerFemale);
        recyclerFemale.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerFemale.setAdapter(femaleAdapter);



    }
}
