package com.example.tugasppl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tugasppl.Model.Event;

import java.util.ArrayList;

public class DetailEventActivity extends AppCompatActivity {

    private TextView nama_event, nama_ukm, tanggal, deskripsi;
    private LinearLayout layout;
    private ImageView thumbnail;
    private Event dataEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);

        init();
        getData();
    }

    private void getData() {
        nama_event.setText(dataEvent.getNama_event());
        nama_ukm.setText(dataEvent.getNama_ukm());
        tanggal.setText(dataEvent.getTanggal());
        deskripsi.setText(dataEvent.getDeskripsi());
        Glide.with(this).load(dataEvent.getThumbnail()).into(thumbnail);
        if(dataEvent.getEvaluasi() != null){
            TextView tvEvaluasi, tvHasil;
            tvEvaluasi = new TextView(this);
            tvHasil = new TextView(this);
            tvEvaluasi.setText("Evaluasi :");
            tvEvaluasi.setTextAppearance(R.style.TextAppearance_AppCompat_Title);
            tvHasil.setText(dataEvent.getEvaluasi());
            layout.addView(tvEvaluasi);
            layout.addView(tvHasil);
        }
    }

    private void init() {
        nama_event = (TextView) findViewById(R.id.textViewDetailEventNamaEvent);
        nama_ukm = (TextView) findViewById(R.id.textViewDetailEventNamaUkm);
        tanggal = (TextView) findViewById(R.id.textViewDetailEventTanggal);
        deskripsi = (TextView) findViewById(R.id.textViewDetailEventDeskripsi);
        thumbnail = (ImageView) findViewById(R.id.imageViewDetailEventThumbnail);
        layout = findViewById(R.id.layoutEvaluasi);
        dataEvent = (Event) getIntent().getSerializableExtra("DATA");
    }
}
