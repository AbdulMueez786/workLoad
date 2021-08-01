package com.example.workload;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.workload.Employee.employee_mainscreen;
import com.example.workload.Model.employee;
import com.example.workload.sql_lite.MyDBHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class create_profile extends AppCompatActivity {

    private com.google.android.material.textfield.TextInputLayout create_profile_fname,create_profile_lname,
            create_profile_dob,create_profile_designation,create_profile_bio;
    private com.google.android.material.textview.MaterialTextView profile_gender_male,profile_gender_female,
            profile_gender_neither;
    private com.google.android.material.button.MaterialButton save_button;
    private com.google.android.material.floatingactionbutton.FloatingActionButton camera;
    private CircleImageView image_profile;
    private String  selected_gender="none";
    private int request_code=200;
    private Uri selectedImage_uri=null;
    //Firebase
    private FirebaseUser USER;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        USER=FirebaseAuth.getInstance().getCurrentUser();
        create_profile_fname=findViewById(R.id.create_profile_fname);
        create_profile_lname=findViewById(R.id.create_profile_lname);
        create_profile_bio=findViewById(R.id.create_profile_bio);
        create_profile_designation=findViewById(R.id.create_profile_designation);
        image_profile=findViewById(R.id.image_profile);
        camera=findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        save_button=findViewById(R.id.save_button);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if all fields are filed
                if(checkDataEntered()==true){
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
            storageReference=storageReference.child("profile/"+USER.getUid());
            storageReference.putFile(this.selectedImage_uri)
                    .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            Toast.makeText(create_profile.this, "complited", Toast.LENGTH_LONG).show();
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
                            hasmap.put("profile",dp);
                            hasmap.put("f_Name",create_profile_fname.getEditText().getText().toString());
                            hasmap.put("l_Name",create_profile_lname.getEditText().getText().toString());
                            hasmap.put("designation",create_profile_designation.getEditText().getText().toString());
                            hasmap.put("username",create_profile_fname.getEditText().getText().toString()+" "
                                    +create_profile_lname.getEditText().getText().toString());
                            FirebaseDatabase.getInstance().getReference("employee")
                                    .child(USER.getUid()).updateChildren(hasmap)
                                    .addOnSuccessListener(new OnSuccessListener() {
                                        @Override
                                        public void onSuccess(Object o) {
                                            MyDBHelper helper=new MyDBHelper(create_profile.this);
                                            helper.update_employee(FirebaseAuth.getInstance().getCurrentUser().getUid(),create_profile_fname.getEditText().getText().toString(),
                                                    create_profile_lname.getEditText().getText().toString(),create_profile_fname.getEditText().getText().toString()+" "+
                                                            create_profile_lname.getEditText().getText().toString(),create_profile_designation.getEditText().getText().toString(),
                                                    dp);
                                            helper.close();

                                            Intent intent =new Intent(create_profile.this, codeVerifier.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });

                            Toast.makeText(create_profile.this, "Successfull", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(create_profile.this, "Failuer", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(create_profile.this, "No internet,Try again", Toast.LENGTH_LONG).show();
                }
            });

        } else{
            Toast.makeText(create_profile.this, "Please select your profile pic", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==200){
            this.selectedImage_uri=data.getData();
            this.image_profile.setImageURI(this.selectedImage_uri);
        }
    }

    // verification
    boolean isEmpty(com.google.android.material.textfield.TextInputLayout obj) {
        CharSequence str = obj.getEditText().getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean checkDataEntered() {
        if (isEmpty(this.create_profile_fname)) {
            this.create_profile_fname.setError("First Name is required!");
            return false;
        }
        if (isEmpty(this.create_profile_lname)) {
            this.create_profile_lname.setError("Last Name is required!");
            return false;
        }

        if (isEmpty(this.create_profile_designation)) {
            this.create_profile_designation.setError("designation is required!");
            return false;
        }
        if (isEmpty(this.create_profile_bio)) {
            this.create_profile_bio.setError("Bio is required!");
            return false;
        }

        if(selectedImage_uri==null){
            Toast.makeText(create_profile.this, "Please select your profile pic", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


}