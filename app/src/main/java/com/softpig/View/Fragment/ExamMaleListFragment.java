package com.softpig.View.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.softpig.model.ExamMale;
import com.softpig.presenter.adapters.ExamMaleAdapter;
import com.softpig.R;
import com.softpig.View.PigActivity;

import java.util.List;

public class ExamMaleListFragment extends Fragment {
    private View viewInfoMale;
    private List<ExamMale> listExamMale;
    private RecyclerView recyclerMaleExam;
    private ExamMaleAdapter examMaleAdapter;
    private TextView tvNoMaleExam;
    private SwipeRefreshLayout refreshListExamMale;


    public ExamMaleListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewInfoMale =  inflater.inflate(R.layout.fragment_list_male_exam, container, false);
        refreshListExamMale = viewInfoMale.findViewById(R.id.refresh_list_exam_male);
        refreshListExamMale.setOnRefreshListener(() -> ((PigActivity)getContext()).actualizarListMaleExam(refreshListExamMale));
        ((PigActivity)getActivity()).setSearch("MaleExam");
        tvNoMaleExam = viewInfoMale.findViewById(R.id.tv_noExamMale);
        tvNoMaleExam.setText("No hay registro de examanes");
        if(!listExamMale.isEmpty()){
            tvNoMaleExam.setText(listExamMale.size() + " Examen(es) registrado(s)");
        }
            examMaleAdapter = new ExamMaleAdapter(this.listExamMale, getContext());
            recyclerMaleExam = viewInfoMale.findViewById(R.id.recyclerListExamMale);
            recyclerMaleExam.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerMaleExam.setAdapter(examMaleAdapter);

        return viewInfoMale;
    }

    public void setListExamMale(List<ExamMale> listExamMale) {
        this.listExamMale = listExamMale;
    }

    public ExamMaleAdapter getMaleExamAdapter() {
        return examMaleAdapter;
    }

    public void notificarAdapter() {
        tvNoMaleExam.setText("No hay registro de examanes");
        if(!listExamMale.isEmpty()){
            tvNoMaleExam.setText(listExamMale.size() + " Examen(es) registrado(s)");
        }

        examMaleAdapter = new ExamMaleAdapter(this.listExamMale, getContext());
        recyclerMaleExam = viewInfoMale.findViewById(R.id.recyclerListExamMale);
        recyclerMaleExam.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerMaleExam.setAdapter(examMaleAdapter);

    }
}