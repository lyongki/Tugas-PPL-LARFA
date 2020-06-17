package com.example.tugasppl.adapter;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasppl.Model.Surat;
import com.example.tugasppl.R;

import java.util.ArrayList;

public class SuratAdapter extends RecyclerView.Adapter<SuratAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Surat> dataSurat;

    public SuratAdapter(Context context, ArrayList<Surat> dataSurat) {
        this.context = context;
        this.dataSurat = dataSurat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_surat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Surat current = dataSurat.get(position);
        Log.d("data surat",dataSurat.get(position).getId()+"-"+dataSurat.get(position).getNama());
        holder.bindTo(current);
        Log.d("onbind","dipanggil");
    }

    @Override
    public int getItemCount() {
        return dataSurat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements ActivityCompat.OnRequestPermissionsResultCallback{
        private TextView tipe,tanggal,judul;

        private Button file;
        private DownloadManager downloadManager;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            init(itemView);

            file.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ){
                        if(context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        else
                            startDownload();
                    }
                }
            });

        }

        private void startDownload() {
            Uri uri = Uri.parse(dataSurat.get(getAdapterPosition()).getFile());
            String namaFile = uri.getLastPathSegment();
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setTitle(namaFile);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, namaFile);
            downloadManager.enqueue(request);
        }

        private void init(View itemView) {
            tipe = (TextView) itemView.findViewById(R.id.textViewSuratTipe);
            tanggal = (TextView) itemView.findViewById(R.id.textViewSuratTanggal);
            judul = (TextView) itemView.findViewById(R.id.textViewSuratJudul);
            file = (Button) itemView.findViewById(R.id.buttonSuratFile);
            downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        }

        public void bindTo(Surat current) {
            if(current.getRole().matches("0"))
                tipe.setText("Surat Masuk");
            else
                tipe.setText("Surat Keluar");
            tanggal.setText(current.getTanggal());
            judul.setText(current.getNama());
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            if(requestCode == 1){
                if(grantResults.length>0 &&grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    startDownload();
                else
                    Toast.makeText(context, "Perizinan dibutuhkan untuk download", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
