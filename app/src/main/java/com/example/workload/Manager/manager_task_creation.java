package com.example.workload.Manager;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workload.Model.employee;
import com.example.workload.MyBroadcastReceiver;
import com.example.workload.R;
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
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class manager_task_creation extends AppCompatActivity {
    private EditText task_name,task_description,task_deadline;
    private Spinner emplist;
    private List<employee> ls;
    private int selected_item=0;
    ArrayList<String> names;
    private Button createtaskbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_task_creation);

        task_name=findViewById(R.id.task_name);
        task_description=findViewById(R.id.task_description);
        task_deadline=findViewById(R.id.task_deadline);
        emplist = (Spinner)findViewById(R.id.employeedropdown);
        createtaskbutton=findViewById(R.id.createtaskbutton);

        ls=new ArrayList<>();

        names = new ArrayList<>();
        names = getIntent().getStringArrayListExtra("data");
        if(names == null){
            names = new ArrayList<>();
            read_employees();
        }

        emplist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                selected_item = position; //this is your selected item

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        createtaskbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(checkDataEntered()){


                create_task(ls.get(selected_item).getId(),task_name.getText().toString()
                        ,task_description.getText().toString(),task_deadline.getText().toString());
                finish();
            }
            }
        });
        read_employees();
    }

    void read_employees(){
        MyBroadcastReceiver r;
        r=new MyBroadcastReceiver();
        if(r.connection==false){
            final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference reference= FirebaseDatabase.getInstance().getReference("employee");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        employee emp=snapshot.getValue(employee.class);
                        assert emp!=null;
                        assert firebaseUser!=null;

                        if(emp.getId().matches(firebaseUser.getUid())==false){
                            ls.add(emp);
                            System.out.println(emp.getUsername());
                            names.add(emp.getUsername());
                        }
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(manager_task_creation.this,R.layout.support_simple_spinner_dropdown_item, names);
                    emplist.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
        else{
            MyDBHelper helper=new MyDBHelper(manager_task_creation.this);
            ls=helper.readAllData();
            System.out.println(ls+"++++++++++++++++++++++++++++++");
            for(employee i:ls ){
                names.add(i.getUsername());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(manager_task_creation.this,R.layout.support_simple_spinner_dropdown_item, names);
            emplist.setAdapter(adapter);
            helper.close();
        }

    }
    private boolean isEmpty(EditText obj) {
        CharSequence str = obj.getText().toString();
        return TextUtils.isEmpty(str);
    }
    private boolean checkDataEntered() {
        if (isEmpty(this.task_name)) {
            this.task_name.setError("task name is required!");
            return false;
        }
        else{
            //this.task_name.setEnabled(false);
        }
        if (isEmpty(this.task_deadline)) {
            this.task_deadline.setError("task deadline is required!");
            return false;
        }
        else{
            //this.task_deadline.setEnabled(false);
        }
        if (isEmpty(this.task_description)) {
            this.task_description.setError("task description is required!");
            return false;
        }
        else{
            //this.task_description.setEnabled(false);
        }
        return true;
    }
    void create_task(String emp_id,String task_name,String task_description,String task_deadline){
        MyBroadcastReceiver r;
        r=new MyBroadcastReceiver();
        if(r.connection==false) {

            // create task
            HashMap<String, Object> hm = new HashMap<>();
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            String key = ref.child("task").push().getKey();
            hm.put("id", key);
            hm.put("task_title", this.task_name.getText().toString());
            hm.put("description", this.task_description.getText().toString());
            hm.put("dueDate", this.task_deadline.getText().toString());
            hm.put("emp_id", emp_id);
            hm.put("status", "_");
            hm.put("rating", "_");
            hm.put("image", "default");
            ref.child("task").child(key).setValue(hm).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    //To generate notification
                    try {
                        OneSignal.postNotification(new JSONObject("{'contents':{'en':'" +

                                        "Manager have " + " assigned you task " +
                                        "'},'headings':{'en':'" +
                                        "Workload" +
                                        "'},'include_player_ids': ['" +
                                        ls.get(selected_item).getP_id() +
                                        "']}"),
                                new OneSignal.PostNotificationResponseHandler() {
                                    @Override
                                    public void onSuccess(JSONObject response) {
                                        finish();
                                    }

                                    @Override
                                    public void onFailure(JSONObject response) {

                                    }
                                });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        else{
            MyDBHelper helper=new MyDBHelper(manager_task_creation.this);
            helper.insert_task("2",task_name
                    ,task_description,task_deadline,emp_id,
                    "_","_","_");
            helper.close();
        }
    }




}