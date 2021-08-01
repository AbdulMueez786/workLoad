package com.example.workload;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.workload.Model.employee;
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

import java.util.Date;
import java.util.List;

public class signup extends AppCompatActivity {
    private EditText Signup_email,Signup_password,Signup_confirmPassword;
    private Button Signup_button;
    private FirebaseAuth auth;
    private DatabaseReference user_ref;
    private FirebaseUser USER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Signup_email =findViewById(R.id.Signup_email);
        Signup_password=findViewById(R.id.Signup_password);
        Signup_confirmPassword=findViewById(R.id.Signup_confirmPassword);
        Signup_button=findViewById(R.id.Signup_button);
        auth=FirebaseAuth.getInstance();
        user_ref=FirebaseDatabase.getInstance().getReference("employee");
        MyBroadcastReceiver receiver;
        receiver=new MyBroadcastReceiver();

        Signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String EMAIL=Signup_email.getText().toString();
                final String PASSWORD=Signup_password.getText().toString();
                final String CONFIRM_PASSWORD=Signup_confirmPassword.getText().toString();


                auth.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                        .addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {


                                if (task.isSuccessful()) {

                                    Toast toast = Toast.makeText(signup.this, "Successfully created", Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
                                    toast.show(); // display the Toast
                                    USER = FirebaseAuth.getInstance().getCurrentUser();
                                    String id = USER.getUid();
                                    DateFormat df = new DateFormat();
                                    OSPermissionSubscriptionState status = OneSignal.getPermissionSubscriptionState();
                                    String app_id = status.getSubscriptionStatus().getUserId();
                                    CharSequence s= df.format("yyyy.MM.dd", new Date());
                                    employee emp=new employee(id,EMAIL,"fname","lname","usern..","desig..","prof..",s.toString(),"0","0",app_id);
                                    user_ref.child(id).setValue(emp);



                                    Intent intent =new Intent(signup.this,create_profile.class);
                                    startActivity(intent);
                                    MyDBHelper helper=new MyDBHelper(signup.this);
                                    helper.insert_employee(id,EMAIL,"fname","lname","user","prof","",s.toString(),"0","0",app_id);
                                    helper.close();
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast toast = Toast.makeText(signup.this, "Already account on this email", Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
                                    toast.show(); // display the Toast
                                }

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast toast = Toast.makeText(signup.this, "Already account on this email", Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
                                toast.show(); // display the Toast
                            }
                        });

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
        if (isEmpty(this.Signup_email)) {
            this.Signup_email.setError("email is required!");
            return false;
        }
        else if(isEmail(this.Signup_email)==false){
            this.Signup_email.setError("invalid email");
            return false;
        }
        else{
            this.Signup_email.setEnabled(false);
        }
        if (isEmpty(this.Signup_password)) {
            this.Signup_password.setError("password is required!");
            return false;
        }
        else if(this.Signup_password.getText().length()>6){
            this.Signup_password.setError("password size minimum 6 character!");
            return false;
        }
        else{
            this.Signup_password.setEnabled(false);
        }
        if(this.Signup_confirmPassword.getText().toString().matches(this.Signup_password.getText().toString())){
            this.Signup_confirmPassword.setError("Invalid Password!");
            return false;
        }
        return true;
    }

    void Register_User(){

    }

}