package com.adiguzel.anil.kochisstteil;

import android.app.ProgressDialog;
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

public class Combination extends AppCompatActivity {


    private static final String URL_DATA="https://kochisstteil.herokuapp.com/combinationRead";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<CombinationList> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combination);



        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems=new ArrayList<>();
        loadRecyclerViewData();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void loadRecyclerViewData()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading data.....");
        progressDialog.show();

        String email="selo";
        String password="123";

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


                            JSONArray array=response.getJSONArray("combination");

                            for (int i=0;i<array.length();i++)
                            {
                                JSONObject o =array.getJSONObject(i);

                                CombinationList item=new CombinationList(o.getString("menuName"),o.getString("mainMenu"),o.getString("subMenu"),o.getString("desert"),o.getString("soup"),o.getString("salad"),o.getString("totalDuration"),o.getString("_id"));
                                listItems.add(item);
                            }

                            adapter=new CombinationAdapter(listItems,getApplicationContext());
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
