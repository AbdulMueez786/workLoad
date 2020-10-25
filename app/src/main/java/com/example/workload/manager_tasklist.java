package com.example.workload;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class manager_tasklist extends AppCompatActivity {

    Button back;
    ArrayList<Task> tasks = new ArrayList<>();
    RecyclerView rv;
    MyRvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_tasklist);
        rv = findViewById(R.id.taskrv);

        back = findViewById(R.id.manager_tasklist_back);
        tasks.add(new Task("Developing", "Do that task","20/11/2020","Armughan","incomplete", "5","android.resource://com.example.workload/drawable/umar"));
        tasks.add(new Task("Developing", "Do that task","20/11/2020","Armughan","incomplete", "5","android.resource://com.example.workload/drawable/umar"));
        tasks.add(new Task("Developing", "Do that task","20/11/2020","Armughan","incomplete", "5","android.resource://com.example.workload/drawable/umar"));

        adapter = new MyRvAdapter(tasks,this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }
}