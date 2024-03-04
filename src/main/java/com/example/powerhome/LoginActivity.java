package com.example.powerhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {


    private TextView errorConnectAccountTextView;
    TextInputEditText textInputEditTextName, textInputEditTextPassword;
    private Button connectBtn;

    private String username;
    private String password;


    private Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        errorConnectAccountTextView = findViewById(R.id.errorConnectAccountTextView);
        textInputEditTextName = findViewById(R.id.username);
        textInputEditTextPassword = findViewById(R.id.password);
        connectBtn = findViewById(R.id.connectBtn);

        database = new Database(getApplicationContext());

        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = textInputEditTextName.getText().toString();
                password = textInputEditTextPassword.getText().toString();

                connectuser();
                //LANCEER LA REQUÊTE POUR CONNECTER LE USER
            }
        });
    }


    public void onApiResponse(JSONObject response) {
        Boolean success = null;
        String error = "";

        try {
            success = response.getBoolean("success");

            if (success == true) {
                Intent menuactivity = new Intent(getApplicationContext(), MenuActivity.class);
                menuactivity.putExtra("username", username);
                startActivity(menuactivity);
                finish();
            } else {
                error = response.getString("error");
                errorConnectAccountTextView.setVisibility(View.VISIBLE);
                errorConnectAccountTextView.setText(error);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }


    public void connectuser() {
        //10.0.2.2 LOCALHOST DANS GOOGLE
        String url = "http://10.0.2.2/powerhome3/actions/connectUser.php";

        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                onApiResponse(response);
                Toast.makeText(getApplicationContext(), "OPERATION SUCCESSFUL", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });

        database.queue.add(jsonObjectRequest);

    }


    public void register (View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

}