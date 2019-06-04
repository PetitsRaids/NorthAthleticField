package com.petits_raids.northathleticfield.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.petits_raids.northathleticfield.EmployeeInfoActivity;
import com.petits_raids.northathleticfield.R;
import com.petits_raids.northathleticfield.gson.Employee;

import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    private Context context;

    private List<Employee> employeeList;

    public SearchResultAdapter(Context context, List<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.employee_name);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_result_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(l -> {
            Intent intent = new Intent(context, EmployeeInfoActivity.class);
            intent.putExtra("employee", employeeList.get(viewHolder.getAdapterPosition()));
            context.startActivity(intent);
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(employeeList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }
}
