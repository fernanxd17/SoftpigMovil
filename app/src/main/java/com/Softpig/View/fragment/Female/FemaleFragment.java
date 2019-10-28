package com.Softpig.View.fragment.Female;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softpig.Presenter.Adapters.FemaleAdapter;
import com.Softpig.Presenter.FemalePresenter;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FemaleFragment extends Fragment {
    private RecyclerView recyclerFemale;
    private FemalePresenter femalePresenter;
    private FemaleAdapter femaleAdapter;

    public FemaleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_female, container, false);
        ((MainMenuActivity)getActivity()).setTitleTolbar("Reproductoras");
        femalePresenter = new FemalePresenter();
        recyclerFemale = view.findViewById(R.id.recyclerFemale);
        recyclerFemale.setLayoutManager(new LinearLayoutManager(getContext()));
        FemaleAdapter femaleAdapter = new FemaleAdapter(FemalePresenter.getFemale());
        recyclerFemale.setAdapter(femaleAdapter);

        return view;
    }

}
