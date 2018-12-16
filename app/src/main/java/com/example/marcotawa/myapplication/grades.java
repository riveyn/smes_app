package com.example.marcotawa.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class grades extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grades_lay, container, false);

        //list of semester default item selected is position 0
        ArrayList<String> semester = new ArrayList<>();
        Spinner semSpinner = view.findViewById(R.id.sem_spinner);

        //add sem list
        semester.add("1st semester S.Y. 20xx - 20xx");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), R.layout.spinner_grades, semester);
        semSpinner.setAdapter(adapter);

        return view;
    }
}
