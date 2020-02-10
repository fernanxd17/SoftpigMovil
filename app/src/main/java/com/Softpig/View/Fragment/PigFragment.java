package com.Softpig.View.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.Softpig.Model.Pig;
import com.Softpig.Presenter.Adapters.PigAdapter;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;
import java.util.ArrayList;

public class PigFragment extends Fragment {

    private RecyclerView recyclerPig;
    private PigAdapter pigAdapter;
    private ArrayList<Pig> listPigs;
    private View viewPigs;
    private TextView tv_noPigs;
    private SwipeRefreshLayout refreshListPig;


    public PigFragment() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewPigs =  inflater.inflate(R.layout.fragment_list_pigs, container, false);
        refreshListPig = viewPigs.findViewById(R.id.refreshListPig);

        refreshListPig.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ((MainMenuActivity)getContext()).actualizarValoresListPig(refreshListPig);
            }
        });


            ((MainMenuActivity)getActivity()).setSearch("Pig");
            if(listPigs.isEmpty()){
                tv_noPigs = viewPigs.findViewById(R.id.tv_noPigs);
                tv_noPigs.setText("No Existen porcinos registrados");
                return viewPigs;
            }
            pigAdapter = new PigAdapter(listPigs, getContext());
            recyclerPig = viewPigs.findViewById(R.id.recyclerPig);
            recyclerPig.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerPig.setAdapter(pigAdapter);



        return  viewPigs;
    }


    public void setListPig(ArrayList<Pig> listPigs) {
        this.listPigs = listPigs;
    }

    public PigAdapter getPigAdapter() {
        return pigAdapter;
    }

    public void notifyAdapter() {
        pigAdapter = new PigAdapter(listPigs, getContext());
        recyclerPig = viewPigs.findViewById(R.id.recyclerPig);
        recyclerPig.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerPig.setAdapter(pigAdapter);
    }
}
