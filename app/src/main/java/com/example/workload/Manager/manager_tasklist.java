package com.example.workload.Manager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workload.Model.task;
import com.example.workload.R;
import com.example.workload.adapter.MyRvAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class manager_tasklist extends AppCompatActivity {

    Button back;
    ArrayList<task> tasks = new ArrayList<>();
    RecyclerView rv;
    MyRvAdapter adapter;
    private de.hdodenhof.circleimageview.CircleImageView searchbar_profile;
    private com.google.android.material.textfield.TextInputEditText list_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_tasklist);
        this.searchbar_profile=findViewById(R.id.searchbar_profile);
        this.list_search=findViewById(R.id.list_search);

        this.list_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        this.searchbar_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDrawer();
            }
        });
        rv = findViewById(R.id.taskrv);
        back = findViewById(R.id.manager_tasklist_back);
        adapter = new MyRvAdapter(tasks,this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        read();
    }
    void read(){
        FirebaseDatabase.getInstance().getReference("task").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               tasks.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    task t=snapshot.getValue(task.class);
                    if(t.getRating().matches("_")==true
                    ){
                        tasks.add(t);
                    }

                }
                adapter.refreshList();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    void openDrawer(){
       /* DrawerLayout navDrawer = this.findViewById(R.id.Home_drawer);
        if(!navDrawer.isDrawerOpen(GravityCompat.START))
            navDrawer.openDrawer(GravityCompat.START);
        else
            navDrawer.closeDrawer(GravityCompat.END);*/
    }
    @Override
    protected void onRestart() {
        super.onRestart();

    }
}