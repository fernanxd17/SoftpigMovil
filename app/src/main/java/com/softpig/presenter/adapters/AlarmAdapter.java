package com.softpig.presenter.adapters;

import android.content.Context;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.softpig.model.Alarm;
import com.softpig.R;
import com.softpig.View.MainMenuActivity;
import java.util.ArrayList;
import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolderAlarm> implements Filterable {

    private List<Alarm> listAlarm;
    private List<Alarm> listAlarmFull;
    private Context context;

    public AlarmAdapter(List<Alarm> listAlarm, Context context) {
        this.listAlarm = listAlarm;
        listAlarmFull = new ArrayList<>(listAlarm);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderAlarm onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_alarm,parent, false);
        return new ViewHolderAlarm(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAlarm holder, int position) {
        Alarm alarm = listAlarm.get(position);
        holder.tvAsunto.setText(alarm.getIssue());
        holder.tvFecha.setText(alarm.getDate());
        holder.tvHora.setText(alarm.getHour());
        holder.ivRemoveAlarm.setOnClickListener(view -> ((MainMenuActivity)context).eliminarAlarma());
    }

    @Override
    public int getItemCount() {
        return listAlarm.size();
    }

    @Override
    public Filter getFilter() {
        return alarmFilter;
    }

    private Filter alarmFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Alarm> listaFiltrada = new ArrayList<>();
            if(charSequence == null || charSequence.length()==0){
                listaFiltrada.addAll(listAlarmFull);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(Alarm alarm: listAlarmFull){
                    if(String.valueOf(alarm.getIssue()).toLowerCase().contains(filterPattern) ||
                            String.valueOf(alarm.getIdAlarm()).toLowerCase().contains(filterPattern)){
                        listaFiltrada.add(alarm);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = listaFiltrada;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listAlarm.clear();
            listAlarm.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolderAlarm extends RecyclerView.ViewHolder {

        private TextView tvAsunto;
        private TextView tvFecha;
        private TextView tvHora;
        private ImageView ivRemoveAlarm;

        public ViewHolderAlarm(@NonNull View itemView) {
            super(itemView);
            tvAsunto = itemView.findViewById(R.id.tv_asunto);
            tvFecha = itemView.findViewById(R.id.tv_valor_fecha);
            tvHora = itemView.findViewById(R.id.tv_valor_hora);
            ivRemoveAlarm = itemView.findViewById(R.id.iv_remove_alarm);
        }
    }
}