package com.example.tugasppl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasppl.R;

public class EmptyAdapter extends RecyclerView.Adapter<EmptyAdapter.ViewHolder> {

    private Context context;
    private String text;

    public EmptyAdapter(Context context, String text) {
        this.context = context;
        this.text = text;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_empty, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText("Tidak Ada "+text);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView  = itemView.findViewById(R.id.textViewEmpty);
        }
    }
}
