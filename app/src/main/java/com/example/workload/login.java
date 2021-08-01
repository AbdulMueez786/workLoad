package com.example.workload;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workload.Employee.employee_mainscreen;
import com.example.workload.Manager.manager_history;
import com.example.workload.Manager.manager_mainscreen;
import com.example.workload.Model.employee;
import com.example.workload.Model.task;
import com.example.workload.sql_lite.MyDBHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OneSignal;

import java.util.HashMap;

public class login extends AppCompatActivity {
    private FirebaseUser login_firebaseUser;
    private EditText login_email,login_password;
    private Button login_button;
    private TextView login_registerLink;
    private FirebaseAuth auth;
    private DatabaseReference user_ref;

    @Override
    protected void onStart(){
        super.onStart();
        //S

        login_firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

        if(login_firebaseUser!=null){


            if(login_firebaseUser.getEmail().matches("manager@gmail.com")==true){
                //Manager
                Intent intent=new Intent(login.this,manager_mainscreen.class);
                startActivity(intent);
                finish();
            }
            else {
                //employee
                Intent intent=new Intent(login.this,employee_mainscreen.class);
                startActivity(intent);
                finish();
            }

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email=findViewById(R.id.login_email);
        login_password=findViewById(R.id.login_password);
        login_registerLink=findViewById(R.id.login_registerLink);
        login_button=findViewById(R.id.login_button);
        auth=FirebaseAuth.getInstance();

        user_ref=FirebaseDatabase.getInstance().getReference("employee");
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(checkDataEntered()) {
                        final String email = login_email.getText().toString();
                        final String password = login_password.getText().toString();
                        auth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {

                                            OSPermissionSubscriptionState status = OneSignal.getPermissionSubscriptionState();
                                            String app_id = status.getSubscriptionStatus().getUserId();
                                            HashMap hasmap = new HashMap();
                                            hasmap.put("p_id", app_id);
                                            user_ref.child(auth.getCurrentUser().getUid()).updateChildren(hasmap);

                                            if (email.trim().matches("manager@gmail.com")) {
                                                Intent intent = new Intent(login.this, manager_mainscreen.class);
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                Intent intent = new Intent(login.this, employee_mainscreen.class);
                                                startActivity(intent);
                                                finish();
                                            }

                                        } else {
                                            Toast toast = Toast.makeText(login.this, "Incorrect Input", Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
                                            toast.show(); // display the Toast
                                        }
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast toast = Toast.makeText(login.this, "Sorry some error occur please try again", Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
                                        toast.show(); // display the Toast
                                    }
                                });
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

    //Varification
    private boolean isEmpty(EditText obj) {
        CharSequence str = obj.getText().toString();
        return TextUtils.isEmpty(str);
    }
    private boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    private boolean checkDataEntered() {
        if (isEmpty(this.login_email)) {
            this.login_email.setError("email is required!");
            return false;
        }
        else if(isEmail(this.login_email)==false){
            this.login_email.setError("invalid email");
            return false;
        }
        else{
            //this.login_email.setEnabled(false);
        }
        if (isEmpty(this.login_password)) {
            this.login_password.setError("password is required!");
            return false;
        }
        else if(this.login_password.getText().length()<6){
            this.login_password.setError("password size minimum 6 character!");
            return false;
        }
        else{
            //this.login_password.setEnabled(false);
        }
        return true;
    }
}