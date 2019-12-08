package com.Softpig.Presenter.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.Softpig.Model.ExamMale;
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
    public void onBindViewHolder(@NonNull final ViewHolderMaleExam holder, int position) {

        final ExamMale examMale = listMaleExam.get(position);
        holder.bind(examMale);
        holder.itemView.setOnClickListener(v -> {
            boolean expanded = examMale.isExpanded();
            examMale.setExpanded(!expanded);
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return listMaleExam == null ? 0 : listMaleExam.size();
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

        private TextView tvResult, tvNameExam, tvDateExam;
        private TextView tvModificarResultado;
        private View subItem;
        private EditText et_resultado;
        private Button btCancelar, btAgregar;

        public ViewHolderMaleExam(@NonNull View itemView) {
            super(itemView);
            tvNameExam = itemView.findViewById(R.id.nameExamMale);
            tvDateExam = itemView.findViewById(R.id.tv_date_exam_male);
            subItem = itemView.findViewById(R.id.ll_sub_item);
            tvResult = itemView.findViewById(R.id.sub_item_result);
            tvModificarResultado = itemView.findViewById(R.id.sub_item_modificar_result);
        }

        private void bind(ExamMale examMale) {

            boolean expanded = examMale.isExpanded();
            tvResult.setText(examMale.getResult());
            tvDateExam.setText(examMale.getExamDate());
            tvNameExam.setText(examMale.getName());
            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);

            tvModificarResultado.setOnClickListener(view -> {
                final AlertDialog.Builder alert = new AlertDialog.Builder(context);
                View viewDialog = ((PigActivity)context).getLayoutInflater().inflate(R.layout.report_exam, null);
                et_resultado = viewDialog.findViewById(R.id.et_resultado);
                btAgregar = viewDialog.findViewById(R.id.bt_agregar);
                btCancelar = viewDialog.findViewById(R.id.bt_cancelar);

                alert.setView(viewDialog);
                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);

                btCancelar.setOnClickListener(v -> alertDialog.dismiss());

                btAgregar.setOnClickListener(v -> {
                    ((PigActivity)context).modificarExamMale(examMale.getIdExam(),et_resultado.getText().toString(), examMale.getExamDate());
                    alertDialog.dismiss();
                });

                alertDialog.show();
            });
        }
    }
}