package com.example.workload;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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



    }
}