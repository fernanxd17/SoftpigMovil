package com.Softpig.Presenter.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Softpig.Model.Pig;
import com.Softpig.R;

import java.util.ArrayList;

public class PigAdapter extends RecyclerView.Adapter<PigAdapter.ViewHolderPig> {

    private ArrayList<Pig> listPig;

    public PigAdapter(ArrayList<Pig> listPig) {
            this.listPig = listPig;
        }

        @NonNull
        @Override
        public ViewHolderPig onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_pig,null, false);
            return new ViewHolderPig(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolderPig holder, int position) {
            holder.tv_idPig.setText("ID: "+listPig.get(position).getIdPig());
            holder.tv_etapaPig.setText(listPig.get(position).getGrowthPhase());
            holder.tv_pesoPig.setText(listPig.get(position).getWeigth()+" Kg");
            holder.tv_sexoPig.setText(listPig.get(position).getSex());
        }

        @Override
        public int getItemCount() {
            return listPig.size();
        }

        public class ViewHolderPig extends RecyclerView.ViewHolder {

            ImageView imagenPig;
            TextView tv_idPig,tv_etapaPig,tv_pesoPig, tv_sexoPig;
            RelativeLayout rlCardviewPig;

            public ViewHolderPig(@NonNull View itemView) {
                super(itemView);
                tv_idPig = itemView.findViewById(R.id.tv_idPig);
                tv_etapaPig = itemView.findViewById(R.id.tv_etapaPig);
                tv_pesoPig = itemView.findViewById(R.id.tv_pesoPig);
                tv_sexoPig = itemView.findViewById(R.id.tv_sexoPig);
                rlCardviewPig = itemView.findViewById(R.id.rl_cardview_pig);

                rlCardviewPig.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        }


}
