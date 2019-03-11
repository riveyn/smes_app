package com.example.marcotawa.myapplication;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Announcement extends Fragment {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    View view;
    //public RequestQueue requestQueue;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //ListView announcements=(ListView)view.findViewById(R.id.event_list);
        view=inflater.inflate(R.layout.announcement_lay,container,false);
        //createAnnouncement();

        // get the listview
        expListView = (ExpandableListView) view.findViewById(R.id.event_list);

               // preparing list data
        prepareListData();
/*
        listAdapter = new ExpandableListAdapter( this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
*/
        return view;
   }

    private void prepareListData() {
        final String query = "SELECT announcements.*,person.fname,person.mname,person.lname FROM announcements RIGHT JOIN person on person.person_id=announcements.person_id where recipient_category = 1";
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        StringRequest request=new StringRequest(Request.Method.POST, Global.URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(Global.TAG, "HTTPResponse: "+response);

                JSONArray jsonArray;
                try{
                    jsonArray=new JSONArray(response);
                    //Toast.makeText(getActivity(),response+"Length=",Toast.LENGTH_LONG).show();
                    //List<Map<String, String>> data = new ArrayList<Map<String, String>>();
                    HashMap<String,List<String>> announcementDetails= new HashMap<String,List<String>>();
                    for(int ctr=0; ctr<jsonArray.length();ctr++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(ctr);
                        Log.d(Global.TAG, "JSONObject: "+jsonObject.toString());
                        //AnnouncementTitle.add(jsonObject.get("title").toString());
                        listDataHeader.add(jsonObject.get("title").toString());
                        List<String> stringList=new ArrayList<String>();

                        stringList.add(jsonObject.get("content").toString());

                        List<String> announcementList = new ArrayList<>();
                        announcementList.add(jsonObject.get("date_start").toString().substring(0,10));
                        announcementList.add(jsonObject.get("date_end").toString().substring(0,10));
                        announcementList.add(jsonObject.get("lname").toString()+", "+jsonObject.get("fname").toString()+" "+jsonObject.get("mname").toString());
                        announcementDetails.put(listDataHeader.get(ctr), announcementList);
                        //AnnouncementContent.put(AnnouncementTitle.get(ctr), stringList);
                        listDataChild.put(listDataHeader.get(ctr), stringList);
                    }
                    listAdapter = new ExpandableListAdapter( Announcement.this, listDataHeader, listDataChild, announcementDetails);
                    // setting list adapter
                    expListView.setAdapter(listAdapter);
                }
                catch(Exception e){
                    Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_LONG).show();
                    Log.d(Global.TAG, "Volley Exception thrown: ", e);
                }

            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();
                Log.d(Global.TAG, "Some Error Occured.", error);
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String,String>();
                params.put("query",query);
                params.put("token",Global.TOKEN);
                return params;
            }
        };
        Global.requestQueue.add(request);
        /*
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
        */
    }
/*

    public void createAnnouncement(){
        final String query="SELECT * FROM `student_announcements`";
        final ExpandableListView announcements=view.findViewById(R.id.event_list);
        final List<String> AnnouncementTitle=new ArrayList<String>();
        final HashMap<String,List<String>> AnnouncementContent=new HashMap<String,List<String>>();

        StringRequest request=new StringRequest(Request.Method.POST, Global.URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray;
                try{
                    jsonArray=new JSONArray(response);
                    //Toast.makeText(getActivity(),response+"Length=",Toast.LENGTH_LONG).show();
                    List<Map<String, String>> data = new ArrayList<Map<String, String>>();
                    for(int ctr=0; ctr<jsonArray.length();ctr++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(ctr);
                        AnnouncementTitle.add(jsonObject.get("title").toString());
                        List<String> stringList=new ArrayList<String>();
                        stringList.add(jsonObject.get("content").toString());
                        AnnouncementContent.put(AnnouncementTitle.get(ctr), stringList);
                    }
                    com.example.marcotawa.myapplication.ExpandableListAdapter adapter=new com.example.marcotawa.myapplication.ExpandableListAdapter(getActivity(),AnnouncementTitle,AnnouncementContent);
                    announcements.setAdapter(adapter);

                }
                catch(Exception e){
                    Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_LONG).show();
                }

            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String,String>();
                params.put("query",query);
                params.put("token",Global.TOKEN);
                return params;
            }
        };

        requestQueue.add(request);
    } */
}
