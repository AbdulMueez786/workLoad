package com.example.workload;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRvAdapter extends RecyclerView.Adapter<EmployeeRvAdapter.EmployeeViewHolder> implements Filterable {
    List<Employee> ls;
    List<Employee> lsFull;
    Context c;
    public EmployeeRvAdapter(List<Employee> ls, Context c) {
        this.c = c;
        this.ls = ls;
        lsFull = new ArrayList<>(ls);
    }

    @NonNull
    @Override

    public EmployeeRvAdapter.EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(c).inflate(R.layout.emp_row,parent, false);
        return new EmployeeViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeRvAdapter.EmployeeViewHolder holder, final int position) {
        holder.name.setText(ls.get(position).getName());
        holder.job.setText(ls.get(position).getDesignation());

        holder.mEmployee = ls.get(position);

    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {
        TextView name, job;
        View mView; Employee mEmployee;
        LinearLayout ll;
        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.empname);
            job= itemView.findViewById(R.id.empjob);

            ll =itemView.findViewById(R.id.ll);
        }
    }

    public void refreshList(){
        lsFull = new ArrayList<Employee>(ls);
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
