package com.example.tugasppl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasppl.Model.History;
import com.example.tugasppl.R;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context context;
    private ArrayList<History> dataHistory;

    public HistoryAdapter(Context context, ArrayList<History> dataHistory) {
        this.context = context;
        this.dataHistory = dataHistory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_histori, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        History current = dataHistory.get(position);
        holder.bindTo(current);
    }

    @Override
    public int getItemCount() {
        return dataHistory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tanggal, jumlah, keterangan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            init(itemView);
        }

        private void init(View itemView) {
            tanggal = itemView.findViewById(R.id.textViewHistoryTanggal);
            jumlah = itemView.findViewById(R.id.textViewHistoryJumlah);
            keterangan = itemView.findViewById(R.id.textViewHistoryKeterangan);
        }

        public void bindTo(History current) {
            tanggal.setText(current.getTanggal());
            keterangan.setText(current.getKeterangan());
            jumlah.setText(current.getJumlah());
        }
    }
}
