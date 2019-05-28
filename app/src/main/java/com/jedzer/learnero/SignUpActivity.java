package com.jedzer.learnero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jedzer.misc.Settings;
import com.jedzer.model.Unit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    EditText signupUsernameEditText;
    EditText signupEmailEditText;
    EditText signupPasswordEditText;
    EditText signupPasswordRepeatEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupUsernameEditText = findViewById(R.id.signupUsernameEditText);
        signupEmailEditText = findViewById(R.id.signupEmailEditText);
        signupPasswordEditText = findViewById(R.id.signupPasswordEditText);
        signupPasswordRepeatEditText = findViewById(R.id.signupPasswordRepeatEditText);
    }

    private boolean isSignUp(String username)
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
                            result[0] = (!detail.equals(""));
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

    private void postNewUser(final String username, final String email, final String password)
    {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url ="http://192.168.1.49:8000/api/users/";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("username", username);
                params.put("email", email);
                params.put("profile", null);
                params.put("password", password);

                return params;
            }
        };
        queue.add(postRequest);
    }

    public void signUp(View view) {
        String username = signupUsernameEditText.getText().toString();
        String email = signupEmailEditText.getText().toString();
        String password = signupPasswordEditText.getText().toString();
        String passwordRepeat = signupPasswordRepeatEditText.getText().toString();

        if (!password.equals(passwordRepeat))
        {
            Toast toast = Toast.makeText(this, "Password are different", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        if (isSignUp(username)) {
            Toast toast = Toast.makeText(this, "User already created", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        postNewUser(username, email, password);

        Settings.setLogIn(username);
    }
}
