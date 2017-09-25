package com.adiguzel.anil.kochisstteil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SaladAdd extends AppCompatActivity {
    EditText editMenuName;
    EditText editRecipe;
    EditText editDuration;
    private static final String LoginUrl="https://kochisstteil.herokuapp.com/saladAdd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salad_add);
        editMenuName=(EditText)findViewById(R.id.editText3);
        editRecipe=(EditText)findViewById(R.id.editText5);
        editDuration=(EditText)findViewById(R.id.editText6);
    }

    public void MainMenuAdd(View v)
    {
        addMainMenu();

    }



    private void addMainMenu()

    {
        String email="selo";
        String password="123";
        String addSalad=editMenuName.getText().toString();
        String addRecipe=editRecipe.getText().toString();
        String addDuration=editDuration.getText().toString();
        RequestQueue queue = Volley.newRequestQueue(this);

        // POST parameters
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        params.put("password",password);
        params.put("salad", addSalad);
        params.put("recipe",addRecipe);
        params.put("duration",addDuration);
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



                            if (message=="true")
                            {
                                Toast.makeText(getApplicationContext(), "Ekleme Saglandi", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SaladAdd.this, Salad.class);
                                startActivity(intent);
                                finish();

                            }
                            else
                            {

                                Toast.makeText(getApplicationContext(), "Ekleme Basarisiz", Toast.LENGTH_LONG).show();
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
