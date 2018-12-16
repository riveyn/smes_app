package com.example.marcotawa.myapplication;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    View view;
    RequestQueue requestQueue;
    //public RequestQueue requestQueue;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //ListView announcements=(ListView)view.findViewById(R.id.event_list);
        view=inflater.inflate(R.layout.announcement_lay,container,false);
        requestQueue=Volley.newRequestQueue(getActivity());
        createAnnouncement();
        return view;
    }
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
                    Toast.makeText(getActivity(),response+"Length=",Toast.LENGTH_LONG).show();
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
    }
}
