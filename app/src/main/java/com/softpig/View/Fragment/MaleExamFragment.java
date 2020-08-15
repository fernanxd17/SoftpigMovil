package com.softpig.View.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.softpig.model.ExamMale;
import com.softpig.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MaleExamFragment extends Fragment {

    private TextView tvValorCode, tvValorName, tvValorDate;
    private TextView tvValorDesc, tvValorResult;
    private View viewMaleExam;
    private ExamMale examMale;

    public MaleExamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewMaleExam = inflater.inflate(R.layout.fragment_male_exam, container, false);
        capturarCampos();
        modificarCampos();

        return viewMaleExam;
    }

    private void modificarCampos() {
        tvValorCode.setText(String.valueOf(examMale.getIdExam()));
        tvValorName.setText(examMale.getName());
        tvValorResult.setText(examMale.getResult());
        tvValorDesc.setText(examMale.getDescripcion());
        tvValorDate.setText(examMale.getExamDate());
    }

    private void capturarCampos() {
        tvValorCode = viewMaleExam.findViewById(R.id.tv_valor_code_male_exam);
        tvValorName = viewMaleExam.findViewById(R.id.tv_valor_name_male_exam);
        tvValorDate = viewMaleExam.findViewById(R.id.tv_valor_date_male_exam);
        tvValorDesc = viewMaleExam.findViewById(R.id.tv_valor_desc_male_exam);
        tvValorResult = viewMaleExam.findViewById(R.id.tv_valor_result_male_exam);
    }



    public void setMaleExamFragment(ExamMale examMale) {
        this.examMale = examMale;
    }
}
