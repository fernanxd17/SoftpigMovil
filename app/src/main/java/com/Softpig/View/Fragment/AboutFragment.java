package com.Softpig.View.Fragment;

import com.Softpig.R;
import android.view.*;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

public class AboutFragment extends Fragment {
    
    public AboutFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //((MainMenuActivity)getActivity()).setTitleTolbar("Acerca De");
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        return view;
    }
}