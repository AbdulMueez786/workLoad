package com.example.workload.Manager;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.workload.Employee.employee_list;
import com.example.workload.Model.employee;
import com.example.workload.Model.task;
import com.example.workload.MyBroadcastReceiver;
import com.example.workload.R;
import com.example.workload.login;
import com.example.workload.sql_lite.MyDBHelper;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.onesignal.OneSignal;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class manager_mainscreen extends AppCompatActivity {

    MyBroadcastReceiver receiver;
    private CircleImageView Drawer_Profile_Pic;
    private TextView Drawer_username,Drawer_email;
    ImageButton employeeList, currTasks, taskHistory, createTask;
    ArrayList<employee> emps = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();
        this.panding_request();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_mainscreen);
        //receiver=new MyBroadcastReceiver();
        //IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        //intentFilter.addAction("com.armughanaslam.broadcastsender2");


        //registerReceiver(receiver,intentFilter);

        employeeList = findViewById(R.id.employee_list);
        currTasks = findViewById(R.id.current_tasks);
        taskHistory = findViewById(R.id.task_history);
        createTask = findViewById(R.id.create_task);
        ImageView menu=findViewById(R.id.menu);

        NavigationView navView = (NavigationView) findViewById(R.id.man_navigation); // initiate a Navigation View
        View hview=navView.getHeaderView(0);
        Drawer_Profile_Pic=hview.findViewById(R.id.Home_profilePic);
        Drawer_email=hview.findViewById(R.id.drawer_email);
        Drawer_username=hview.findViewById(R.id.drawer_username);

        Drawer_email.setText("manager@gmail.com");
        Drawer_username.setText("S Manager");
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/workload-f6685.appspot.com/o/profile%2FxfCiUqp67PgsocbdowxrtmbjafI2?alt=media&token=2af9c5eb-33ae-45d2-ab86-399e93a9e257").into(Drawer_Profile_Pic);
// implement setNavigationItemSelectedListener event on NavigationView
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if(itemId==R.id.dor_emplist){
                    Intent intent =new Intent(manager_mainscreen.this,manager_tasklist.class);
                    startActivity(intent);
                }
                if(itemId==R.id.dor_current_task){
                    Intent intent =new Intent(manager_mainscreen.this,manager_task_creation.class);
                    startActivity(intent);
                }
                if(itemId==R.id.dor_man_taskhistory){
                    Intent intent =new Intent(manager_mainscreen.this,manager_history.class);
                    startActivity(intent);
                }
                if(itemId==R.id.dor_man_logout){
                    FirebaseAuth.getInstance().signOut();
                    Intent intent =new Intent(manager_mainscreen.this, login.class);
                    startActivity(intent);
                }
                return false;
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout navDrawer = findViewById(R.id.drawer_layout);
                // If the navigation drawer is not open then open it, if its already open then close it.
                if(!navDrawer.isDrawerOpen(GravityCompat.START)) {
                    navDrawer.openDrawer(GravityCompat.START);
                } else
                    navDrawer.closeDrawer(GravityCompat.END);
            }
        });

      //  emps.add(new employee("Armughan", "CS Engineer", "android.resource://com.example.workload/drawable/umar","20/10/2020","" ,"" , "", "", "","","" ));


        employeeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(manager_mainscreen.this, employee_list.class);
                startActivity(intent);
            }
        });

        currTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(manager_mainscreen.this, manager_tasklist.class);
                startActivity(intent);
            }
        });

        createTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(manager_mainscreen.this,manager_task_creation.class);

                ArrayList<String> employeeNames = new ArrayList<>();
                for(employee e: emps){
                    employeeNames.add(e.getUsername());
                }

                intent.putExtra("data", employeeNames);

                startActivity(intent);

            }
        });

        taskHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(manager_mainscreen.this, manager_history.class);
                startActivity(intent);
            }
        });
    }

    void panding_request(){

        MyBroadcastReceiver r;
        r=new MyBroadcastReceiver();
        if(r.connection==false) {
            MyDBHelper helper = new MyDBHelper(manager_mainscreen.this);
            String k = "2";
            for (task i : helper.readAlltasks()) {
                if (i.getId().matches("2")) {
                    String key = FirebaseDatabase.getInstance().getReference("task").push().getKey();
                    i.setId(key);
                    FirebaseDatabase.getInstance().getReference("task").child(key).setValue(i);
                    System.out.println(key +" = "+i.getId());
                    System.out.println(helper.readAlltasks());
                    helper.delete("2");
                    System.out.println(helper.readAlltasks());
                    helper.insert_task(i.getId(),i.getTask_title(),i.getDescription(),i.getDueDate(),
                            i.getEmp_id(),i.getStatus(),i.getRating(),i.getImage());
                    System.out.println(helper.readAlltasks());
                    FirebaseDatabase.getInstance().getReference("employee").child(i.getEmp_id())
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                               try {
                                    String s=dataSnapshot.child("p_id").getValue(String.class);
                                    OneSignal.postNotification(new JSONObject("{'contents':{'en':'" +

                                                    "Manager have " + " assigned you task " +
                                                    "'},'headings':{'en':'" +
                                                    "Workload" +
                                                    "'},'include_player_ids': ['" +
                                                    s +
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
                                } catch (
                                JSONException e) {
                                    e.printStackTrace();
                                }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                }
            }
            helper.close();
        }
        else{

        }
    }
}