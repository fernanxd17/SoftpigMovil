package com.softpig.presenter.adapters;

import android.content.Context;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.softpig.model.Tool;
import com.softpig.R;
import com.softpig.View.MainMenuActivity;
import com.softpig.View.ProfileActivity;
import java.util.ArrayList;
import java.util.List;

public class ToolAdapter extends RecyclerView.Adapter<ToolAdapter.ViewHolderArticle> implements Filterable {


    private List<Tool>listToolFull;
    private List<Tool> listTool;
    private Context context;
    private boolean toolEmployee;
    public ToolAdapter(List<Tool> listTool, boolean toolEmployee, Context context) {
        this.listTool = listTool;
        listToolFull = new ArrayList<>(listTool);
        this.toolEmployee = toolEmployee;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderArticle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_tool,parent, false);


        return new ViewHolderArticle(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderArticle holder, final int position) {
            final Tool tool = listTool.get(position);
            holder.tvIdArticle.setText("ID: "+ tool.getIdArticle());
            holder.tvNameArticle.setText(tool.getName());
            holder.tvTypeArticle.setText(tool.getTypeArticle());

            if(!toolEmployee){
                holder.tvValorTotalTool.setText(String.valueOf(tool.getQuantity()));
                holder.tvValorToolsAvailable.setText(String.valueOf(tool.getQuantity()- tool.getLoan()));
                holder.tvValorToolsLoan.setText(String.valueOf(tool.getLoan()));
            }else{
                holder.tvTotalArticle.setText("# Prestadas: ");
                holder.tvValorTotalTool.setText(tool.getLoan() + " Unds.");

            }

            holder.ivRemoveArticleEmployee.setOnClickListener(view -> {

                try{
                    ((MainMenuActivity)context).eliminarArticulo(tool.getIdArticle(), "Article");
                }catch (Exception e){
                    ((ProfileActivity)context).eliminarArticuloPersona(tool.getIdArticle(), "ArticlePerson");
                }
            });
    }

    @Override
    public int getItemCount() {
        return listTool.size();
    }

    @Override
    public Filter getFilter() {
        return toolFilter;
    }


    private Filter toolFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Tool> listaFiltrada = new ArrayList<>();
            if(charSequence == null || charSequence.length()==0){
                listaFiltrada.addAll(listToolFull);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(Tool tool: listToolFull){
                    if(tool.getName().toLowerCase().contains(filterPattern) ||
                        tool.getTypeArticle().toLowerCase().contains(filterPattern)){
                        listaFiltrada.add(tool);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = listaFiltrada;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listTool.clear();
            listTool.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolderArticle extends RecyclerView.ViewHolder {

        private TextView tvNameArticle;
        private TextView tvIdArticle;
        private TextView tvTypeArticle;
        private TextView tvTotalArticle;
        private TextView tvAvailableTool;
        private TextView tvLoanTool;
        private TextView tvValorToolsAvailable;
        private TextView tvValorToolsLoan;
        private TextView tvValorTotalTool;
        private ImageView ivRemoveArticleEmployee;

        public ViewHolderArticle(@NonNull View itemView) {
            super(itemView);

                tvNameArticle = itemView.findViewById(R.id.tv_nameArticle);
                tvTypeArticle = itemView.findViewById(R.id.tv_typearticle);
                tvIdArticle = itemView.findViewById(R.id.tv_idArticle);

                tvTotalArticle = itemView.findViewById(R.id.tv_totalarticle);
                tvAvailableTool = itemView.findViewById(R.id.tv_available_article);
                tvLoanTool = itemView.findViewById(R.id.tv_loan_article);

                tvValorTotalTool = itemView.findViewById(R.id.tv_valor_totalarticle);
                tvValorToolsAvailable = itemView.findViewById(R.id.tv_valor_available_article);
                tvValorToolsLoan = itemView.findViewById(R.id.tv_valor_loan_article);

                ivRemoveArticleEmployee = itemView.findViewById(R.id.iv_remove_article);
                if(MainMenuActivity.rol.equalsIgnoreCase("Empleado Operativo")){
                    tvValorToolsAvailable.setVisibility(View.INVISIBLE);
                    tvValorToolsLoan.setVisibility(View.INVISIBLE);
                    ivRemoveArticleEmployee.setVisibility(View.INVISIBLE);
                }

                if(toolEmployee){
                    tvAvailableTool.setVisibility(View.INVISIBLE);
                    tvLoanTool.setVisibility(View.INVISIBLE);
                    tvValorToolsAvailable.setVisibility(View.INVISIBLE);
                    tvValorToolsLoan.setVisibility(View.INVISIBLE);
                }

        }
    }
}