package com.petits_raids.northathleticfield.fragment;

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

import java.util.ArrayList;
import java.util.List;

public class PersonFragment extends Fragment {

    private List<String> categoryList = new ArrayList<>();

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
        return view;
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
