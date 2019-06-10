package com.petits_raids.northathleticfield;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.petits_raids.northathleticfield.adapter.SalaryAdapter;
import com.petits_raids.northathleticfield.adapter.SearchResultAdapter;
import com.petits_raids.northathleticfield.gson.Employee;
import com.petits_raids.northathleticfield.listener.HttpCallBackListener;
import com.petits_raids.northathleticfield.utils.DecodeJsonUtils;
import com.petits_raids.northathleticfield.utils.HttpUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    String titleName;

    RecyclerView.Adapter adapter;

    List<Employee> employeeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        titleName = getIntent().getStringExtra("search_name");
        Toolbar toolbar = findViewById(R.id.search_toolbar);
        toolbar.setTitle(titleName);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        RecyclerView recyclerView = findViewById(R.id.search_result);
        LinearLayoutManager manager = new LinearLayoutManager(SearchActivity.this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        if (!titleName.equals("薪资发放")) {
            HttpUtils.requestData("http://192.168.43.120:8080/first/employee/provide", new HttpCallBackListener() {
                @Override
                public void onSuccess(String response) {
                    employeeList.clear();
                    employeeList.addAll(DecodeJsonUtils.decodeJson(response));
                    adapter = new SearchResultAdapter(SearchActivity.this, employeeList);
                    runOnUiThread(() -> {
                        recyclerView.setAdapter(adapter);
                        Toast.makeText(SearchActivity.this, R.string.data_update, Toast.LENGTH_SHORT).show();
                    });
                }

                @Override
                public void onError(Exception e) {
                    runOnUiThread(() -> Toast.makeText(SearchActivity.this, R.string.request_error, Toast.LENGTH_SHORT).show());
                }
            });
        } else {
            adapter = new SalaryAdapter(SearchActivity.this);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!titleName.equals("薪资发放")) {
            getMenuInflater().inflate(R.menu.search_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.search:
                break;
            default:
                break;
        }
        return true;
    }
}
