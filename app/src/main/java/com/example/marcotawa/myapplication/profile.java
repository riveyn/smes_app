package com.example.marcotawa.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class profile extends Fragment {

    private SharedPreferences session;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_lay, container, false);

        final  String[] items = getResources().getStringArray(R.array.gender_spinner);
        final  Spinner spinner = view.findViewById(R.id.gender_spinner);
        final String[] genderspinnervalue = {""};

        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_item,items){
            @Override
            public boolean isEnabled(int position) {
                if(position == 0 ){
                    return false;
                }else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position,View convertView,ViewGroup parent) {
                View view1 = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView)view1;
                if (position == 0 ){
                    //color of default
                    tv.setTextColor(Color.GRAY);
                }else {
                    //color of items
                    tv.setTextColor(Color.BLACK);
                }
                return view1;
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_profile);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String)parent.getItemAtPosition(position);

                //store selected item on spinner
                if (position>0){
                    genderspinnervalue[0] = selectedItemText;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //spinner formatting
        ArrayAdapter<String> coloradapter = new ArrayAdapter<String>(this.getContext(), R.layout.spinner_profile,items);
        spinner.setAdapter(coloradapter);
        session=getActivity().getSharedPreferences(Global.SESSION,Context.MODE_PRIVATE);
        spinner.setAdapter(spinnerArrayAdapter);
        EditText fname=view.findViewById(R.id.fname_txt);
        EditText mname=view.findViewById(R.id.mname_txt);
        EditText lname=view.findViewById(R.id.lname_txt);
        EditText bdate=view.findViewById(R.id.dbirth_txt);
        fname.setText(session.getString("fname",""));
        mname.setText(session.getString("mname",""));
        lname.setText(session.getString("lname",""));
        bdate.setText(session.getString("birthdate",""));



        return view;


    }

}

