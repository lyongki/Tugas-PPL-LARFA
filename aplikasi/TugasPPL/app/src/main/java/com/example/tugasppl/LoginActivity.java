package com.example.tugasppl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText username,password;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private  String url = "http://192.168.1.5/ServiceTugasPPL.php";
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.editTextUsername);
        password = (EditText) findViewById(R.id.editTextPassword);
        Button login = (Button) findViewById(R.id.buttonLogin);
        requestQueue = Volley.newRequestQueue(this);
        preferences = getSharedPreferences("pref",MODE_PRIVATE);
        if(preferences.getString("id_pengurus","0").matches("0") == false && preferences.getString("id_ukm","0").matches("0") == false){
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().matches("")==false && password.getText().toString().matches("")==false){
                    stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if(jsonObject.getString("code").matches("1")){
                                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                    preferences.edit().putString("id_pengurus",jsonObject.getString("id")).apply();
                                    preferences.edit().putString("id_ukm",jsonObject.getString("id_ukm")).apply();
                                    preferences.edit().putString("nama",jsonObject.getString("nama")).apply();
                                    preferences.edit().putString("role",jsonObject.getString("role")).apply();
                                    Log.d("id_ukm login",preferences.getString("id_ukm","-1"));
                                    startActivity(intent);
                                }else
                                    Toast.makeText(LoginActivity.this, "Anda bukan pengurus",Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this, "Gagal terhubung ke server",Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> map = new HashMap<>();
                            map.put("flag","login_pengurus");
                            map.put("username",username.getText().toString());
                            map.put("password",password.getText().toString());
                            return map;
                        }
                    };
                    requestQueue.add(stringRequest);
                }else{
                    Toast.makeText(LoginActivity.this, "Masukkan username dan password terlebih dahulu",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onStop() {
        finish();
        super.onStop();
    }
}
