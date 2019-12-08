package com.Softpig.View.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.Softpig.Model.Male;
import com.Softpig.Presenter.Adapters.MaleAdapter;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;

import java.util.ArrayList;

public class MaleFragment extends Fragment {

    private RecyclerView recyclerMale;
    private MaleAdapter maleAdapter;
    private ArrayList<Male> listMales;
    private  View viewMale;
    private  TextView tv_noMales;
    private Button bt_desasignar_male;
    private String [] listIdMale;
    private Male male;
    private SwipeRefreshLayout refreshListMale;

    public MaleFragment(ArrayList<Male> listMales) {
        this.listMales = listMales;
    }

    public MaleFragment(){

    }

    public MaleAdapter getMaleAdapter(){
        return maleAdapter;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        viewMale = inflater.inflate(R.layout.fragment_list_male, container, false);
        refreshListMale = viewMale.findViewById(R.id.refresh_list_male);
        refreshListMale.setOnRefreshListener(() -> ((MainMenuActivity)getContext()).actualizarListaMale(refreshListMale));
        ((MainMenuActivity)getActivity()).setTitleTolbar("Reproductores");
        ((MainMenuActivity)getActivity()).setSearch("Male");
        if (listMales.isEmpty()){
            tv_noMales = viewMale.findViewById(R.id.tv_noMales);
            tv_noMales.setText("No existen reproductores");
        }
        maleAdapter = new MaleAdapter(this.listMales, getContext());
        recyclerMale = viewMale.findViewById(R.id.recyclerMale);
        recyclerMale.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerMale.setAdapter(maleAdapter);
        return viewMale;
    }




    public void setListMale(ArrayList<Male> listMale) {
        this.listMales = listMale;
    }

    public void notificarAdapter() {

        if (listMales.isEmpty()){
            tv_noMales = viewMale.findViewById(R.id.tv_noMales);
            tv_noMales.setText("No existen reproductores");
        }
        maleAdapter = new MaleAdapter(this.listMales, getContext());
        recyclerMale = viewMale.findViewById(R.id.recyclerMale);
        recyclerMale.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerMale.setAdapter(maleAdapter);
    }
}