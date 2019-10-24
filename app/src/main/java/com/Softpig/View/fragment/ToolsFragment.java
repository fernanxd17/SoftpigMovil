package com.Softpig.View.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Softpig.Presenter.Adapters.ArticleAdapter;
import com.Softpig.Presenter.ArticlePresenter;
import com.Softpig.R;
import com.Softpig.ui.tools.ToolsViewModel;

import static com.Softpig.R.id.recyclerArticle;

public class ToolsFragment extends Fragment {

    private RecyclerView recyclerArticle;
    private ArticlePresenter articlePresenter;
    private ArticleAdapter articleAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_tools, container, false);
        articlePresenter = new ArticlePresenter();
        recyclerArticle = view.findViewById(R.id.recyclerArticle);
        recyclerArticle.setLayoutManager(new LinearLayoutManager(getContext()));
        articleAdapter = new ArticleAdapter(articlePresenter.getArticles());
        recyclerArticle.setAdapter(articleAdapter);

        return view;
    }
}