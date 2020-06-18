package com.example.tugasppl.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tugasppl.PinjamInventarisActivity;
import com.example.tugasppl.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InventarisFragment extends Fragment {

    private TableLayout tableLayout;
    private Spinner spinner;
    private SharedPreferences preferences;
    private ArrayAdapter arrayAdapter;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private FloatingActionButton fab;
    private String url = "http://192.168.43.47/ServiceTugasPPL.php";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_inventaris, container, false);

        init(root);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tableLayout.removeViews(1,(tableLayout.getChildCount()-1));
                String tipe = getResources().getStringArray(R.array.spinner_inventaris_id)[position];
                getData(tipe);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PinjamInventarisActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

    private void getData(final String tipe) {
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    if(jsonArray.length() > 0){
                        for(int i = 0; i<jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            TextView no = new TextView(getActivity());
                            TextView nama = new TextView(getActivity());
                            TextView jumlah = new TextView(getActivity());

                            no.setText(Integer.toString(i+1));
                            nama.setText(jsonObject.getString("nama"));
                            jumlah.setText(jsonObject.getString("jumlah"));

                            TableRow.LayoutParams lpNo = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            lpNo.weight=1;
                            lpNo.setMargins(1,0,1,1);
                            TableRow.LayoutParams lpNama = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            lpNama.weight=6;
                            lpNama.setMargins(1,0,1,1);
                            TableRow.LayoutParams lpJumlah = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            lpJumlah.weight=2;
                            lpJumlah.setMargins(1,0,1,1);

                            no.setLayoutParams(lpNo);
                            nama.setLayoutParams(lpNama);
                            jumlah.setLayoutParams(lpJumlah);

                            no.setBackgroundColor(Color.WHITE);
                            nama.setBackgroundColor(Color.WHITE);
                            jumlah.setBackgroundColor(Color.WHITE);

                            no.setGravity(Gravity.CENTER);
                            nama.setPadding(16,0,0,0);
                            jumlah.setGravity(Gravity.CENTER);

                            TableRow tableRow = new TableRow(getActivity());
                            tableRow.addView(no);
                            tableRow.addView(nama);
                            tableRow.addView(jumlah);

                            tableLayout.addView(tableRow);
                        }
                    }else {
                        TextView kosong = new TextView(getActivity());
                        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 200);
                        layoutParams.weight =1;
                        layoutParams.setMargins(2,0,2,2);
                        kosong.setLayoutParams(layoutParams);
                        kosong.setBackgroundColor(Color.WHITE);
                        if(tipe.matches("0"))
                            kosong.setText("Tidak ada Inventaris Tersedia");
                        else if(tipe.matches("1"))
                            kosong.setText("Tidak ada Inventaris Dipinjam");
                        else
                            kosong.setText("Tidak ada Inventaris");
                        TableRow tableRow = new TableRow(getActivity());
                        tableRow.addView(kosong);
                        tableLayout.addView(tableRow);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Gagal terhubung ke server",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("flag","get_inventaris");
                map.put("id_ukm",preferences.getString("id_ukm","0"));
                map.put("tipe",tipe);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void init(View root) {
        spinner = root.findViewById(R.id.spinerr_inventaris);
        tableLayout = root.findViewById(R.id.tableLayoutInventaris);
        preferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        arrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_inventaris, android.R.layout.simple_spinner_item);
        requestQueue = Volley.newRequestQueue(getActivity());
        fab = root.findViewById(R.id.fabInventaris);
    }
}
