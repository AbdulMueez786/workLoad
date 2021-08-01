package com.example.workload.Employee;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workload.Model.employee;
import com.example.workload.R;
import com.example.workload.adapter.EmployeeRvAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class employee_list extends AppCompatActivity {

    private Button back;
    private ArrayList<employee> ls;
    private RecyclerView rv;
    private EmployeeRvAdapter adapter;
    private de.hdodenhof.circleimageview.CircleImageView searchbar_profile;
    private com.google.android.material.textfield.TextInputEditText list_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        rv = findViewById(R.id.Emprv);
        back = findViewById(R.id.em_back);
        ls = new ArrayList<>();
        adapter = new EmployeeRvAdapter(ls,this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
        read();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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
    }

    void read(){
        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("employee");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ls.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    employee emp=snapshot.getValue(employee.class);
                    assert emp!=null;
                    assert firebaseUser!=null;
                    if(emp.getId().matches(firebaseUser.getUid())==false){
                        ls.add(emp);
                        System.out.println("uiuiuiiiui");
                    }
                }
                System.out.println("uiuiuiiiui");
                adapter.addlist();
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

}