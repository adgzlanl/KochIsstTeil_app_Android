package com.adiguzel.anil.kochisstteil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInstaller;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    Session session;
    EditText editEmailAddress;
    EditText editPassword;
    TextView editRegister;
       private static final String LoginUrl="https://kochisstteil.herokuapp.com/login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        editEmailAddress=(EditText)findViewById(R.id.editText1);
        editPassword=(EditText)findViewById(R.id.editText2);
        editRegister=(TextView)findViewById(R.id.textView1);
        session=new Session(this);



        if (session.loggedin())
        {
            Intent intent = new Intent(Login.this, General.class);
            startActivity(intent);
            finish();
        }
    }

    public void login(View v)
    {
        loginRequest();


    }


    public void register(View v)
    {
        Intent intent = new Intent(this, register.class);
        startActivity(intent);

    }

    private void loginRequest()

    {
        String email=editEmailAddress.getText().toString();
        String password=editPassword.getText().toString();
        RequestQueue queue = Volley.newRequestQueue(this);

        // POST parameters
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        params.put("password",password);
        JSONObject jsonObj = new JSONObject(params);

// Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, LoginUrl, jsonObj, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {


                        try {


                            String message = response.getString("success");



                            if (message=="false")
                            {
                                Toast.makeText(getApplicationContext(), "Kullanici Kaydi Bulunamadi", Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(), "Register Sayfasina Yönlendiriliyorsunuz", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Login.this, register.class);
                                startActivity(intent);
                                finish();

                            }
                            else
                            {
                                session.setLoggedin(true);
                                String email = response.getString("email");
                                String password=response.getString("password");
                                String id = response.getString("ID");

                                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("Useremail", email);
                                editor.putString("Userpassword",password);
                                editor.putString("Userid", id);
                                editor.commit();
                                Intent intent = new Intent(Login.this, General.class);
                                startActivity(intent);
                                finish();

                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {


                                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);


    }
}
