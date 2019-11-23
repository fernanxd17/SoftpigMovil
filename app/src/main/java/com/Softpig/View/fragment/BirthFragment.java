package com.Softpig.View.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softpig.Model.Birth;
import com.Softpig.Presenter.Adapters.BirthAdapter;
import com.Softpig.Presenter.Adapters.MaleAdapter;
import com.Softpig.R;
import com.Softpig.View.PigActivity;

import java.util.ArrayList;
import java.util.List;

public class BirthFragment extends Fragment {

    private List<Birth> listBirth;
    private RecyclerView recyclerBirth;
    private BirthAdapter birthAdapter;
    private TextView noBirth;

    public BirthFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewBirth = inflater.inflate(R.layout.fragment_list_birth, container, false);
        ((PigActivity)getActivity()).setSearch("Birth");
        noBirth = viewBirth.findViewById(R.id.tv_nobirth);
        if(listBirth.size() == 0){
            noBirth.setText("No existen registros de partos");
        }else{
            birthAdapter = new BirthAdapter(listBirth, getContext());
            recyclerBirth = viewBirth.findViewById(R.id.recyclerBirth);
            recyclerBirth.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerBirth.setAdapter(birthAdapter);
        }

        return viewBirth;
    }

    public void setListBirth(List<Birth> listBirth){
        this.listBirth = new ArrayList<>(listBirth);
    }

    public BirthAdapter getBirthAdapter(){
        return birthAdapter;
    }
}
