package com.Softpig.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.Softpig.Model.Article;
import com.Softpig.Presenter.Adapters.ArticleAdapter;
import com.Softpig.Presenter.ArticlePresenter;
import com.Softpig.R;

import java.util.ArrayList;

import static com.Softpig.R.id.recyclerArticle;

public class ArticleActivity extends AppCompatActivity {

    private RecyclerView recyclerArticle;
    private ArticlePresenter articlePresenter;
    private ArticleAdapter articleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.articles);
        articlePresenter = new ArticlePresenter();
        recyclerArticle = findViewById(R.id.recyclerArticle);
        recyclerArticle.setLayoutManager(new LinearLayoutManager(this));
        articleAdapter = new ArticleAdapter(articlePresenter.getArticles());
        recyclerArticle.setAdapter(articleAdapter);
    }

}
