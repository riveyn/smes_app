package com.example.marcotawa.myapplication;

import com.android.volley.RequestQueue;

public class Global {
    public static String DOMAIN="192.168.43.20";
    public static String URL="http://"+Global.DOMAIN+"/smes-perigrinus/assets/php/android.php";
    public static String TOKEN="160413763d3bdd6f8a451d5d0c62b668559f9c828a0a29609f5e3e495ecfdc70c01f8175c4ff601f95725434db06c077cb812bd112f61c63b35ceec355cf27ba";
    public static String SESSION="SESSION";
    public static String TAG = "APP_DEBUG_TAG";
    public static RequestQueue requestQueue;
}
