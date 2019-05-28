package com.jedzer.learnero;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jedzer.misc.Crypto;
import com.jedzer.misc.Settings;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText loginNicknameEditText;
    EditText loginPasswordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginNicknameEditText = findViewById(R.id.loginNicknameEditText);
        loginPasswordEditText = findViewById(R.id.loginPasswordEditText);
    }

    public void signUp(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }




    private boolean isSignUp(String username, final String password)
    {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.1.49:8000/api/users/" + username + "/";
        final boolean[] result = {false};

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String detail = jsonObject.getString("detail");
                            String serverPassword = jsonObject.getString("password");
                            result[0] = (!detail.equals("") && password.equals(serverPassword));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) { }
                });
        queue.add(stringRequest);
        return result[0];
    }

    public void logIn(View view) {
        String nickname = loginNicknameEditText.getText().toString();
        String password = loginPasswordEditText.getText().toString();
        String hexPassword = Crypto.bin2hex(password.getBytes());

        if (isSignUp(nickname, hexPassword))
            Settings.setLogIn(nickname);
    }
}
