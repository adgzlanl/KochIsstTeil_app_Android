package com.adiguzel.anil.kochisstteil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubMenu extends AppCompatActivity {
    private static final String URL_DATA="https://kochisstteil.herokuapp.com/subMenuRead";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<MenuList> listItems;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);
        recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems=new ArrayList<>();
        loadRecyclerViewData();
        session=new Session(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.recycler_contex, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(SubMenu.this, SubMenuAdd.class);
            startActivity(intent);
            finish();
            return true;
        }
        if (id == R.id.action_logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout()
    {
        session.setLoggedin(false);
        Intent intent = new Intent(SubMenu.this, Login.class);
        startActivity(intent);
        finish();
    }
    private void loadRecyclerViewData()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading data.....");
        progressDialog.show();
        SharedPreferences shared = getSharedPreferences("MyPref", 0);
        String SharedPreferencesEmail = (shared.getString("Useremail", ""));
        String SharedPreferencesPassword = (shared.getString("Userpassword", ""));
        String SharedPreferencesId = (shared.getString("Userid", ""));
        String email=SharedPreferencesEmail;
        String password=SharedPreferencesPassword;

        // POST parameters
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        params.put("password",password);
        JSONObject jsonObj = new JSONObject(params);


        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, URL_DATA, jsonObj, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        progressDialog.dismiss();

                try {


                    JSONArray array=response.getJSONArray("subMenu");

                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject o =array.getJSONObject(i);

                        MenuList item=new MenuList(o.getString("subMenu"),o.getString("recipe"),o.getString("_id"),o.getString("duration"));
                        listItems.add(item);
                    }

                    adapter=new SubMenuAdapter(listItems,getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonObjRequest);
    }


}



