package com.example.powerhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class RegisterActivity extends AppCompatActivity {

    private TextView errorCreateAccountTextView;
    TextInputEditText textInputEditTextName, textInputEditTextEmail, textInputEditTextPassword;
    private Button subBtn;

    private String username;
    private String email;
    private String password;

    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textInputEditTextName = findViewById(R.id.Username);
        textInputEditTextEmail = findViewById(R.id.Email);
        textInputEditTextPassword = findViewById(R.id.Password);
        errorCreateAccountTextView = findViewById(R.id.errorCreateAccountTextView);
        subBtn = findViewById(R.id.subBtn);

        database = new Database(getApplicationContext());

        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = textInputEditTextName.getText().toString();
                email = textInputEditTextEmail.getText().toString();
                password = textInputEditTextPassword.getText().toString();

                createAccount();
                //LANCER LA REQUÊTE POUR INSCRIRE LE USER
            }
        });
    }

    public void onApiResponse(JSONObject response) {
        Boolean success = null;
        String error = "";

        try {
            success = response.getBoolean("success");

            if (success == true) {
                Intent menuactivity = new Intent(getApplicationContext(), MainActivity.class);
                menuactivity.putExtra("username", username);
                startActivity(menuactivity);
                finish();
            } else {
                error = response.getString("error");
                errorCreateAccountTextView.setVisibility(View.VISIBLE);
                errorCreateAccountTextView.setText(error);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }


    public void createAccount () {
        //10.0.2.2 LOCALHOST DANS GOOGLE
        String url = "http://10.0.2.2/powerhome3/actions/createAccount.php";

        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("email", email);
        params.put("password", password);

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                onApiResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_LONG).show();

            }
        });

        database.queue.add(jsonObjectRequest);
    }


    public void login (View view) {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }

}