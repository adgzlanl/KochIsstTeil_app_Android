package com.adiguzel.anil.kochisstteil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class General extends AppCompatActivity {
    TextView editAccounName;
    private Session session;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawerLayout);
        mActionBarDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.nav_open,R.string.nav_close);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        session=new Session(this);
        mNavigationView=(NavigationView) findViewById(R.id.navigationView);
        View headerView = mNavigationView.getHeaderView(0);
        SharedPreferences shared = getSharedPreferences("MyPref", 0);
        String SharedPreferencesEmail = (shared.getString("Useremail", ""));
        editAccounName=(TextView) headerView.findViewById(R.id.AccountName);
        editAccounName.setText(SharedPreferencesEmail);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id =item.getItemId();

                if(id==R.id.Mainmenu)
                {
                    Intent i = new Intent(General.this, MainMenu.class);
                    startActivity(i);
                    mDrawerLayout.closeDrawers();
                }

                else if (id==R.id.Submenu)
                {
                    Intent i = new Intent(General.this, SubMenu.class);
                    startActivity(i);
                    mDrawerLayout.closeDrawers();
                }

                else if (id==R.id.Desert)
                {
                    Intent i = new Intent(General.this, Desert.class);
                    startActivity(i);
                    mDrawerLayout.closeDrawers();
                }

                else if (id==R.id.Soup)
                {
                    Intent i = new Intent(General.this, Soup.class);
                    startActivity(i);
                    mDrawerLayout.closeDrawers();
                }

                else if (id==R.id.Salad)
                {
                    Intent i = new Intent(General.this, Salad.class);
                    startActivity(i);
                    mDrawerLayout.closeDrawers();
                }

                else if (id==R.id.Combination)
                {
                    Intent i = new Intent(General.this, Combination.class);
                    startActivity(i);
                    mDrawerLayout.closeDrawers();
                }

                else if (id==R.id.Setting)
                {
                    Intent i = new Intent(General.this, Settings.class);
                    startActivity(i);
                    mDrawerLayout.closeDrawers();
                }

                return false;
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }



    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_logout) {
            logout();
            return true;
        }

        if(mActionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.recycler_contex, menu);
        return true;
    }


    private void logout()
    {
        session.setLoggedin(false);
        Intent intent = new Intent(General.this, Login.class);
        startActivity(intent);
        finish();
    }

}
