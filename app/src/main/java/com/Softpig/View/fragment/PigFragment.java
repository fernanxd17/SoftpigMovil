package com.Softpig.View.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Softpig.Presenter.Adapters.PigAdapter;
import com.Softpig.Presenter.PigPresenter;
import com.Softpig.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PigFragment extends Fragment {

    private RecyclerView recyclerPig;
    private PigPresenter pigPresenter;
    private PigAdapter pigAdapter;

    public PigFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pigs, container, false);

        pigPresenter = new PigPresenter();
        recyclerPig = view.findViewById(R.id.recyclerPig);
        recyclerPig.setLayoutManager(new LinearLayoutManager(getContext()));
        pigAdapter = new PigAdapter(pigPresenter.getPigs());
        recyclerPig.setAdapter(pigAdapter);

        return  view;
    }



}
