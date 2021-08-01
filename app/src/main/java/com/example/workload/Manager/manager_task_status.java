package com.example.workload.Manager;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.workload.Employee.employee_mainscreen;
import com.example.workload.Model.employee;
import com.example.workload.R;
import com.example.workload.create_profile;
import com.example.workload.signup;
import com.example.workload.sql_lite.MyDBHelper;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class manager_task_status extends AppCompatActivity {
    Button submit, viewImage;
    TextView name, description, status;
    EditText rating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_task_status);

        submit = findViewById(R.id.task_submit);
        name = findViewById(R.id.task_name);
        description = findViewById(R.id.task_desc);
        status = findViewById(R.id.task_status);
        rating = findViewById(R.id.rating);
        viewImage = findViewById(R.id.view_image);


        name.setText(getIntent().getStringExtra("Name").toString());
        description.setText(getIntent().getStringExtra("Description").toString());
        status.setText(getIntent().getStringExtra("Status").toString());

        viewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(manager_task_status.this);
                ViewGroup viewGroup = findViewById(R.id.content);
                ImageView img = new ImageView(manager_task_status.this);
                Picasso.get().load(getIntent().getStringExtra("Image").toString()).into(img);
                //img.setImageURI(Uri.parse(getIntent().getStringExtra("Image").toString()));
                builder.setTitle("Image");
                System.out.println(getIntent().getStringExtra("Image").toString());
                builder.setView(img);
                builder.setNegativeButton("ok", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface arg0, int arg1)
                    {

                    }
                });
                builder.show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap hasmap=new HashMap();
                hasmap.put("rating",rating.getText().toString());

                FirebaseDatabase.getInstance().getReference("task")
                        .child(getIntent().getStringExtra("id").toString()).updateChildren(hasmap)
                        .addOnSuccessListener(new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {

                                if(rating.getText().toString().matches("_")==false){
                                         //insert task in sql
                                    MyDBHelper helper=new MyDBHelper(manager_task_status.this);
                                    helper.insert_task(getIntent().getStringExtra("id").toString(),name.getText().toString()
                                            ,description.getText().toString(),getIntent().getStringExtra("due_date").toString(),
                                            getIntent().getStringExtra("emp_id").toString(),
                                            status.getText().toString(),rating.getText().toString(),getIntent().getStringExtra("Image").toString());
                                    helper.close();
/*
                                    final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
                                    DatabaseReference reference= FirebaseDatabase.getInstance().getReference("employee");

                                    reference.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                            for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                                                employee emp=snapshot.getValue(employee.class);

                                                if(emp.getId().matches(getIntent().getStringExtra("emp_id").toString())==true){
                                                       HashMap hasmap=new HashMap();
                                                       System.out.println();
                                                        hasmap.put("total_task_assign",String.valueOf(Integer.parseInt(emp.getTotal_task_assign())+1));
                                                        FirebaseDatabase.getInstance().getReference("employee")
                                                                .child(getIntent().getStringExtra("emp_id").toString()).updateChildren(hasmap);
                                                }
                                            }
                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                        }
                                    });
*/                                }
                                else{

                                }

                                finish();

                            }
                        });
            }
        });


    }
}