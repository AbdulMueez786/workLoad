package com.example.workload.Employee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.workload.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

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
        read();
    }
    void read(){

        FirebaseDatabase.getInstance().getReference("employee").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                empProf_name.setText(dataSnapshot.child("username").getValue(String.class));
                empProf_designation.setText(dataSnapshot.child("designation").getValue(String.class));
                empProf_join_Date.setText(dataSnapshot.child("joining_date").getValue(String.class));
                Picasso.get().load(dataSnapshot.child("profile").getValue(String.class))
                        .into(empProf_profile);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}