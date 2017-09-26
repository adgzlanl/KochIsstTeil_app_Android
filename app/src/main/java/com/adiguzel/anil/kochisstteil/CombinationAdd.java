package com.adiguzel.anil.kochisstteil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CombinationAdd extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText EditMenuName;
    private String stringMainMenu,stringSubMenu,stringDesert,stringSalad,stringSoup,stringTotalDuration;
    private static final String COMBINATIONMENU_URL_DATA="https://kochisstteil.herokuapp.com/combinationAdd";
    //Added Menu Tools*********************
    Spinner SpinnerMainMenuadd;
    private JSONArray MainMenuArray;
    private ArrayList<String> MainMenuList;
    private static final String MAINMENU_URL_DATA="https://kochisstteil.herokuapp.com/mainMenuRead";

    //Added SubMenu Tools*********************
    Spinner SpinnerSubMenuadd;
    private JSONArray SubMenuArray;
    private ArrayList<String> SubMenuList;
    private static final String SUBMENU_URL_DATA="https://kochisstteil.herokuapp.com/subMenuRead";

    //Added Desert Tools*********************
    Spinner SpinnerDesertadd;
    private JSONArray DesertArray;
    private ArrayList<String> DesertList;
    private static final String DESERTMENU_URL_DATA="https://kochisstteil.herokuapp.com/desertRead";

    //Added Salad Tools*********************
    Spinner SpinnerSaladadd;
    private JSONArray SaladArray;
    private ArrayList<String> SaladList;
    private static final String SALADMENU_URL_DATA="https://kochisstteil.herokuapp.com/saladRead";

    //Added Menu Tools*********************
    Spinner SpinnerSoupadd;
    private JSONArray SoupArray;
    private ArrayList<String> SoupList;
    private static final String SOUPMENU_URL_DATA="https://kochisstteil.herokuapp.com/soupRead";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combination_add);
        EditMenuName=(EditText) findViewById(R.id.MenuNameAdd);
        SpinnerMainMenuadd=(Spinner)findViewById(R.id.MainMenuAddSpinner);
        SpinnerSubMenuadd=(Spinner)findViewById(R.id.SubMenuAddSpinner);
        SpinnerDesertadd=(Spinner)findViewById(R.id.DesertAddSpinner);
        SpinnerSaladadd=(Spinner)findViewById(R.id.SaladAddSpinner);
        SpinnerSoupadd=(Spinner)findViewById(R.id.SoupAddSpinner);

        SharedPreferences shared = getSharedPreferences("MyPref", 0);
        String SharedPreferencesEmail = (shared.getString("Useremail", ""));
        String SharedPreferencesPassword = (shared.getString("Userpassword", ""));
        String SharedPreferencesId = (shared.getString("Userid", ""));
        MainMenuList=new ArrayList<String>();
        SubMenuList=new ArrayList<String>();
        DesertList=new ArrayList<String>();
        SaladList=new ArrayList<String>();
        SoupList=new ArrayList<String>();
        MainMenuaddData(SharedPreferencesEmail,SharedPreferencesPassword);
        SubMenuaddData(SharedPreferencesEmail,SharedPreferencesPassword);
        DesertaddData(SharedPreferencesEmail,SharedPreferencesPassword);
        SaladaddData(SharedPreferencesEmail,SharedPreferencesPassword);
        SoupaddData(SharedPreferencesEmail,SharedPreferencesPassword);



        SpinnerMainMenuadd.setOnItemSelectedListener(this);
        SpinnerSubMenuadd.setOnItemSelectedListener(this);
        SpinnerDesertadd.setOnItemSelectedListener(this);
        SpinnerSaladadd.setOnItemSelectedListener(this);
        SpinnerSoupadd.setOnItemSelectedListener(this);



    }


    public void addCombination(View v)
    {
        addCombinationMenu();
    }





    private void MainMenuaddData(final String email, final String password) {
        StringRequest request = new StringRequest(Request.Method.POST, MAINMENU_URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                 if (response.length()>0){

                    JSONObject j=null;

                    try {

                        j=new JSONObject(response);

                        MainMenuArray=j.getJSONArray("mainMenu");
                        getMainMenuData(MainMenuArray);

                    } catch (JSONException e) {

                        e.printStackTrace();
                    }


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(request);

    }


    private void getMainMenuData(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                MainMenuList.add(json.getString("mainMenu"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        SpinnerMainMenuadd.setAdapter(new ArrayAdapter<String>(CombinationAdd.this, android.R.layout.simple_spinner_dropdown_item, MainMenuList));
    }

    private void SubMenuaddData(final String email, final String password) {
        StringRequest request = new StringRequest(Request.Method.POST, SUBMENU_URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.length()>0){

                    JSONObject j=null;

                    try {

                        j=new JSONObject(response);

                        SubMenuArray=j.getJSONArray("subMenu");
                        getSubMenuData(SubMenuArray);

                    } catch (JSONException e) {

                        e.printStackTrace();
                    }


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(request);

    }


    private void getSubMenuData(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                SubMenuList.add(json.getString("subMenu"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        SpinnerSubMenuadd.setAdapter(new ArrayAdapter<String>(CombinationAdd.this, android.R.layout.simple_spinner_dropdown_item, SubMenuList));
    }


    private void DesertaddData(final String email, final String password) {
        StringRequest request = new StringRequest(Request.Method.POST, DESERTMENU_URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.length()>0){

                    JSONObject j=null;

                    try {

                        j=new JSONObject(response);

                        DesertArray=j.getJSONArray("desert");
                        getDesertMenu(DesertArray);

                    } catch (JSONException e) {

                        e.printStackTrace();
                    }


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(request);

    }


    private void getDesertMenu(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                DesertList.add(json.getString("desert"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        SpinnerDesertadd.setAdapter(new ArrayAdapter<String>(CombinationAdd.this, android.R.layout.simple_spinner_dropdown_item, DesertList));
    }

    private void SaladaddData(final String email, final String password) {
        StringRequest request = new StringRequest(Request.Method.POST, SALADMENU_URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.length()>0){

                    JSONObject j=null;

                    try {

                        j=new JSONObject(response);

                        SaladArray=j.getJSONArray("salad");
                        getSaladMenu(SaladArray);

                    } catch (JSONException e) {

                        e.printStackTrace();
                    }


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(request);

    }


    private void getSaladMenu(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                SaladList.add(json.getString("salad"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        SpinnerSaladadd.setAdapter(new ArrayAdapter<String>(CombinationAdd.this, android.R.layout.simple_spinner_dropdown_item, SaladList));
    }

    private void SoupaddData(final String email, final String password) {
        StringRequest request = new StringRequest(Request.Method.POST, SOUPMENU_URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.length()>0){

                    JSONObject j=null;

                    try {

                        j=new JSONObject(response);

                        SoupArray=j.getJSONArray("soup");
                        GetSoupMenu(SoupArray);

                    } catch (JSONException e) {

                        e.printStackTrace();
                    }


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(request);

    }


    private void GetSoupMenu(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                SoupList.add(json.getString("soup"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        SpinnerSoupadd.setAdapter(new ArrayAdapter<String>(CombinationAdd.this, android.R.layout.simple_spinner_dropdown_item, SoupList));
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


        switch (adapterView.getId())
        {
            case R.id.MainMenuAddSpinner:

                stringMainMenu=SpinnerMainMenuadd.getSelectedItem().toString();
                //Toast.makeText(getApplicationContext(), stringMainMenu, Toast.LENGTH_LONG).show();


                break;

            case R.id.SubMenuAddSpinner:

                stringSubMenu=SpinnerSubMenuadd.getSelectedItem().toString();
               // Toast.makeText(getApplicationContext(), stringSubMenu, Toast.LENGTH_LONG).show();

            break;

            case R.id.DesertAddSpinner:
                stringDesert=SpinnerDesertadd.getSelectedItem().toString();
                //Toast.makeText(getApplicationContext(), stringDesert, Toast.LENGTH_LONG).show();

                break;

            case R.id.SaladAddSpinner:

                stringSalad=SpinnerSaladadd.getSelectedItem().toString();
               // Toast.makeText(getApplicationContext(), stringSalad, Toast.LENGTH_LONG).show();

                break;

            case R.id.SoupAddSpinner:

                stringSoup=SpinnerSoupadd.getSelectedItem().toString();
               // Toast.makeText(getApplicationContext(), stringSoup, Toast.LENGTH_LONG).show();

                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }




    private void addCombinationMenu()

    {
        String email="selo";
        String password="123";

        RequestQueue queue = Volley.newRequestQueue(this);

        // POST parameters
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        params.put("password",password);
        params.put("menuName", EditMenuName.getText().toString());
        params.put("mainMenu", stringMainMenu);
        params.put("subMenu",stringSubMenu);
        params.put("desert",stringDesert);
        params.put("soup", stringSoup);
        params.put("salad",stringSalad);
        params.put("duration","45");
        JSONObject jsonObj = new JSONObject(params);

// Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, COMBINATIONMENU_URL_DATA, jsonObj, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {


                        try {


                            String message = response.getString("success");



                            if (message=="true")
                            {
                                Toast.makeText(getApplicationContext(), "Ekleme Saglandi", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(CombinationAdd.this, Combination.class);
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
