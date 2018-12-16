package com.example.marcotawa.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import br.com.simplepass.loading_button_lib.interfaces.OnAnimationEndListener;

public class login extends AppCompatActivity {

    CircularProgressButton circularProgressButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        circularProgressButton = findViewById(R.id.btn_Login);
        circularProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask<String,String,String> loginBTN = new AsyncTask<String, String, String>() {
                    @Override
                    protected String doInBackground(String... strings) {
                       //background task for login
                        try {
                          Thread.sleep(3000);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                        return "done";
                    }

                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    protected void onPostExecute(String s) {
                        if (s.equals("done")){
                            Toast.makeText(login.this, "Succesfully Logged In", Toast.LENGTH_SHORT).show();
                            circularProgressButton.doneLoadingAnimation(Color.parseColor("#333639"), BitmapFactory.decodeResource(getResources(),R.drawable.ic_done_white_48dp));
                            finish();

                            //choose student or faculty
                            Intent studentInent = new Intent(login.this, StudentActivity.class);
                            login.this.startActivity(studentInent);
                        }
                        else if (s.equals("failed")){
                            Toast.makeText(login.this, "Invalid Password or Email", Toast.LENGTH_SHORT).show();
                            circularProgressButton.revertAnimation();

                        }
                    }
                };
            circularProgressButton.startAnimation();
            loginBTN.execute();
            }
        });


    }
}
