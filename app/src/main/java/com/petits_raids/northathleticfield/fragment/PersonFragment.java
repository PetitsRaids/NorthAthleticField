package com.petits_raids.northathleticfield.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.petits_raids.northathleticfield.R;
import com.petits_raids.northathleticfield.adapter.BasicInformationAdapter;
import com.petits_raids.northathleticfield.utils.CalenderUtils;
import com.petits_raids.northathleticfield.utils.Logger;
import com.petits_raids.northathleticfield.view.ProgressView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonFragment extends Fragment {

    private List<String> categoryList = new ArrayList<>();

    private ProgressView monthProgress, taskProgress;

    private boolean isFirst = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        categoryList.add("1");
        categoryList.add("2");
        categoryList.add("3");
        categoryList.add("4");
        BasicInformationAdapter adapter = new BasicInformationAdapter(getContext(), categoryList);
        View view = inflater.inflate(R.layout.person_view_layout, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.person_recycler);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        monthProgress = view.findViewById(R.id.month_progress);
        taskProgress = view.findViewById(R.id.task_progress);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.d("onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.d("onResume");
        if (isFirst){
            float dateProgress = (float) CalenderUtils.getTodayDate();
            dateProgress = dateProgress / (float) CalenderUtils.getTotalDays() * 100;
            ObjectAnimator animator = ObjectAnimator.ofFloat(monthProgress, "progress", 0, dateProgress);
            animator.setDuration(500);
            animator.start();
            Random random = new Random();
            float task = random.nextFloat() * 100;
            animator = ObjectAnimator.ofFloat(taskProgress, "progress", 0, task);
            animator.setDuration(500);
            animator.start();
            isFirst = false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
