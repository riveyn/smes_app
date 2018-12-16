package com.example.marcotawa.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import br.com.simplepass.loading_button_lib.interfaces.OnAnimationEndListener;

public class login extends AppCompatActivity {

    CircularProgressButton circularProgressButton;
    RequestQueue requestQueue;
    public SharedPreferences session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        requestQueue= Volley.newRequestQueue(this);
        session=getSharedPreferences(Global.SESSION, Context.MODE_PRIVATE);
        circularProgressButton = findViewById(R.id.btn_Login);

        circularProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularProgressButton.startAnimation();
                StringRequest request=new StringRequest(Request.Method.POST, Global.URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(login.this,response,Toast.LENGTH_LONG).show();
                        try{
                            JSONArray jsonArray=new JSONArray(response);
                            if(jsonArray.length()==1){
                                JSONObject jsonObject=jsonArray.getJSONObject(0);
                                SharedPreferences.Editor editor=session.edit();
                                editor.putString("username",jsonObject.getString("username"));
                                editor.putString("password",jsonObject.getString("pswrd"));
                                editor.putString("fname",jsonObject.getString("fname"));
                                editor.putString("mname",jsonObject.getString("mname"));
                                editor.putString("lname",jsonObject.getString("lname"));
                                editor.putString("birthdate",jsonObject.getString("birthdate"));
                                editor.putString("sex",jsonObject.getString("sex"));
                                editor.commit();
                                finish();
                                Intent studentIntent = new Intent(login.this, StudentActivity.class);
                                login.this.startActivity(studentIntent);
                            }
                            else{
                                Toast.makeText(login.this,"Wrong Username or Password",Toast.LENGTH_LONG).show();
                                circularProgressButton.revertAnimation();
                            }
                        }
                        catch(Exception e){
                            Toast.makeText(login.this,e.toString(),Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(login.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams(){
                        Map<String,String> params=new HashMap<String,String>();
                        params.put("query","SELECT * FROM users as u " +
                                           "INNER JOIN person as p ON p.person_id=u.person_id WHERE " +
                                           "username=? AND pswrd=?;");
                        params.put("param1",((EditText)findViewById(R.id.email_field)).getText().toString());
                        params.put("param2",((EditText)findViewById(R.id.password_field)).getText().toString());
                        params.put("query_types","ss");
                        params.put("token",Global.TOKEN);
                        return params;
                    }

                };
                requestQueue.add(request);
            }
        });


    }
}
