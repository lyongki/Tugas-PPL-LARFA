package com.example.tugasppl.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tugasppl.Model.Event;
import com.example.tugasppl.R;
import com.example.tugasppl.DetailEventActivity;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Event> dataEvent;

    public HomeAdapter(Context context, ArrayList<Event> dataEvent) {
        this.context = context;
        this.dataEvent = dataEvent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_home, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event current = dataEvent.get(position);
        holder.bindTo(current);
    }

    @Override
    public int getItemCount() {
        return dataEvent.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView thumbnail;
        private TextView nama_event, nama_ukm, tanggal;
        private CardView event;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            init(itemView);
            event.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailEventActivity.class);
                    intent.putExtra("DATA",dataEvent.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }

        private void init(View itemView) {
            thumbnail = (ImageView) itemView.findViewById(R.id.imageViewHomeThumbnail);
            nama_event = (TextView) itemView.findViewById(R.id.textViewHomeNamaEvent);
            nama_ukm = (TextView) itemView.findViewById(R.id.textViewHomeNamaUkm);
            tanggal = (TextView) itemView.findViewById(R.id.textViewHomeTanggal);
            event = (CardView) itemView.findViewById(R.id.cardViewHome);
        }

        public void bindTo(Event current) {
            nama_ukm.setText(current.getNama_ukm());
            nama_event.setText(current.getNama_event());
            tanggal.setText(current.getTanggal());
            Glide.with(context).load(current.getThumbnail()).into(thumbnail);
        }
    }
}
