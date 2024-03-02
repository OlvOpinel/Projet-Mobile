package com.example.powerhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private TextView errorCreateAccountTextView;
    TextInputEditText textInputEditTextName, textInputEditTextEmail, textInputEditTextPassword;
    private Button subBtn;

    private String Name;
    private String Email;
    private String Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textInputEditTextName = findViewById(R.id.etName);
        textInputEditTextEmail = findViewById(R.id.etMail);
        textInputEditTextPassword = findViewById(R.id.etPassword);
        subBtn = findViewById(R.id.subBtn);

        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name = textInputEditTextName.getText().toString();
                Email = textInputEditTextEmail.getText().toString();
                Password = textInputEditTextPassword.getText().toString();

                //LANCER LA REQUÊTE POUR INSCRIRE LE USER
            }
        });

    }

    public void login (View view) {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }

}