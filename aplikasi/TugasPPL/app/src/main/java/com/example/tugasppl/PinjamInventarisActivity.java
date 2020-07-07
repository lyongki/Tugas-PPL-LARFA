package com.example.tugasppl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tugasppl.Model.Inventaris;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PinjamInventarisActivity extends AppCompatActivity {

    private Spinner spinner;
    private ArrayAdapter arrayAdapter;
    private TextView jumlahTersedia;
    private EditText jumlahPinjam;
    private Button simpan;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private ArrayList<Inventaris> dataInventaris;
    private String url = "https://lyongkitan.000webhostapp.com/ServiceTugasPPL.php";
    private SharedPreferences preferences;
    private ArrayList<String> namaInventaris;
    private String id_inventaris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinjam_inventaris);

        init();
        getData(dataInventaris);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, namaInventaris);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (dataInventaris.size() > 0) {
                    id_inventaris = dataInventaris.get(position).getId();
                    jumlahTersedia.setText(dataInventaris.get(position).getJumlah_tersedia());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(jumlahPinjam.getText().toString()) > Integer.parseInt(jumlahTersedia.getText().toString()))
                    Toast.makeText(PinjamInventarisActivity.this, "Jumlah Inventaris tersedia tidak mencukupi", Toast.LENGTH_SHORT).show();
                else
                    save_pinjam();
            }
        });
    }

    private void save_pinjam() {
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getString("code").matches("1"))
                        finish();
                    else
                        Toast.makeText(PinjamInventarisActivity.this, "Gagal menyimpan", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PinjamInventarisActivity.this, "Gagal terhubung ke server", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("flag", "save_pinjam");
                map.put("jumlah", jumlahPinjam.getText().toString());
                map.put("id_inventaris", id_inventaris);
                map.put("id_pengurus", preferences.getString("id_pengurus", "0"));
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void init() {
        spinner = findViewById(R.id.spinerr_inventaris_pinjam);
        jumlahTersedia = findViewById(R.id.textViewInventarisNilaiJumlahTersedia);
        jumlahPinjam = findViewById(R.id.editTextInventarisJumlahPinjam);
        simpan = findViewById(R.id.buttonInventarisPinjam);
        preferences = getSharedPreferences("pref", MODE_PRIVATE);
        requestQueue = Volley.newRequestQueue(this);
        dataInventaris = new ArrayList<>();
        namaInventaris = new ArrayList<>();
    }

    private void getData(final ArrayList<Inventaris> dataInventaris) {
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    if (jsonArray.length() > 0) {
                        for ( int i = 0; i < jsonArray.length(); i++ ) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Inventaris inventaris = new Inventaris(jsonObject.getString("id"), jsonObject.getString("nama"), jsonObject.getString("jumlah_tersedia"));
                            dataInventaris.add(inventaris);
                        }
                        for ( int i = 0; i < dataInventaris.size(); i++ ) {
                            namaInventaris.add(dataInventaris.get(i).getNama());
                        }
                        arrayAdapter.notifyDataSetChanged();
                    } else {
                        namaInventaris.add("Tidak ada Inventaris Tersedia");
                        jumlahTersedia.setText("0");
                        spinner.setEnabled(false);
                        jumlahPinjam.setEnabled(false);
                        simpan.setEnabled(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PinjamInventarisActivity.this, "Gagal terhubung ke server", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("flag", "get_pinjam");
                map.put("id_ukm", preferences.getString("id_ukm", "0"));
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
