package com.example.marcotawa.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class StudentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private SharedPreferences session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Global.requestQueue=Volley.newRequestQueue(this);
        session=getSharedPreferences(Global.SESSION,MODE_PRIVATE);
        setContentView(R.layout.student_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.student_drawer);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Announcement()).commit();
            navigationView.setCheckedItem(R.id.nav_announcement);
        }
        TextView studentName=(TextView) navigationView.getHeaderView(0).findViewById(R.id.stud_name);
        String name=session.getString("fname","")+" "+
                session.getString("mname","")+" "+
                session.getString("lname","");
        studentName.setText(name);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_announcement:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Announcement()).commit();
                //createAnnouncement();
                break;
            case R.id.nav_grades:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new grades()).commit();
                break;
            case R.id.nav_schedule:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new schedule()).commit();
                break;
            case R.id.nav_record:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new record()).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new profile()).commit();
                break;
            case R.id.nav_library:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new library()).commit();
                break;
            case R.id.nav_registration:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new registration()).commit();
                break;
            case R.id.nav_settings:
                Toast.makeText(this, "Settings not found", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logged Out", Toast.LENGTH_LONG).show();
                //open login page
                finish();
                Intent studentInent = new Intent(StudentActivity.this, login.class);
                StudentActivity.this.startActivity(studentInent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}
