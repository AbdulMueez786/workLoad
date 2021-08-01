package com.example.workload.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workload.Model.task;
import com.example.workload.R;
import com.example.workload.Manager.manager_task_status;

import java.util.ArrayList;
import java.util.List;

public class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyViewHolder> implements Filterable {
    List<task> ls;
    List<task> lsFull;
    Context c;

    public MyRvAdapter(List<task> ls, Context c) {
        this.c = c;
        this.ls = ls;
        lsFull = new ArrayList<>(ls);
    }

    @NonNull
    @Override

    public MyRvAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(c).inflate(R.layout.task_row,parent, false);
        return new MyViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull MyRvAdapter.MyViewHolder holder, final int position) {
        holder.name.setText(ls.get(position).getTask_title());
        holder.deadline.setText("Deadline: "+ ls.get(position).getDueDate());

//
        holder.mTask = ls.get(position);


        holder.viewStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent obj=new Intent(c, manager_task_status.class);
                obj.putExtra("id",ls.get(position).getId());
                obj.putExtra("Name",ls.get(position).getTask_title());
                obj.putExtra("Description",ls.get(position).getDescription());
                obj.putExtra("Status",ls.get(position).getStatus());
                obj.putExtra("Rating",ls.get(position).getRating());
                obj.putExtra("Image",ls.get(position).getImage());
                obj.putExtra("due_date",ls.get(position).getDueDate());
                obj.putExtra("emp_id",ls.get(position).getEmp_id());
                c.startActivity(obj);

            }
        });
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, deadline;
        View mView; task mTask; Button viewStatus;
        LinearLayout ll;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.name);
            deadline= itemView.findViewById(R.id.deadline);
            viewStatus = itemView.findViewById(R.id.view_status);

            ll =itemView.findViewById(R.id.ll);
        }
    }

    public void refreshList(){
        lsFull = new ArrayList<task>(ls);
    }

    @Override
    public Filter getFilter() {
        return contactFilter;
    }

    private Filter contactFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<task> filteredList = new ArrayList<>();

            if(charSequence == null || charSequence.length() == 0){
                filteredList.addAll(lsFull);
            }
            else{
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(task c: lsFull){
                    if(c.getTask_title().toLowerCase().contains(filterPattern)){
                        filteredList.add(c);
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
            ls.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}
