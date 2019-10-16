package com.Softpig.Presenter.Adapters;

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

    public ArticleAdapter(ArrayList<Article> listArticle) {
        this.listArticle = listArticle;
    }

    @NonNull
    @Override
    public ViewHolderArticle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_employee,null, false);
        return new ViewHolderArticle(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderArticle holder, int position) {
        holder.tv_idArticle.setText(listArticle.get(position).getIdArticle());
        holder.tv_nameArticle.setText(listArticle.get(position).getName());
        holder.tv_totalarticle.setText(listArticle.get(position).getQuantity());
        holder.tv_typearticle.setText(listArticle.get(position).getTypeArticle());
        //holder.imagenEmployee.setImageResource('@drawable/');
    }

    @Override
    public int getItemCount() {
        return listArticle.size();
    }

    public class ViewHolderArticle extends RecyclerView.ViewHolder {

        ImageView imagenArticle, icon_delete;
        TextView tv_nameArticle, tv_totalarticle, tv_typearticle, tv_idArticle;

        public ViewHolderArticle(@NonNull View itemView) {
            super(itemView);
            tv_nameArticle = (TextView) itemView.findViewById(R.id.tv_nameArticle);
            tv_totalarticle = (TextView) itemView.findViewById(R.id.tv_totalarticle);
            tv_typearticle = (TextView) itemView.findViewById(R.id.tv_typearticle);
            tv_idArticle = (TextView) itemView.findViewById(R.id.tv_idArticle);
        }
    }
}
