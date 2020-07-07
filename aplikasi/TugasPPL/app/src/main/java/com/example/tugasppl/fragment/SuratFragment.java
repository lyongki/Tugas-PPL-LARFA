package com.example.tugasppl.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tugasppl.Model.Surat;
import com.example.tugasppl.R;
import com.example.tugasppl.adapter.EmptyAdapter;
import com.example.tugasppl.adapter.SuratAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SuratFragment extends Fragment {

    private Button search;
    private String tipe, text;
    private Spinner spinner;
    private ArrayAdapter<CharSequence> arrayAdapter;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private ArrayList<Surat> dataSurat;
    private SuratAdapter suratAdapter;
    private EmptyAdapter emptyAdapter;
    private RecyclerView recyclerView;
    private String url = "http://192.168.2.254/ServiceTugasPPL.php";
    private SharedPreferences preferences;
    private EditText namaSearch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_surat, container, false);
        init(root);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                namaSearch.setText("");
                tipe = getResources().getStringArray(R.array.spinner_surat_id)[position];
                text = parent.getItemAtPosition(position).toString();
                getData(dataSurat, tipe, text, 0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(dataSurat, tipe, text, 1);
                InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return root;
    }

    private void getData(final ArrayList<Surat> dataSurat, final String tipe, final String text, final int code) {
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dataSurat.clear();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    if(jsonArray.length() == 0){
                        emptyAdapter = new EmptyAdapter(getActivity(),text);
                        recyclerView.setAdapter(emptyAdapter);
                        emptyAdapter.notifyDataSetChanged();
                    }else {

                        for(int i = 0; i< jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Surat surat = new Surat(jsonObject.getString("id"), jsonObject.getString("nama"), jsonObject.getString("tanggal"), jsonObject.getString("role"), jsonObject.getString("file"));
                            dataSurat.add(surat);
                        }
                        recyclerView.setAdapter(suratAdapter);
                        suratAdapter.notifyDataSetChanged();
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
                    map.put("flag", "search_surat");
                    map.put("key", namaSearch.getText().toString());
                }
                else
                    map.put("flag","get_surat");
                map.put("id_ukm",preferences.getString("id_ukm","0"));
                map.put("tipe",tipe);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void init(View root) {
        search = root.findViewById(R.id.buttonSuratSearch);
        namaSearch =root.findViewById(R.id.editTextSuratSearch);
        spinner = root.findViewById(R.id.spinerr_surat);
        recyclerView = root.findViewById(R.id.recyclerViewSurat);
        arrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_surat, android.R.layout.simple_spinner_item);
        requestQueue = Volley.newRequestQueue(getActivity());
        dataSurat = new ArrayList<>();
        suratAdapter = new SuratAdapter(getActivity(),dataSurat);
        preferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);

    }
}