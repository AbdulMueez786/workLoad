package com.example.workload.adapter;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workload.Employee.Employee_task_detail;
import com.example.workload.Model.task;
import com.example.workload.R;

import java.util.ArrayList;
import java.util.List;
public class EmpTasks_Adapter extends RecyclerView.Adapter<EmpTasks_Adapter.MyViewHolder> implements Filterable {

    private Context c;
    private List<task> ls;
    private List<task> contact_list_copy;
    public EmpTasks_Adapter(Context c, List<task> ls) {
        this.c = c;
        this.ls = ls;
        this.contact_list_copy =new ArrayList<>(ls);//copy of our main list
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
        holder.title.setText(ls.get(position).getTask_title());
        holder.date.setText(ls.get(position).getDueDate());
        //holder.time.setText(ls.get(position).getTime());
        holder.l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(c, Employee_task_detail.class);
                intent.putExtra("id",ls.get(position).getId());
                intent.putExtra("task_name",ls.get(position).getTask_title());
                intent.putExtra("task_desc",ls.get(position).getDescription());
                intent.putExtra("task_deadline",ls.get(position).getDueDate());
                intent.putExtra("task_status",ls.get(position).getStatus());
                c.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return ls.size();
    }

    public void addlist(){
        contact_list_copy =new ArrayList<task>(ls);
    }


    @Override
    public Filter getFilter() {
        return exampleFilter;
    }


    private Filter exampleFilter=new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<task> filteredList = new ArrayList<>();

            System.out.println("Hjhjfhjsdhjsfdh"+constraint);

            if (constraint.equals(null) || constraint.length() == 0) {
                filteredList.addAll(contact_list_copy);
                System.out.println("Working_______________");
                System.out.println(contact_list_copy);
            } else {
                System.out.println("Else Block");
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (task item : contact_list_copy) {
                    System.out.println(filterPattern);
                    System.out.println("");
                    if (item.getTask_title().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            ls.clear();
            System.out.println("Result");
            ls.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };



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