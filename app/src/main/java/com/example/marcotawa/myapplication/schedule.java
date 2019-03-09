package com.example.marcotawa.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class schedule extends Fragment {

    private HorizontalListView horizontalListView;
    private HorizontalAdapter customeAdapter;
    private ArrayList<Model> imageModelArrayList;
    private int[] myImageList = new int[]{R.drawable.ic_smile, R.drawable.ic_done_white_48dp,
            R.drawable.grades_cellsminor,R.drawable.ic_launcher_background
            ,R.drawable.logo_notitle_background,R.drawable.shape_default,
            R.drawable.logo_notitle_background,R.drawable.ic_done_white_48dp};
    private String[] myImageNameList = new String[]{"Benz", "Bike",
            "Car","Carrera"
            ,"Ferrari","Harly",
            "Lamborghini","Silver"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grades_lay, container, false);

        horizontalListView = (HorizontalListView) findViewById(R.id.HorizontalListView);

        imageModelArrayList = populateData();
        Log.d("hjhjh",imageModelArrayList.size()+"");
        customeAdapter = new HorizontalAdapter(this,imageModelArrayList);
        horizontalListView.setAdapter(customeAdapter);

 return view;
    }
}
