package com.example.workload;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class EmpTasks_Adapter extends RecyclerView.Adapter<EmpTasks_Adapter.MyViewHolder> {

    private Context c;
    private List<EmpTask_Model> ls;

    public EmpTasks_Adapter(Context c, List<EmpTask_Model> ls) {
        this.c = c;
        this.ls = ls;
    }
    @NonNull
    @Override
    public EmpTasks_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {   //holder is created
        View itemView= LayoutInflater.from(c).inflate(R.layout.emptask_row,null,false);
        return new MyViewHolder(itemView);
    }

    //holder will contain reference of emptask_histroy_row.xml layout
    @Override
    public void onBindViewHolder(@NonNull final EmpTasks_Adapter.MyViewHolder holder, final int position) {//data is inserted or binded with emptask_histroy_row.xml
        holder.title.setText(ls.get(position).getTitle());
        holder.date.setText(ls.get(position).getDate());
        holder.time.setText(ls.get(position).getTime());
        holder.l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }


    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {//the class capture the vies
        private TextView title, date,time;
        private LinearLayout l2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title =itemView.findViewById(R.id.title_);
            date =itemView.findViewById(R.id.date);
            time=itemView.findViewById(R.id.time);
            l2=itemView.findViewById(R.id.l2);
        }
    }
}