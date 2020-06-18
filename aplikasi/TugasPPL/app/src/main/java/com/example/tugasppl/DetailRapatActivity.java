package com.example.tugasppl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.tugasppl.Model.Rapat;

public class DetailRapatActivity extends AppCompatActivity {

    private TextView nama, hasil, tanggal;
    private Rapat dataRapat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_rapat);

        init();
        getData();
    }

    private void getData() {
        nama.setText(dataRapat.getNama());
        tanggal.setText(dataRapat.getTanggal());
        hasil.setText(dataRapat.getHasil());
    }

    private void init() {
        nama = findViewById(R.id.textViewDetailRapatNama);
        hasil = findViewById(R.id.textViewDetailRapatDeskripsi);
        tanggal = findViewById(R.id.textViewDetailRapatTanggal);
        dataRapat = (Rapat) getIntent().getSerializableExtra("DATA");
    }
}
