package id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Koneksi.VolleySingleton;
import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Model.UserModel;
import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Session.SessionPreference;

public class LoginActivity extends AppCompatActivity {
    private final String url = "http://10.1.5.24:8080/users";
    EditText etUsername, etPassword;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (SessionPreference.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnSubmit);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

    }

    private void userLogin() {
        final String username = etUsername.getText().toString();
        final String password = etPassword.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        String user = obj.getString("UserName");
                        String pass = obj.getString("Password");
                        Log.d("data", "onResponse: " + user + pass);
                        if (username.equals(user) && password.equals(pass)) {
                            UserModel userModel = new UserModel(
                                    obj.getInt("IDUser"),
                                    obj.getString("Name"),
                                    obj.getString("UserName"),
                                    obj.getString("Password"),
                                    obj.getString("Branch")
                            );
                            SessionPreference.getInstance(getApplicationContext()).userLogin(userModel);
                            Toast.makeText(getApplicationContext(), "Berhasil login", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
