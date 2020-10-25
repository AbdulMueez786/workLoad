package com.example.workload;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
                img.setImageURI(Uri.parse(getIntent().getStringExtra("Image").toString()));
                builder.setTitle("Image");
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
                finish();
            }
        });

    }
}