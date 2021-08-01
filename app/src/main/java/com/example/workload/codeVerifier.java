package com.example.workload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.workload.Employee.employee_mainscreen;
import com.example.workload.Manager.manager_mainscreen;
import com.google.firebase.auth.FirebaseAuth;

public class codeVerifier extends AppCompatActivity {

    Button submit;
    EditText company_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_verifier);

        submit = findViewById(R.id.codeVerifier_submit);
        company_code=findViewById(R.id.company_code);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(company_code.getText().toString().matches("1002")){
                    if(FirebaseAuth.getInstance().getCurrentUser().getUid()!=null
                    && FirebaseAuth.getInstance().getCurrentUser().getEmail().matches("manager@gmail.com")
                    ){
                        Intent intent = new Intent(codeVerifier.this, manager_mainscreen.class);
                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(codeVerifier.this, employee_mainscreen.class);
                        startActivity(intent);
                    }
                }
            }
        });

    }
}