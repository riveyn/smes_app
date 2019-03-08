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

        final  String[] genderitems = getResources().getStringArray(R.array.gender_spinner);
        final String[] civilitems = getResources().getStringArray(R.array.civil_spinner);
        final  Spinner genderspinner = view.findViewById(R.id.gender_spinner);
        final Spinner civilspinner = view.findViewById(R.id.civilstatus_spinner);
        final String[] genderspinnervalue = {""};
        final String[] civilspinnervalue = {""};


        //start of gender spinner
        final ArrayAdapter<String> genderspinnerArrayAdapter = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_item,genderitems){
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

        genderspinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_profile);
        genderspinner.setAdapter(genderspinnerArrayAdapter);

        genderspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        // gender spinner formatting
        ArrayAdapter<String> gendercoloradapter = new ArrayAdapter<>(this.getContext(), R.layout.spinner_profile, genderitems);
        genderspinner.setAdapter(gendercoloradapter);
        session=getActivity().getSharedPreferences(Global.SESSION,Context.MODE_PRIVATE);
        genderspinner.setAdapter(genderspinnerArrayAdapter);

        //start of civil status spinner
        final ArrayAdapter<String> civilspinnerArrayAdapter = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_item,civilitems){
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

        civilspinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_profile);
        civilspinner.setAdapter(civilspinnerArrayAdapter);

        civilspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String)parent.getItemAtPosition(position);

                //store selected item on spinner
                if (position>0){
                    civilspinnervalue[0] = selectedItemText;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //civil spinner formatting
        ArrayAdapter<String> civilcoloradapter = new ArrayAdapter<>(this.getContext(), R.layout.spinner_profile, civilitems);
        genderspinner.setAdapter(civilcoloradapter);
        session=getActivity().getSharedPreferences(Global.SESSION,Context.MODE_PRIVATE);
        civilspinner.setAdapter(civilspinnerArrayAdapter);


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

