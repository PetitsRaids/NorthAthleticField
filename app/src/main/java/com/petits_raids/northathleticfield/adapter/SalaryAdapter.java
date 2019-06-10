package com.petits_raids.northathleticfield.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.petits_raids.northathleticfield.R;
import com.petits_raids.northathleticfield.utils.CalenderUtils;

import java.math.BigDecimal;

public class SalaryAdapter extends RecyclerView.Adapter<SalaryAdapter.ViewHolder> {

    private Context context;

    private static int month = CalenderUtils.getTodayMonth();

    private static String Year = "今年";

    public SalaryAdapter(Context context) {
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView salaryTv;

        ViewHolder(View view) {
            super(view);
            salaryTv = view.findViewById(R.id.salary_tv);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.salary_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BigDecimal bigDecimal = new BigDecimal(5000 + Math.random() * 5000);
        String salary = getLastMonth() + bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        holder.salaryTv.setText(salary);
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    private String getLastMonth() {
        if (month == 1) {
            month = 12;
            Year = "去年";
        } else
            --month;
        return Year + month + "月: ";
    }
}
