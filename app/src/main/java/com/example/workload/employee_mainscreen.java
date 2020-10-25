package com.example.workload;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class employee_mainscreen extends AppCompatActivity {

ImageView B1,B2,B3,B4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_mainscreen);
        B1=findViewById(R.id.b1);
        B2=findViewById(R.id.b2);
        B3=findViewById(R.id.b3);
        B4=findViewById(R.id.b4);
        ImageView menu=findViewById(R.id.menu);
        NavigationView navView = (NavigationView) findViewById(R.id.emp_navigation); // initiate a Navigation View
// implement setNavigationItemSelectedListener event on NavigationView
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if(itemId==R.id.dor_performance){
                    Intent intent =new Intent(employee_mainscreen.this,employee_performance.class);
                    startActivity(intent);
                }
                if(itemId==R.id.dor_task){
                    Intent intent =new Intent(employee_mainscreen.this,employee_tasks.class);
                    startActivity(intent);
                }
                if(itemId==R.id.dor_taskhistory){
                    Intent intent =new Intent(employee_mainscreen.this,emptask_history.class);
                    startActivity(intent);
                }
                if(itemId==R.id.dor_logout){
                    Intent intent =new Intent(employee_mainscreen.this,login.class);
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



      B1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent =new Intent(employee_mainscreen.this,employee_performance.class);
              startActivity(intent);
          }
      });
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(employee_mainscreen.this,employee_tasks.class);
                startActivity(intent);
            }
        });
        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(employee_mainscreen.this,emptask_history.class);
                startActivity(intent);
            }
        });
        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(employee_mainscreen.this,employee_profile.class);
                startActivity(intent);
            }
        });
    }

}