package com.example.tugasppl.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tugasppl.Model.Event;
import com.example.tugasppl.R;
import com.example.tugasppl.adapter.HomeAdapter;
import com.example.tugasppl.adapter.UKMKuAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class UKMKuFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Event> dataEvent;
    private UKMKuAdapter ukmKuAdapter;
    private StringRequest stringRequest;
    private RequestQueue requestQueue;
    private String url = "http://192.168.1.4/ServiceTugasPPL.php";

    public UKMKuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_ukmku, container, false);
        init(root);

        getData(dataEvent);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(ukmKuAdapter);
        return root;
    }

    private void getData(final ArrayList<Event> dataEvent) {
        stringRequest = new StringRequest(Request.Method.POST,url , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Event event = new Event(jsonObject.getString("id"), jsonObject.getString("nama_ukm"));
                        dataEvent.add(event);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ukmKuAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Gagal terhubung ke server",Toast.LENGTH_SHORT).show();
                Log.d("Log dari "+UKMKuAdapter.class.getSimpleName(),error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("flag","get_ukm");
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void init(View root) {
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerViewUkmku);
        dataEvent= new ArrayList<>();
        ukmKuAdapter = new UKMKuAdapter(getContext(), dataEvent);
        requestQueue = Volley.newRequestQueue(getContext());
    }

}
