package com.example.tugasppl.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
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
import com.example.tugasppl.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UangFragment extends Fragment {

    private TextView saldo,nama,nim;
    private RelativeLayout layoutSaldo,layoutKeterangan;
    private EditText jumlah,keterangan,isiNama,isiNim;
    private Button simpan;
    private SharedPreferences preferences;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private String url = "http://192.168.2.254/ServiceTugasPPL.php";
    private String nilaiSaldo,tipe,code,mNim,mNama,mJumlah,mKeterangan;;
    private Spinner spinner;
    private ArrayAdapter arrayAdapter;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_uang_masuk_keluar, container, false);

        init(root);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                tipe = getResources().getStringArray(R.array.spinner_uang_id)[position];
                //tipe 0 =  uang masuk, 1 = uang keluar
                keterangan.setText("");
                jumlah.setText("");
                isiNama.setText("");
                isiNim.setText("");
                radioGroup.clearCheck();

                if(tipe.matches("1")) {
                    getData();
                    layoutSaldo.setVisibility(View.VISIBLE);
                    radioGroup.setVisibility(View.GONE);
                    layoutKeterangan.setVisibility(View.VISIBLE);
                    keterangan.setVisibility(View.VISIBLE);
                    nama.setVisibility(View.GONE);
                    nim.setVisibility(View.GONE);
                    isiNim.setVisibility(View.GONE);
                    isiNama.setVisibility(View.GONE);
                }else {
                    saldo.setText("Banyak Saldo");
                    layoutSaldo.setVisibility(View.GONE);
                    layoutKeterangan.setVisibility(View.GONE);
                    radioGroup.setVisibility(View.VISIBLE);
                    keterangan.setVisibility(View.GONE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //code 0 = kas, 1 = lainnya
                layoutKeterangan.setVisibility(View.VISIBLE);
                if(checkedId == R.id.radioButtonUangKas){
                    keterangan.setVisibility(View.GONE);
                    code = "0";
                    nama.setVisibility(View.VISIBLE);
                    nim.setVisibility(View.VISIBLE);
                    isiNama.setVisibility(View.VISIBLE);
                    isiNim.setVisibility(View.VISIBLE);
                }else {
                    keterangan.setVisibility(View.VISIBLE);
                    code = "1";
                    nama.setVisibility(View.GONE);
                    nim.setVisibility(View.GONE);
                    isiNim.setVisibility(View.GONE);
                    isiNama.setVisibility(View.GONE);
                }

            }
        });
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tipe.matches("1")){
                    if(!jumlah.getText().toString().isEmpty() && !keterangan.getText().toString().isEmpty()) {
                        if (Integer.parseInt(jumlah.getText().toString()) > Integer.parseInt(saldo.getText().toString()))
                            Toast.makeText(getActivity(), "Tidak dapat melebihi saldo",Toast.LENGTH_SHORT).show();
                        else{
                            saveData();
                            jumlah.setText("");
                            keterangan.setText("");
                            getData();
                        }
                    }
                    else
                        Toast.makeText(getActivity(), "Harap isi terlebih dahulu",Toast.LENGTH_SHORT).show();
                }else{
                    if(radioGroup.getCheckedRadioButtonId() == -1){
                        Toast.makeText(getActivity(), "Harap isi terlebih dahulu",Toast.LENGTH_SHORT).show();
                    }else {
                        switch (radioGroup.getCheckedRadioButtonId()) {
                            case R.id.radioButtonUangKas:
                                if (!jumlah.getText().toString().isEmpty() && !isiNama.getText().toString().isEmpty()
                                        && !isiNim.getText().toString().isEmpty()) {
                                    saveData();
                                    jumlah.setText("");
                                    isiNim.setText("");
                                    isiNama.setText("");
                                    radioGroup.clearCheck();
                                    layoutKeterangan.setVisibility(View.GONE);
                                }
                                else
                                    Toast.makeText(getActivity(), "Harap isi terlebih dahulu", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.radioButtonUangOther:
                                if (!jumlah.getText().toString().isEmpty() && !keterangan.getText().toString().isEmpty()) {
                                    saveData();
                                    jumlah.setText("");
                                    keterangan.setText("");
                                    radioGroup.clearCheck();
                                    layoutKeterangan.setVisibility(View.GONE);
                                }
                                else
                                    Toast.makeText(getActivity(), "Harap isi terlebih dahulu", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        return root;
    }

    private void saveData() {
        if(tipe.matches("0")){
            if(code.matches("0")){
                mNim = isiNim.getText().toString();
                mNama = isiNama.getText().toString();
                mJumlah = jumlah.getText().toString();
            }else{
                mKeterangan = keterangan.getText().toString();
                mJumlah = jumlah.getText().toString();
            }
        }else {
            mJumlah = jumlah.getText().toString();
            mKeterangan = keterangan.getText().toString();
        }
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response",response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getString("code").matches("1")) {
                        Toast.makeText(getActivity(), "Berhasil menyimpan", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getActivity(), "Gagal menyimpan",Toast.LENGTH_SHORT).show();
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
                map.put("flag","save_uang");
                map.put("id_ukm",preferences.getString("id_ukm","0"));
                map.put("jumlah",mJumlah);
                if(tipe.matches("0")){
                    map.put("code",code);
                    if(code.matches("1"))
                        map.put("keterangan",mKeterangan);
                    else {
                        map.put("keterangan", "Uang Kas");
                        map.put("nim", mNim);
                        map.put("nama", mNama);

                    }
                }else
                    map.put("keterangan",mKeterangan);
                map.put("role",tipe);

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void getData() {
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    nilaiSaldo = jsonObject.getString("saldo");
                    saldo.setText(nilaiSaldo);
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
                map.put("flag","get_saldo");
                map.put("id_ukm",preferences.getString("id_ukm","0"));
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void init(View root) {
        saldo = root.findViewById(R.id.textViewUangNilaiSaldo);
        nama = root.findViewById(R.id.textViewUangNama);
        nim = root.findViewById(R.id.textViewUangNIM);
        layoutSaldo = root.findViewById(R.id.layoutUangSaldo);
        layoutKeterangan = root.findViewById(R.id.layoutRadioGrup);
        jumlah = root.findViewById(R.id.editTextUangJumlah);
        keterangan = root.findViewById(R.id.editTextUangKeterangan);
        isiNama = root.findViewById(R.id.editTextUangNama);
        isiNim = root.findViewById(R.id.editTextUangNIM);
        simpan = root.findViewById(R.id.buttonUangSimpan);
        spinner = root.findViewById(R.id.spinerr_uang);
        radioGroup = root.findViewById(R.id.radioGroupUang);
        preferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        requestQueue = Volley.newRequestQueue(getActivity());
        arrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_uang, android.R.layout.simple_spinner_item);
    }
}