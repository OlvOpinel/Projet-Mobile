package com.example.powerhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.material.textfield.TextInputEditText;


public class LoginActivity extends AppCompatActivity {


    private TextView errorConnectAccountTextView;
    TextInputEditText textInputEditTextName, textInputEditTextPassword;
    private Button connectBtn;

    private String Name;
    private String Password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        errorConnectAccountTextView = findViewById(R.id.errorConnectAccountTextView);
        textInputEditTextName = findViewById(R.id.etName);
        textInputEditTextPassword = findViewById(R.id.etPassword);
        connectBtn = findViewById(R.id.connectBtn);

        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name = textInputEditTextName.getText().toString();
                Password = textInputEditTextPassword.getText().toString();

                //LANCEER LA REQUÊTE POUR CONNECTER LE USER
            }
        });
    }


    public void connectuser() {
        //10.0.2.2 LOCALHOST DANS GOOGLE
        String URL = "http://localhost:8888/powerhome/";
    }


    public void register (View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

}