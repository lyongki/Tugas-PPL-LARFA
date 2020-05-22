package com.example.tugasppl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tugasppl.Model.Event;

import java.util.ArrayList;

public class DetailEventActivity extends AppCompatActivity {

    private TextView nama_event, nama_ukm, tanggal, deskripsi;
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
    }

    private void init() {
        nama_event = (TextView) findViewById(R.id.textViewDetailEventNamaEvent);
        nama_ukm = (TextView) findViewById(R.id.textViewDetailEventNamaUkm);
        tanggal = (TextView) findViewById(R.id.textViewDetailEventTanggal);
        deskripsi = (TextView) findViewById(R.id.textViewDetailEventDeskripsi);
        thumbnail = (ImageView) findViewById(R.id.imageViewDetailEventThumbnail);
        dataEvent = (Event) getIntent().getSerializableExtra("DATA");
    }
}
