package com.example.workload;

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

import java.util.ArrayList;
import java.util.List;

public class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyViewHolder> implements Filterable {
    List<Task> ls;
    List<Task> lsFull;
    Context c;
    public MyRvAdapter(List<Task> ls, Context c) {
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
        holder.name.setText(ls.get(position).getName());
        holder.deadline.setText("Deadline: "+ ls.get(position).getDueDate());

//
        holder.mTask = ls.get(position);


        holder.viewStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent obj=new Intent(c,manager_task_status.class);

                obj.putExtra("Name",ls.get(position).getName());
                obj.putExtra("Description",ls.get(position).getDescription());
                obj.putExtra("Status",ls.get(position).getStatus());
                obj.putExtra("Rating",ls.get(position).getRating());
                obj.putExtra("Image",ls.get(position).getImage());
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
        View mView; Task mTask; Button viewStatus;
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
        lsFull = new ArrayList<Task>(ls);
    }

    @Override
    public Filter getFilter() {
        return contactFilter;
    }

    private Filter contactFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

//            List<Task> filteredList = new ArrayList<>();
//
//            if(charSequence == null || charSequence.length() == 0){
//                filteredList.addAll(lsFull);
//            }
//            else{
//                String filterPattern = charSequence.toString().toLowerCase().trim();
//
//                for(Task c: lsFull){
//                    if(c.getName().toLowerCase().contains(filterPattern)){
//                        filteredList.add(c);
//                    }
//                }
//            }
            FilterResults results = new FilterResults();
//            results.values = filteredList;
//
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
