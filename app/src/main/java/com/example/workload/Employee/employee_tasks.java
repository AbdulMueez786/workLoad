package com.example.workload.Employee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;

import com.example.workload.Model.task;
import com.example.workload.R;
import com.example.workload.adapter.EmpTasks_Adapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class employee_tasks extends AppCompatActivity {
    private ImageView menu;
    private RecyclerView rv2;
    //private Button button;
    private EmpTasks_Adapter adapter;
    private List<task> ls;
    private de.hdodenhof.circleimageview.CircleImageView searchbar_profile;
    private com.google.android.material.textfield.TextInputEditText list_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_tasks);
        this.searchbar_profile=findViewById(R.id.searchbar_profile);
        this.list_search=findViewById(R.id.list_search);

        ls=new ArrayList<>();
        //menu=findViewById(R.id.menu);
        rv2=findViewById(R.id.rv2);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(this);
        rv2.setLayoutManager(manager);
        adapter=new EmpTasks_Adapter(this,ls);
        rv2.setAdapter(adapter);
        read();
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
                //openDrawer();
            }
        });
    }
    void read(){
        FirebaseDatabase.getInstance().getReference("task").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ls.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                   task t=snapshot.getValue(task.class);
                 if(t.getEmp_id().matches(FirebaseAuth.getInstance().getCurrentUser().getUid())
                 &&  t.getRating().matches("_")
                 ){
                   ls.add(t);
                 }
                }
                adapter.addlist();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}