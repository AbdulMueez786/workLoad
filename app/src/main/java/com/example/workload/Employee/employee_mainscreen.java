package com.example.workload.Employee;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.workload.R;
import com.example.workload.login;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

public class employee_mainscreen extends AppCompatActivity {
    private CircleImageView Drawer_Profile_Pic;
    private TextView Drawer_username,Drawer_email;
    ImageView B1,B2,B3,B4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_mainscreen);
        B1=findViewById(R.id.b1);
        B2=findViewById(R.id.b2);
        B3=findViewById(R.id.b3);
        B4=findViewById(R.id.b4);
        ImageView menu=findViewById(R.id.menu);
        NavigationView navView = (NavigationView) findViewById(R.id.emp_navigation); // initiate a Navigation View
        View hview=navView.getHeaderView(0);

        Drawer_Profile_Pic=hview.findViewById(R.id.Home_profilePic);
        Drawer_email=hview.findViewById(R.id.drawer_email);
        Drawer_username=hview.findViewById(R.id.drawer_username);

        read();
     // implement setNavigationItemSelectedListener event on NavigationView
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if(itemId==R.id.dor_performance){
                    Intent intent =new Intent(employee_mainscreen.this,employee_performance.class);
                    startActivity(intent);
                }
                if(itemId==R.id.dor_task){
                    Intent intent =new Intent(employee_mainscreen.this,employee_tasks.class);
                    startActivity(intent);
                }
                if(itemId==R.id.dor_taskhistory){
                    Intent intent =new Intent(employee_mainscreen.this,emptask_history.class);
                    startActivity(intent);
                }
                if(itemId==R.id.dor_logout){
                    FirebaseAuth.getInstance().signOut();
                    Intent intent =new Intent(employee_mainscreen.this, login.class);
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

      B1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent =new Intent(employee_mainscreen.this,employee_performance.class);
              startActivity(intent);
          }
      });
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(employee_mainscreen.this,employee_tasks.class);
                startActivity(intent);
            }
        });
        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(employee_mainscreen.this,emptask_history.class);
                startActivity(intent);
            }
        });
        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(employee_mainscreen.this,employee_profile.class);
                startActivity(intent);
            }
        });
    }
    void read(){

        FirebaseDatabase.getInstance().getReference("employee").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                Drawer_username.setText(dataSnapshot.child("username").getValue(String.class));
                Drawer_email.setText(dataSnapshot.child("email").getValue(String.class));
                Picasso.get().load(dataSnapshot.child("profile").getValue(String.class))
                        .into(Drawer_Profile_Pic);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}