package com.Softpig.Presenter.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Softpig.Model.Tool;
import com.Softpig.Model.Employee;
import com.Softpig.R;

import java.util.ArrayList;
import java.util.List;

public class ToolAdapter extends RecyclerView.Adapter<ToolAdapter.ViewHolderArticle> implements Filterable {

    List<Tool>listToolFull;
    List<Tool> listTool;
    private static boolean toolEmployee;
    public ToolAdapter(ArrayList<Tool> listTool, boolean toolEmployee) {
        this.listTool = listTool;
        this.listToolFull = new ArrayList<>(listTool);
        this.toolEmployee = toolEmployee;
    }

    @NonNull
    @Override
    public ViewHolderArticle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_article,null, false);
        if(toolEmployee)
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_article_employee,null, false);

        return new ViewHolderArticle(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderArticle holder, int position) {
            Tool tool = listTool.get(position);
            holder.tv_idArticle.setText("ID: "+ tool.getIdArticle());
            holder.tv_nameArticle.setText(tool.getName());
            holder.tv_typearticle.setText(tool.getTypeArticle());
            if(!toolEmployee)
                holder.tv_totalarticle.setText(tool.getQuantity()+" unidades");






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
                    if(tool.getName().toLowerCase().contains(filterPattern)){
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

        TextView tv_nameArticle, tv_totalarticle, tv_typearticle, tv_idArticle;
        ImageView ivRemoveArticleEmployee;

        public ViewHolderArticle(@NonNull View itemView) {
            super(itemView);
            if(!toolEmployee){
                tv_totalarticle = itemView.findViewById(R.id.tv_totalarticle);
                tv_nameArticle = itemView.findViewById(R.id.tv_nameArticle);
                tv_typearticle = itemView.findViewById(R.id.tv_typearticle);
                tv_idArticle = itemView.findViewById(R.id.tv_idArticle);
            }else{
                tv_idArticle = itemView.findViewById(R.id.tv_id_article_employee);
                tv_nameArticle = itemView.findViewById(R.id.tv_name_article_employee);
                tv_typearticle = itemView.findViewById(R.id.tv_type_article_employee);
                ivRemoveArticleEmployee = itemView.findViewById(R.id.iv_remove_article_employee);
            }




        }
    }
}
