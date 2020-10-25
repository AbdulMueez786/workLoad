package com.example.workload;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class manager_history extends AppCompatActivity {

    private ImageView menu;
    private RecyclerView rv;
    private Button button;
    private EmpTaskHistory_Adapter adapter;
    private List<EmpTaskHist_Model> ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_history);
        //menu =findViewById(R.id.menu);
        //button=findViewById(R.id.button);


        ls=new ArrayList<>();
        ls.add(new EmpTaskHist_Model("0","Work on X102","Done"));
        ls.add(new EmpTaskHist_Model("0","Fix Bug","Not Done"));
        ls.add(new EmpTaskHist_Model("0","Work on X105","Done"));
        ls.add(new EmpTaskHist_Model("0","Fix Bug","Not Done"));
        ls.add(new EmpTaskHist_Model("0","Work on Rtx909","Done"));
        ls.add(new EmpTaskHist_Model("0","Work on X102","Done"));
        ls.add(new EmpTaskHist_Model("0","Fix Bug","Not Done"));
        ls.add(new EmpTaskHist_Model("0","Work on X105","Done"));
        ls.add(new EmpTaskHist_Model("0","Fix Bug","Not Done"));
        ls.add(new EmpTaskHist_Model("0","Work on Rtx909","Done"));
        rv=findViewById(R.id.man_rv);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        adapter=new EmpTaskHistory_Adapter(this,ls);
        rv.setAdapter(adapter);
    }
}