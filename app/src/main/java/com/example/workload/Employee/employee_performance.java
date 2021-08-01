package com.example.workload.Employee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.fonts.Font;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.workload.Model.employee;
import com.example.workload.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class employee_performance extends AppCompatActivity {
    ImageView empPerformance_menu;
    TextView empPerformance_complitedTask,empPerformance_totalTask,empPerformance_score;
    Button empPerformance_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_performance);
      // empPerformance_menu=findViewById(R.id.empPerformance_menu);
        empPerformance_complitedTask=findViewById(R.id.empPerformance_complitedTask);
        empPerformance_totalTask=findViewById(R.id.empPerformance_totalTask);
        empPerformance_score=findViewById(R.id.empPerformance_score);
        empPerformance_button=findViewById(R.id.empPerformance_button);
        empPerformance_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        FirebaseDatabase.getInstance().getReference("employee").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                empPerformance_complitedTask.setText(dataSnapshot.child("task_complited").getValue(String.class));
                empPerformance_totalTask.setText( dataSnapshot.child("total_task_assign").getValue(String.class));
                if(empPerformance_totalTask.getText().toString().matches("0")){
                    empPerformance_score.setText("0");
                }else{
                    float task_complited=Float.parseFloat(empPerformance_complitedTask.getText().toString());
                    float tasks_assigned=Float.parseFloat(empPerformance_totalTask.getText().toString());
                    float s=task_complited/tasks_assigned;
                    System.out.println(s);
                    empPerformance_score.setText(String.valueOf(s));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}