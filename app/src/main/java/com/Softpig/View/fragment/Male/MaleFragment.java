package com.Softpig.View.fragment.Male;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softpig.Presenter.Adapters.MaleAdapter;
import com.Softpig.Presenter.MalePresenter;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MaleFragment extends Fragment {

    private RecyclerView recyclerMale;
    private MalePresenter malePresenter;
    private MaleAdapter maleAdapter;
    private MaleViewModel maleViewModel;

    public MaleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ((MainMenuActivity)getActivity()).setTitleTolbar("Reproductores");
        View view = inflater.inflate(R.layout.fragment_male, container, false);
        malePresenter = new MalePresenter();
        recyclerMale = view.findViewById(R.id.recyclerMale);
        recyclerMale.setLayoutManager(new LinearLayoutManager(getContext()));
        maleAdapter = new MaleAdapter(malePresenter.getMales());
        recyclerMale.setAdapter(maleAdapter);
        return view;
    }

}
