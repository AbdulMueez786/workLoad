package com.example.workload;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class manager_task_creation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_task_creation);

        Spinner emplist = (Spinner)findViewById(R.id.employeedropdown);

        ArrayList<String> names = new ArrayList<>();

        names = getIntent().getStringArrayListExtra("data");
        if(names == null){
            names = new ArrayList<>();
            names.add("Armughan");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, names);
        emplist.setAdapter(adapter);
    }
}