package com.example.tugasppl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasppl.Model.Rapat;
import com.example.tugasppl.R;

import java.util.ArrayList;

public class RapatAdapter extends RecyclerView.Adapter<RapatAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Rapat> dataRapat;

    public RapatAdapter(Context context, ArrayList<Rapat> dataRapat) {
        this.context = context;
        this.dataRapat = dataRapat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_rapat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Rapat current = dataRapat.get(position);
        holder.bindTo(current);
    }

    @Override
    public int getItemCount() {
        return dataRapat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tanggal,rapat;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            init(itemView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        private void init(View itemView) {
            tanggal = itemView.findViewById(R.id.textViewRapatTanggal);
            rapat = itemView.findViewById(R.id.textViewRapatJudul);
            cardView = itemView.findViewById(R.id.cardViewRapat);
        }

        public void bindTo(Rapat current) {
            tanggal.setText(current.getTanggal());
            rapat.setText(current.getNama());
        }
    }
}
