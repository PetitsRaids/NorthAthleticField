package com.petits_raids.northathleticfield.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.petits_raids.northathleticfield.R;
import com.petits_raids.northathleticfield.adapter.SearchResultAdapter;
import com.petits_raids.northathleticfield.gson.Dept;
import com.petits_raids.northathleticfield.gson.Employee;
import com.petits_raids.northathleticfield.gson.Job;
import com.petits_raids.northathleticfield.listener.HttpCallBackListener;
import com.petits_raids.northathleticfield.utils.DecodeJsonUtils;
import com.petits_raids.northathleticfield.utils.HttpUtils;
import com.petits_raids.northathleticfield.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private List<Employee> employeeList;

    private SearchResultAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_view_layout, container, false);
        Toolbar toolbar = view.findViewById(R.id.search_toolbar);
        RecyclerView recyclerView = view.findViewById(R.id.search_result);

        setHasOptionsMenu(true);
        toolbar.inflateMenu(R.menu.search_menu);
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.search) {
                Logger.d(android.os.Process.myTid());
                HttpUtils.requestData("http://192.168.43.120:8080/first/employee/provide", new HttpCallBackListener() {
                    @Override
                    public void onSuccess(String response) {
                        Logger.d(response);
                        Logger.d(android.os.Process.myTid());
                        employeeList.clear();
                        employeeList.addAll(DecodeJsonUtils.decodeJson(response));
                        getActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getContext(), R.string.request_error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
            return true;
        });
        employeeList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        Employee employee = new Employee();
        employee.setName("dashui");
        employee.setBirthday("1997-06-23");
        employee.setPhone("12345678909");
        employee.setEmail("sdfghjkerty@126.com");
        employee.setHobby("eat");
        Job job = new Job();
        job.setName("大苏打实打实打算");
        Dept dept = new Dept();
        dept.setName("开发工程师");
        employee.setDept(dept);
        employee.setJob(job);
        employeeList.add(employee);
        adapter = new SearchResultAdapter(getContext(), employeeList);
        recyclerView.setAdapter(adapter);
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
