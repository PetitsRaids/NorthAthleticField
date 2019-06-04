package com.petits_raids.northathleticfield;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.petits_raids.northathleticfield.gson.Employee;

public class EmployeeInfoActivity extends AppCompatActivity {

    TextView name, phone, email, dept, job, hobby, birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_info);
        Toolbar toolbar = findViewById(R.id.employee_info_toolbar);
        toolbar.setTitle(R.string.employee_info);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        name = findViewById(R.id.employee_info_name);
        phone = findViewById(R.id.employee_info_phone);
        email = findViewById(R.id.employee_info_email);
        dept = findViewById(R.id.employee_info_dept);
        job = findViewById(R.id.employee_info_job);
        hobby = findViewById(R.id.employee_info_hobby);
        birthday = findViewById(R.id.employee_info_birthday);

        Employee employee = (Employee) getIntent().getSerializableExtra("employee");
        name.setText(employee.getName());
        phone.setText(employee.getPhone());
        email.setText(employee.getEmail());
        dept.setText(employee.getDept().getName());
        job.setText(employee.getJob().getName());
        hobby.setText(employee.getHobby());
        birthday.setText(employee.getBirthday());

        phone.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        phone.getPaint().setAntiAlias(true);
        phone.setOnClickListener(l -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + employee.getPhone()));
            startActivity(intent);
        });
        email.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        email.getPaint().setAntiAlias(true);
        email.setOnClickListener(l -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:" + employee.getEmail()));
            startActivity(intent);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
