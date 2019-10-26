package com.Softpig.View.fragment.Tool;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softpig.Presenter.Adapters.ArticleAdapter;
import com.Softpig.Presenter.ArticlePresenter;
import com.Softpig.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToolFragment extends Fragment {

    private RecyclerView recyclerArticle;
    private ArticlePresenter articlePresenter;
    private ArticleAdapter articleAdapter;

    public ToolFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_tools, container, false);

        articlePresenter = new ArticlePresenter();
        recyclerArticle = view.findViewById(R.id.recyclerArticle);
        recyclerArticle.setLayoutManager(new LinearLayoutManager(getContext()));
        articleAdapter = new ArticleAdapter(articlePresenter.getArticles());
        recyclerArticle.setAdapter(articleAdapter);


        return view;
    }

}
