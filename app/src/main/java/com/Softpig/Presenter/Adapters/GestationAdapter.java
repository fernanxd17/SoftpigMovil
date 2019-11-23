package com.Softpig.Presenter.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Softpig.Model.Birth;
import com.Softpig.Model.PeriodGestation;
import com.Softpig.R;

import java.util.ArrayList;
import java.util.List;

public class GestationAdapter extends  RecyclerView.Adapter<GestationAdapter.ViewHolderGestation> implements Filterable {

    private List<PeriodGestation> listGestation;
    private List<PeriodGestation> listGestationFull;
    private Context context;

    public GestationAdapter(List<PeriodGestation> listGestation, Context context) {
        this.listGestation = listGestation;
        listGestationFull = new ArrayList<>(listGestation);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderGestation onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_gestation,parent, false);
        return new ViewHolderGestation(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderGestation holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public class ViewHolderGestation extends RecyclerView.ViewHolder {
        public ViewHolderGestation(@NonNull View itemView) {
            super(itemView);
        }
    }
}
