package com.example.workload;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class employee_list extends AppCompatActivity {

    Button back;
    ArrayList<Employee> emps = new ArrayList<>();
    RecyclerView rv;
    EmployeeRvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        rv = findViewById(R.id.Emprv);
        back = findViewById(R.id.em_back);

        emps.add(new Employee("Armughan", "CS Engineer", "armughan.jpg","20/10/2020"));
        emps.add(new Employee("Ali", "CS Engineer", "armughan.jpg","20/10/2020"));
        emps.add(new Employee("Imran", "CS Engineer", "armughan.jpg","20/10/2020"));
        emps.add(new Employee("Gujjar", "CS Engineer", "armughan.jpg","20/10/2020"));

        adapter = new EmployeeRvAdapter(emps,this);
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
}