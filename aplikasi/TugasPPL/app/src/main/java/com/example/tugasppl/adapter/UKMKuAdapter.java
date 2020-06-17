package com.example.tugasppl.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tugasppl.Model.Event;
import com.example.tugasppl.R;
import com.example.tugasppl.UKMEventActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UKMKuAdapter extends RecyclerView.Adapter<UKMKuAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Event> dataEvent;

    public UKMKuAdapter(Context context, ArrayList<Event> dataEvent) {
        this.context = context;
        this.dataEvent = dataEvent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_ukmku, parent, false));
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
        private TextView namaUkm;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            init(itemView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, UKMEventActivity.class);
                    intent.putExtra("id_ukm",dataEvent.get(getAdapterPosition()).getId_ukm());
                    context.startActivity(intent);
                }
            });
        }

        private void init(View itemView) {
            namaUkm = (TextView) itemView.findViewById(R.id.textViewUkmkuNama);
            cardView = (CardView) itemView.findViewById(R.id.cardViewUkmKu);
        }

        public void bindTo(Event current) {
            namaUkm.setText(current.getNama_ukm());
        }
    }
}
