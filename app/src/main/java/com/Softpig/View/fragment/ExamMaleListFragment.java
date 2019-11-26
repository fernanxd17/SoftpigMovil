package com.Softpig.View.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.Softpig.Model.ExamMale;
import com.Softpig.Model.Pig;
import com.Softpig.Presenter.Adapters.ExamMaleAdapter;
import com.Softpig.Presenter.Adapters.MaleAdapter;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;
import com.Softpig.View.PigActivity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExamMaleListFragment extends Fragment {
    private View viewInfoMale;
    private List<ExamMale> listExamMale;
    private RecyclerView recyclerMaleExam;
    private ExamMaleAdapter examMaleAdapter;
    private TextView tvNoMaleExam;


    public ExamMaleListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewInfoMale =  inflater.inflate(R.layout.fragment_list_male_exam, container, false);
        ((PigActivity)getActivity()).setSearch("MaleExam");
        tvNoMaleExam = viewInfoMale.findViewById(R.id.tv_noExamMale);
        if(listExamMale.isEmpty()){
            tvNoMaleExam.setText("No hay registro de examanes");
        }else{
            examMaleAdapter = new ExamMaleAdapter(this.listExamMale, getContext());
            recyclerMaleExam = viewInfoMale.findViewById(R.id.recyclerListExamMale);
            recyclerMaleExam.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerMaleExam.setAdapter(examMaleAdapter);
        }
        return viewInfoMale;
    }

    public void setListExamMale(List<ExamMale> listExamMale) {
        this.listExamMale = listExamMale;
    }

    public ExamMaleAdapter getMaleExamAdapter() {
        return examMaleAdapter;
    }

}
