package com.example.workload;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class manager_mainscreen extends AppCompatActivity {

    ImageButton employeeList, currTasks, taskHistory, createTask;
    ArrayList<Employee> emps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_mainscreen);
        employeeList = findViewById(R.id.employee_list);
        currTasks = findViewById(R.id.current_tasks);
        taskHistory = findViewById(R.id.task_history);
        createTask = findViewById(R.id.create_task);
        ImageView menu=findViewById(R.id.menu);

        NavigationView navView = (NavigationView) findViewById(R.id.man_navigation); // initiate a Navigation View
// implement setNavigationItemSelectedListener event on NavigationView
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if(itemId==R.id.dor_emplist){
                    Intent intent =new Intent(manager_mainscreen.this,manager_tasklist.class);
                    startActivity(intent);
                }
                if(itemId==R.id.dor_current_task){
                    Intent intent =new Intent(manager_mainscreen.this,manager_task_creation.class);
                    startActivity(intent);
                }
                if(itemId==R.id.dor_man_taskhistory){
                    Intent intent =new Intent(manager_mainscreen.this,manager_history.class);
                    startActivity(intent);
                }
                if(itemId==R.id.dor_man_logout){
                    Intent intent =new Intent(manager_mainscreen.this,login .class);
                    startActivity(intent);
                }
                return false;
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout navDrawer = findViewById(R.id.drawer_layout);
                // If the navigation drawer is not open then open it, if its already open then close it.
                if(!navDrawer.isDrawerOpen(GravityCompat.START)) {
                    navDrawer.openDrawer(GravityCompat.START);
                } else
                    navDrawer.closeDrawer(GravityCompat.END);
            }
        });

        emps.add(new Employee("Armughan", "CS Engineer", "android.resource://com.example.workload/drawable/umar","20/10/2020"));
        emps.add(new Employee("Ali", "CS Engineer", "android.resource://com.example.workload/drawable/umar","20/10/2020"));
        emps.add(new Employee("Imran", "CS Engineer", "android.resource://com.example.workload/drawable/umar","20/10/2020"));

        employeeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(manager_mainscreen.this, employee_list.class);
                startActivity(intent);
            }
        });

        currTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(manager_mainscreen.this, manager_tasklist.class);
                startActivity(intent);
            }
        });

        createTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(manager_mainscreen.this,manager_task_creation.class);

                ArrayList<String> employeeNames = new ArrayList<>();
                for(Employee e: emps){
                    employeeNames.add(e.getName());
                }

                intent.putExtra("data", employeeNames);

                startActivity(intent);

            }
        });

        taskHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(manager_mainscreen.this, manager_history.class);
                startActivity(intent);
            }
        });

    }
}