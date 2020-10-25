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


public class EmpTaskHistory_Adapter extends RecyclerView.Adapter<EmpTaskHistory_Adapter.MyViewHolder> {

    private Context c;
    private List<EmpTaskHist_Model> ls;

    public EmpTaskHistory_Adapter(Context c, List<EmpTaskHist_Model> ls) {
        this.c = c;
        this.ls = ls;
    }
    @NonNull
    @Override
    public EmpTaskHistory_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {   //holder is created
        View itemView= LayoutInflater.from(c).inflate(R.layout.emptask_histroy_row,null,false);
        return new MyViewHolder(itemView);
    }

    //holder will contain reference of emptask_histroy_row.xml layout
    @Override
    public void onBindViewHolder(@NonNull final EmpTaskHistory_Adapter.MyViewHolder holder, final int position) {//data is inserted or binded with emptask_histroy_row.xml
       holder.title.setText(ls.get(position).getTitle());
       holder.status.setText(ls.get(position).getStatus());
       holder.l1.setOnClickListener(new View.OnClickListener() {
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
        private TextView title, status;
        private LinearLayout l1;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title =itemView.findViewById(R.id.title);
            status =itemView.findViewById(R.id.status);
            l1=itemView.findViewById(R.id.l1);
        }
    }
}
