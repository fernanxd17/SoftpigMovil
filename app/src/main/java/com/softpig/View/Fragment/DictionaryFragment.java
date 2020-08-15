package com.softpig.View.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.softpig.R;

public class DictionaryFragment extends Fragment {

    public DictionaryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //((MainMenuActivity)getActivity()).setTitleTolbar("Diccionario Porcino");
        return inflater.inflate(R.layout.fragment_dictionary, container, false);
    }
}