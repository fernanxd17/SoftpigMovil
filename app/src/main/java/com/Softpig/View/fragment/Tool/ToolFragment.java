package com.Softpig.View.fragment.Tool;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Softpig.Presenter.Adapters.ArticleAdapter;
import com.Softpig.Presenter.ArticlePresenter;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;
import com.Softpig.View.ProfileActivity;
import com.Softpig.View.fragment.AddToolEmployeeDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToolFragment extends Fragment  implements AddToolEmployeeDialog.AddToolEmployeeListerner{

    private RecyclerView recyclerArticle;
    private ArticlePresenter articlePresenter;
    private ArticleAdapter articleAdapter;
    private static boolean toolEmployee = false;
    private FloatingActionButton fbAddArticle;
    public ToolFragment() {
        // Required empty public constructor

    }

    public ToolFragment(boolean toolEmployee){
        this.toolEmployee = toolEmployee;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_tools, container, false);
        articlePresenter = new ArticlePresenter();

        recyclerArticle = view.findViewById(R.id.recyclerArticle);
        recyclerArticle.setLayoutManager(new LinearLayoutManager(getContext()));
        articleAdapter = new ArticleAdapter(articlePresenter.getArticles(), toolEmployee);
        recyclerArticle.setAdapter(articleAdapter);
        fbAddArticle = view.findViewById(R.id.fb_add_tool_employee);
        if(!toolEmployee)
            fbAddArticle.hide();
        else{
            fbAddArticle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openDialog();
                }
            });
        }







        return view;
    }

    public void openDialog() {
        AddToolEmployeeDialog addToolEmployeeDialog = new AddToolEmployeeDialog();
        addToolEmployeeDialog.show(getFragmentManager(), "Article Dialog");
    }



    @Override
    public void agregarArticulo(String nameArticle) {
        ((ProfileActivity) getActivity()).agregarArticle(nameArticle);
    }
}
