package com.example.workload.Employee;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.workload.Manager.manager_history;
import com.example.workload.Model.task;
import com.example.workload.MyBroadcastReceiver;
import com.example.workload.R;
import com.example.workload.adapter.EmpTaskHistory_Adapter;
import com.example.workload.sql_lite.MyDBHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class emptask_history extends AppCompatActivity {

    private ImageView menu;
    private RecyclerView rv;
    private Button button;
    private EmpTaskHistory_Adapter adapter;
    private List<task> ls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emptask_history);
        rv=findViewById(R.id.rv);
        ls=new ArrayList<>();
        rv=findViewById(R.id.rv);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        adapter=new EmpTaskHistory_Adapter(this,ls);
        rv.setAdapter(adapter);
        read();
    }
    void read() {
        MyBroadcastReceiver r;
        r=new MyBroadcastReceiver();
        if(r.connection==false) {
            FirebaseDatabase.getInstance().getReference("task").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ls.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        task t = snapshot.getValue(task.class);
                        if (t.getEmp_id().matches(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                && t.getRating().matches("_") != true
                        ) {
                            ls.add(t);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else{
            MyDBHelper helper=new MyDBHelper(emptask_history.this);
            ls.clear();

            for(task i :helper.readAlltasks()){
                if(i.getEmp_id().matches(FirebaseAuth.getInstance().getCurrentUser().getUid()) &&i.getRating().matches("_")!=true){
                    ls.add(i);
                }
            }
            adapter.notifyDataSetChanged();
            helper.close();
        }
        }

}