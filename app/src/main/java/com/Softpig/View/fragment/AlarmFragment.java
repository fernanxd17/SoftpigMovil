package com.Softpig.View.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softpig.Model.Alarm;
import com.Softpig.Presenter.Adapters.AlarmAdapter;
import com.Softpig.Presenter.Adapters.MaleAdapter;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;

import java.util.ArrayList;
import java.util.List;

public class AlarmFragment extends Fragment {

    private RecyclerView recyclerAlarm;
    private AlarmAdapter alarmAdapter;
    private List<Alarm> listAlarm;
    private View viewAlarm;
    private TextView tvNoAlarm;
    public AlarmFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewAlarm = inflater.inflate(R.layout.fragment_list_alarm, container, false);

        ((MainMenuActivity)getActivity()).setSearch("Alarm");
        if (listAlarm.isEmpty()){
            tvNoAlarm = viewAlarm.findViewById(R.id.tv_noAlarms);
            tvNoAlarm.setText("No existen alarmas");
        }
        alarmAdapter = new AlarmAdapter(this.listAlarm, getContext());
        recyclerAlarm = viewAlarm.findViewById(R.id.recyclerAlarms);
        recyclerAlarm.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerAlarm.setAdapter(alarmAdapter);
        return viewAlarm;
    }

    public void setListAlarm(ArrayList<Alarm> listAlarms) {
        this.listAlarm = listAlarms;
    }

    public AlarmAdapter getAlarmAdapter() {

        return alarmAdapter;
    }
}
