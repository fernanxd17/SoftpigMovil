package com.Softpig.Presenter.Adapters;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Softpig.Model.Article;
import com.Softpig.R;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolderArticle> {

    ArrayList<Article> listArticle;
    private static boolean toolEmployee;
    public ArticleAdapter(ArrayList<Article> listArticle, boolean toolEmployee) {
        this.listArticle = listArticle;
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

            holder.tv_idArticle.setText("ID: "+listArticle.get(position).getIdArticle());
            holder.tv_nameArticle.setText(listArticle.get(position).getName());
            holder.tv_typearticle.setText(listArticle.get(position).getTypeArticle());
            if(!toolEmployee)
                holder.tv_totalarticle.setText(listArticle.get(position).getQuantity()+" unidades");






    }

    @Override
    public int getItemCount() {
        return listArticle.size();
    }

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
