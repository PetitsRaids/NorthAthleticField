package com.petits_raids.northathleticfield.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.petits_raids.northathleticfield.R;

import java.util.List;

public class BasicInformationAdapter extends RecyclerView.Adapter<BasicInformationAdapter.ViewHolder> {

    private Context context;

    private List<String> categoryList;

    public BasicInformationAdapter(Context context, List<String> categoryList){
        this.context = context;
        this.categoryList = categoryList;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView category, specific;
        ViewHolder(View view){
            super(view);
            category = view.findViewById(R.id.category);
            specific = view.findViewById(R.id.specific);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.basic_information_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.category.setText(categoryList.get(position));
        holder.specific.setText(categoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
