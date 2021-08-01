package com.example.workload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.graphics.Paint;

import com.example.workload.sql_lite.MyDBHelper;

public class MainActivity extends AppCompatActivity {


    TextView Main_link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyDBHelper helper=new MyDBHelper(MainActivity.this);
        helper.close();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Main_link=findViewById(R.id.Main_link);
        Main_link.setPaintFlags( Main_link.getPaintFlags() |  Paint.UNDERLINE_TEXT_FLAG);
        Main_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(MainActivity.this,login.class);
                startActivity(intent);
            }
        });
    }
}