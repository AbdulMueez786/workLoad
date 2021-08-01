package com.example.workload.adapter;

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

import com.example.workload.Model.employee;
import com.example.workload.R;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRvAdapter extends RecyclerView.Adapter<EmployeeRvAdapter.EmployeeViewHolder> implements Filterable {
    List<employee> ls;
    List<employee> lsFull;
    Context c;
    public EmployeeRvAdapter(List<employee> ls, Context c) {
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
        holder.name.setText(ls.get(position).getUsername());
        holder.job.setText(ls.get(position).getDesignation());

        holder.mEmployee = ls.get(position);

    }



    public class EmployeeViewHolder extends RecyclerView.ViewHolder {
        TextView name, job;
        View mView; employee mEmployee;
        LinearLayout ll;
        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.empname);
            job= itemView.findViewById(R.id.empjob);

            ll =itemView.findViewById(R.id.ll);
        }
    }



    public void refreshList(){
        lsFull = new ArrayList<employee>(ls);
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public void addlist(){
        lsFull =new ArrayList<employee>(ls);
    }


    @Override
    public Filter getFilter() {
        return exampleFilter;
    }


    private Filter exampleFilter=new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<employee> filteredList = new ArrayList<>();

            System.out.println("Hjhjfhjsdhjsfdh"+constraint);

            if (constraint.equals(null) || constraint.length() == 0) {
                filteredList.addAll(lsFull);
                System.out.println("Working_______________");
               // System.out.println(contact_list_copy);
            } else {
                System.out.println("Else Block");
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (employee item :lsFull) {
                    System.out.println(filterPattern);
                    System.out.println("");
                    if (item.getUsername().toLowerCase().contains(filterPattern)) {
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




}
