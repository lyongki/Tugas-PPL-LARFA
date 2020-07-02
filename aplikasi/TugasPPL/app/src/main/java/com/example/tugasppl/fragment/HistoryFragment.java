package com.example.tugasppl.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tugasppl.Model.History;
import com.example.tugasppl.R;
import com.example.tugasppl.adapter.EmptyAdapter;
import com.example.tugasppl.adapter.HistoryAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HistoryFragment extends Fragment {

    private SharedPreferences preferences;
    private TextView saldo;
    private RecyclerView recyclerView;
    private EmptyAdapter emptyAdapter;
    private HistoryAdapter historyAdapter;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private String url = "http://192.168.1.4/ServiceTugasPPL.php";
    private ArrayList<History> dataHistory;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_history, container, false);

        init(root);
        getData(dataHistory);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(emptyAdapter);
        return root;
    }

    private void getData(final ArrayList<History> dataHistory) {
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    saldo.setText(jsonObject.getString("saldo"));
                    JSONArray jsonArray = jsonObject.getJSONArray("list");
                    if (jsonArray.length() > 0){
                        for(int i = 0 ;i < jsonArray.length();i++){
                            JSONObject list = jsonArray.getJSONObject(i);
                            History history = new History(list.getString("tanggal"), list.getString("jumlah"), list.getString("keterangan"));
                            dataHistory.add(history);
                        }
                        recyclerView.setAdapter(historyAdapter);
                        historyAdapter.notifyDataSetChanged();;
                    }else
                        recyclerView.setAdapter(emptyAdapter);
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
                map.put("flag","get_history");
                map.put("id_ukm",preferences.getString("id_ukm","0"));
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void init(View root) {
        saldo = root.findViewById(R.id.textViewHistorySaldo);
        recyclerView = root.findViewById(R.id.recyclerViewHistory);
        dataHistory = new ArrayList<>();
        emptyAdapter = new EmptyAdapter(getActivity(), "Histori Bulanan");
        historyAdapter = new HistoryAdapter(getActivity(), dataHistory);
        preferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        requestQueue = Volley.newRequestQueue(getActivity());
    }
}