package com.Softpig.View.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softpig.Model.Male;
import com.Softpig.Presenter.Adapters.MaleAdapter;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MaleFragment extends Fragment {

    private RecyclerView recyclerMale;
    private MaleAdapter maleAdapter;
    private ArrayList<Male> listMales;
    private  View viewMale;
    private  TextView tv_noMales;

    public MaleFragment(ArrayList<Male> listMales) {
        this.listMales = listMales;
    }

    public MaleFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        viewMale = inflater.inflate(R.layout.fragment_list_male, container, false);
        ((MainMenuActivity)getActivity()).setTitleTolbar("Reproductores");
        if (listMales.isEmpty()){
            tv_noMales = viewMale.findViewById(R.id.tv_noMales);
            tv_noMales.setText("No existen reproductores");
        }
        maleAdapter = new MaleAdapter(this.listMales);
        recyclerMale = viewMale.findViewById(R.id.recyclerMale);
        recyclerMale.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerMale.setAdapter(maleAdapter);
        return viewMale;
    }

    public void setListMale(ArrayList<Male> listMale) {
        this.listMales = listMale;
    }
}
