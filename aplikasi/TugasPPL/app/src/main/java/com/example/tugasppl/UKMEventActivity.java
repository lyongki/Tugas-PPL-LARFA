package com.example.tugasppl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tugasppl.Model.Event;
import com.example.tugasppl.adapter.UKMEventAdapter;
import com.example.tugasppl.adapter.UKMKuAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UKMEventActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UKMEventAdapter ukmEventAdapter;
    private ArrayList<Event> dataEvent;
    private StringRequest stringRequest;
    private RequestQueue requestQueue;
    private String url = "http://192.168.1.4/ServiceTugasPPL.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ukmevent);

        init();
        getData(dataEvent);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(ukmEventAdapter);
    }

    private void getData(final ArrayList<Event> dataEvent) {
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Event event = new Event(jsonObject.getString("id"), jsonObject.getString("id_ukm"), jsonObject.getString("nama_event"), jsonObject.getString("nama_ukm"),
                                jsonObject.getString("thumbnail"), jsonObject.getString("tanggal"), jsonObject.getString("deskripsi"));
                        dataEvent.add(event);
                        ukmEventAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UKMEventActivity.this, "Gagal terhubung ke server",Toast.LENGTH_SHORT).show();
                Log.d("Log dari "+ UKMKuAdapter.class.getSimpleName(),error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("flag","get_ukm_all_event");
                map.put("id_ukm",getIntent().getStringExtra("id_ukm"));
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewUkmEvent);
        dataEvent = new ArrayList<>();
        ukmEventAdapter = new UKMEventAdapter(this, dataEvent);
        requestQueue = Volley.newRequestQueue(this);
    }
}
