package com.example.workload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class employee_profile extends AppCompatActivity {
   ImageView empProf_menu;
   CircleImageView empProf_profile;
   TextView empProf_name,empProf_designation,empProf_join_Date,empProf_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);

        //empProf_menu=findViewById(R.id.empProf_menu);
        empProf_profile=findViewById(R.id.empProf_profile);
        empProf_name=findViewById(R.id.empProf_name);
        empProf_designation=findViewById(R.id.empProf_designation);
        empProf_join_Date=findViewById(R.id.empProf_joinDate);
        empProf_button=findViewById(R.id.empProf_button);



        empProf_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}