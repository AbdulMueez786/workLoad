package com.example.workload.Employee;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workload.Model.employee;
import com.example.workload.R;
import com.example.workload.create_profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class Employee_task_detail extends AppCompatActivity {

    private TextView task_name,task_desc,task_status,task_deadline;
    private Button upload_image,task_submit;
    private int request_code=200;
    private  Uri selectedImage_uri=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_task_detail);
        task_name=findViewById(R.id.task_name);
        task_desc=findViewById(R.id.task_desc);
        task_deadline=findViewById(R.id.task_deadline);
        task_status=findViewById(R.id.task_status);
        upload_image=findViewById(R.id.upload_image);
        task_submit=findViewById(R.id.task_submit);

        task_name.setText(getIntent().getStringExtra("task_name").toString());
        task_desc.setText(getIntent().getStringExtra("task_desc").toString());
        task_deadline.setText(getIntent().getStringExtra("task_deadline").toString());
        task_status.setText(getIntent().getStringExtra("task_status").toString());

        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        task_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedImage_uri!=null){
                    StoreProfile();
                }
            }
        });
    }
    void openGallery(){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,request_code);
    }

    void StoreProfile(){
        if(selectedImage_uri!=null){
            StorageReference storageReference= FirebaseStorage.getInstance().getReference();
            storageReference=storageReference.child("profile/"+ getIntent().getStringExtra("id").toString());
            storageReference.putFile(this.selectedImage_uri)
                    .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            Toast.makeText(Employee_task_detail.this, "uploded", Toast.LENGTH_LONG).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> task=taskSnapshot.getStorage().getDownloadUrl();
                    task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            final String dp=uri.toString();
                            HashMap hasmap=new HashMap();
                            hasmap.put("image",dp);
                            hasmap.put("status","completed");
                            FirebaseDatabase.getInstance().getReference("task")
                                    .child(getIntent().getStringExtra("id").toString()).updateChildren(hasmap)
                                    .addOnSuccessListener(new OnSuccessListener() {
                                        @Override
                                        public void onSuccess(Object o) {


                                            /*
                                            FirebaseDatabase.getInstance().getReference("employee")
                                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                            for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                                                                employee o=dataSnapshot.getValue(employee.class);
                                                                if(o.getId().matches(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                                                                    HashMap hasmap=new HashMap();
                                                                    hasmap.put("task_complited",String.valueOf(Integer.parseInt(o.getTask_complited()+1)));
                                                                    FirebaseDatabase.getInstance().getReference("employee")
                                                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hasmap);

                                                                }

                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                                        }
                                                    });
                                            */
                                            finish();
                                        }
                                    });

                            Toast.makeText(Employee_task_detail.this, "Successfull", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Employee_task_detail.this, "Failuer", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Employee_task_detail.this, "No internet,Try again", Toast.LENGTH_LONG).show();
                }
            });

        } else{
            Toast.makeText(Employee_task_detail.this, "Please select your profile pic", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==200){
            this.selectedImage_uri=data.getData();
            this.upload_image.setText("uploded...");
            ///this.image_profile.setImageURI(this.selectedImage_uri);
        }
    }

}