package com.example.workload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class signup extends AppCompatActivity {
    private EditText Signup_username,Signup_password,Signup_confirmPassword;
    private Button Signup_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Signup_username =findViewById(R.id.Signup_username);
        Signup_password=findViewById(R.id.Signup_password);
        Signup_confirmPassword=findViewById(R.id.Signup_confirmPassword);
        Signup_button=findViewById(R.id.Signup_button);

        Signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(signup.this,codeVerifier.class);
                startActivity(intent);
            }
        });

    }
}