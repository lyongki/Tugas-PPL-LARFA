package com.example.tugasppl.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
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
import com.example.tugasppl.Model.Rapat;
import com.example.tugasppl.R;
import com.example.tugasppl.adapter.EmptyAdapter;
import com.example.tugasppl.adapter.RapatAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RapatFragment extends Fragment {

    private RecyclerView recyclerView;
    private RapatAdapter rapatAdapter;
    private EmptyAdapter emptyAdapter;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private ArrayList<Rapat> dataRapat;
    private SharedPreferences preferences;
    private String url = "http://192.168.43.47/ServiceTugasPPL.php";
    private Button search;
    private EditText namaSearch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_rapat, container, false);

        init(root);
        getData(dataRapat,0);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(dataRapat, 1);
                InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return root;
    }

    private void getData(final ArrayList<Rapat> dataRapat, final int code) {
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dataRapat.clear();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    if(jsonArray.length() == 0){
                        recyclerView.setAdapter(emptyAdapter);
                        emptyAdapter.notifyDataSetChanged();
                    }else{
                        for ( int i = 0; i<jsonArray.length();i++ ){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Rapat rapat = new Rapat(jsonObject.getString("nama"), jsonObject.getString("tanggal"), jsonObject.getString("hasil"));
                            dataRapat.add(rapat);
                        }
                        recyclerView.setAdapter(rapatAdapter);
                        rapatAdapter.notifyDataSetChanged();
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
                    map.put("flag", "search_rapat");
                    map.put("key", namaSearch.getText().toString());
                }
                else
                    map.put("flag","get_rapat");
                map.put("id_ukm",preferences.getString("id_ukm","0"));
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void init(View root) {
        recyclerView = root.findViewById(R.id.recyclerViewRapat);
        dataRapat = new ArrayList<>();
        rapatAdapter = new RapatAdapter(getActivity(), dataRapat);
        emptyAdapter = new EmptyAdapter(getActivity(), "Rapat");
        requestQueue = Volley.newRequestQueue(getActivity());
        preferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        search = root.findViewById(R.id.buttonRapatSearch);
        namaSearch = root.findViewById(R.id.editTextRapatSearch);
    }
}