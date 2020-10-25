package com.example.workload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class login extends AppCompatActivity {

    private EditText login_username,login_password;
    private Button login_button;
    private TextView login_registerLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_username=findViewById(R.id.login_username);
        login_password=findViewById(R.id.login_password);
        login_registerLink=findViewById(R.id.login_registerLink);
        login_button=findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = login_username.getText().toString();
                if(username.equals("manager") || username.equals("Manager")){
                    Intent intent =new Intent(login.this,manager_mainscreen.class);
                    startActivity(intent);
                }
                else{
                    Intent intent =new Intent(login.this,codeVerifier.class);
                    startActivity(intent);
                }

            }
        });

        login_registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(login.this,signup.class);
                startActivity(intent);
            }
        });

    }
}