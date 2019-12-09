package com.Softpig.View.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softpig.Model.Medicine;
import com.Softpig.Presenter.Adapters.MedicineAdapter;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;

import java.util.ArrayList;
import java.util.List;

public class MedicineFragment extends Fragment {

    private RecyclerView recyclerMedicine;
    private MedicineAdapter medicineAdapter;
    private View viewListMedicine;
    private List<Medicine> listMedicine;
    private TextView tvNoMedicine;
    private SwipeRefreshLayout refrescarListMedicine;

    public MedicineFragment() {
        listMedicine = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewListMedicine =  inflater.inflate(R.layout.fragment_list_medicine, container, false);
        refrescarListMedicine = viewListMedicine.findViewById(R.id.refresh_list_medicine);
        refrescarListMedicine.setOnRefreshListener(() -> {
            ((MainMenuActivity)getContext()).actualizarListaMedicinas(refrescarListMedicine);
        });
        ((MainMenuActivity)getActivity()).setTitleTolbar("Medicamentos");
        ((MainMenuActivity)getActivity()).setSearch("Medicine");

        if (listMedicine.isEmpty()){
            tvNoMedicine = viewListMedicine.findViewById(R.id.tv_noMedicine);
            tvNoMedicine.setText("No existen medicamentos");
        }
        medicineAdapter = new MedicineAdapter(this.listMedicine, getContext());
        recyclerMedicine = viewListMedicine.findViewById(R.id.recyclerMedicine);
        recyclerMedicine.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerMedicine.setAdapter(medicineAdapter);

        return viewListMedicine;
    }

    public void setListMedicine(List <Medicine> listMedicine){
        this.listMedicine = listMedicine;
    }

    public MedicineAdapter getMedicineAdapter() {
        return medicineAdapter;
    }

    public void notificarAdapter() {
        if (listMedicine.isEmpty()){
            tvNoMedicine = viewListMedicine.findViewById(R.id.tv_noMedicine);
            tvNoMedicine.setText("No existen medicamentos");
        }
        medicineAdapter = new MedicineAdapter(this.listMedicine, getContext());
        recyclerMedicine = viewListMedicine.findViewById(R.id.recyclerMedicine);
        recyclerMedicine.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerMedicine.setAdapter(medicineAdapter);
    }

    public void eliminarMedicina(final short idMedicine) {
        for (int i = 0; i < listMedicine.size(); i++){
            if(listMedicine.get(i).getIdMedicine() == idMedicine) {
                listMedicine.remove(i);
                break;
            }
        }
    }
}