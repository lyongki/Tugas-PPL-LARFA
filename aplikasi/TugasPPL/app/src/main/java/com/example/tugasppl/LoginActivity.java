package com.example.tugasppl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = (EditText) findViewById(R.id.editTextUsername);
        final EditText password = (EditText) findViewById(R.id.editTextPassword);
        Button login = (Button) findViewById(R.id.buttonLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().matches("")==false && password.getText().toString().matches("")==false){
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }else
                    Toast.makeText(LoginActivity.this, "Silahkan masukan username dan password",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
