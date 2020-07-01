package com.example.tugasppl.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.tugasppl.Model.Event;
import com.example.tugasppl.Model.Surat;
import com.example.tugasppl.R;
import com.example.tugasppl.adapter.EmptyAdapter;
import com.example.tugasppl.adapter.EvaluasiAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EvaluasiFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Event> dataEvent;
    private StringRequest stringRequest;
    private RequestQueue requestQueue;
    private String url = "http://192.168.1.4/ServiceTugasPPL.php";
    private SharedPreferences preferences;
    private EmptyAdapter emptyAdapter;
    private EvaluasiAdapter evaluasiAdapter;
    private Button search;
    private EditText namaSearch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_evaluasi, container, false);

        init(root);
        getData(dataEvent, 0);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(dataEvent, 1);
                InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return root;
    }

    private void getData(final ArrayList<Event> dataEvent, final int code) {
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dataEvent.clear();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    if(jsonArray.length() == 0){
                        recyclerView.setAdapter(emptyAdapter);
                        emptyAdapter.notifyDataSetChanged();
                    }else {
                        for(int i = 0; i< jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Event event = new Event(jsonObject.getString("id"), jsonObject.getString("id_ukm"), jsonObject.getString("nama_event"), jsonObject.getString("nama_ukm"), jsonObject.getString("thumbnail"), jsonObject.getString("tanggal"), jsonObject.getString("evaluasi"), jsonObject.getString("deskripsi"));
                            dataEvent.add(event);
                        }
                        recyclerView.setAdapter(evaluasiAdapter);
                        evaluasiAdapter.notifyDataSetChanged();
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
                if(code == 1) {
                    map.put("flag", "search_evaluasi");
                    map.put("key", namaSearch.getText().toString());
                }
                else
                    map.put("flag","get_evaluasi");
                    map.put("id_ukm",preferences.getString("id_ukm","0"));
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void init(View root) {
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerViewEvaluasi);
        dataEvent= new ArrayList<>();
        requestQueue = Volley.newRequestQueue(getActivity());
        preferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        emptyAdapter = new EmptyAdapter(getActivity(), "Evaluasi Event");
        evaluasiAdapter = new EvaluasiAdapter(getActivity(), dataEvent);
        search = root.findViewById(R.id.buttonEvaluasiSearch);
        namaSearch = root.findViewById(R.id.editTextEvaluasiSearch);
    }
}