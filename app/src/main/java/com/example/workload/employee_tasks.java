package com.example.workload;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class employee_tasks extends AppCompatActivity {
    private ImageView menu;
    private RecyclerView rv2;
    //private Button button;
    private EmpTasks_Adapter adapter;
    private List<EmpTask_Model> ls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_tasks);
        ls=new ArrayList<>();

        this.ls.add(new EmpTask_Model("0","Tod","5-5-5","3:45pm"));
        this.ls.add(new EmpTask_Model("0","Tod","5-5-5","3:45pm"));
        this.ls.add(new EmpTask_Model("0","Tod","5-5-5","3:45pm"));
        this.ls.add(new EmpTask_Model("0","Tod","5-5-5","3:45pm"));
        this.ls.add(new EmpTask_Model("0","Tod","5-5-5","3:45pm"));
        this.ls.add(new EmpTask_Model("0","Tod","5-5-5","3:45pm"));
        this.ls.add(new EmpTask_Model("0","Tod","5-5-5","3:45pm"));
        this.ls.add(new EmpTask_Model("0","Tod","5-5-5","3:45pm"));
        this.ls.add(new EmpTask_Model("0","Tod","5-5-5","3:45pm"));
        //menu=findViewById(R.id.menu);
        rv2=findViewById(R.id.rv2);

        RecyclerView.LayoutManager manager=new LinearLayoutManager(this);
        rv2.setLayoutManager(manager);
        adapter=new EmpTasks_Adapter(this,ls);
        rv2.setAdapter(adapter);


        /*TaskEmp_recycleView=findViewById(R.id.TaskEmp_recycleView);

*/


    }
}