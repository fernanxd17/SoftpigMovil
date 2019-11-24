package com.Softpig.Presenter.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Softpig.Model.ExamMale;
import com.Softpig.Model.Male;
import com.Softpig.R;
import com.Softpig.View.PigActivity;

import java.util.ArrayList;
import java.util.List;

public class ExamMaleAdapter extends RecyclerView.Adapter<ExamMaleAdapter.ViewHolderMaleExam> implements Filterable {

    private List<ExamMale> listMaleExam;
    private List<ExamMale> listMaleExamFull;
    private Context context;

    public ExamMaleAdapter(List<ExamMale> listMaleExam, Context context) {
        this.listMaleExam = listMaleExam;
        listMaleExamFull = new ArrayList<>(listMaleExam);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderMaleExam onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_male_exam,parent, false);
        return new ViewHolderMaleExam(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMaleExam holder, int position) {
            final ExamMale examMale = listMaleExam.get(position);
            holder.tvDateExam.setText(examMale.getExamDate());
            holder.tvNameExam.setText(examMale.getName());
            holder.tvIdMaleExam.setText(String.valueOf(examMale.getIdExam()));
            holder.llCardViewExamMale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((PigActivity) context).inflarMaleExam(examMale);
                }
            });
    }

    @Override
    public int getItemCount() {
        return listMaleExam.size();
    }

    @Override
    public Filter getFilter() {
        return maleExamFilter;
    }

    private Filter maleExamFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<ExamMale> listaFiltrada = new ArrayList<>();
            if(charSequence == null || charSequence.length()==0){
                listaFiltrada.addAll(listMaleExamFull);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(ExamMale examMale: listMaleExamFull){
                    if(String.valueOf(examMale.getIdExam()).toLowerCase().contains(filterPattern) ||
                            examMale.getName().toLowerCase().contains(filterPattern)){
                        listaFiltrada.add(examMale);
                    }

                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = listaFiltrada;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listMaleExam.clear();
            listMaleExam.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolderMaleExam extends RecyclerView.ViewHolder {

        private TextView tvIdMaleExam, tvNameExam, tvDateExam;
        private LinearLayout llCardViewExamMale;
        public ViewHolderMaleExam(@NonNull View itemView) {
            super(itemView);

            tvIdMaleExam = itemView.findViewById(R.id.tv_idMaleExam);
            tvNameExam = itemView.findViewById(R.id.tv_nameExam);
            tvDateExam = itemView.findViewById(R.id.tv_date_exam_male);
            llCardViewExamMale = itemView.findViewById(R.id.ll_cardview_male_exam);
        }



    }
}
