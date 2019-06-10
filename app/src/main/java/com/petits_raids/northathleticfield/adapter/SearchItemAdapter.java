package com.petits_raids.northathleticfield.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.petits_raids.northathleticfield.R;
import com.petits_raids.northathleticfield.SearchActivity;

import java.util.List;

public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.ViewHolder> {

    private Context context;

    private List<String> itemName;

    public SearchItemAdapter(Context context, List<String> itemName) {
        this.context = context;
        this.itemName = itemName;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.search_item_name);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_item, null, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(l -> {
            Intent intent = new Intent(context, SearchActivity.class);
            intent.putExtra("search_name", itemName.get(viewHolder.getAdapterPosition()));
            context.startActivity(intent);
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(itemName.get(position));
    }

    @Override
    public int getItemCount() {
        return itemName.size();
    }
}
