package com.petits_raids.northathleticfield;

import android.os.Bundle;
import android.widget.Button;

import com.petits_raids.northathleticfield.adapter.FragmentAdapter;
import com.petits_raids.northathleticfield.fragment.AttendanceFragment;
import com.petits_raids.northathleticfield.fragment.PersonFragment;
import com.petits_raids.northathleticfield.fragment.SalaryFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    Button salaryBtn, attendanceBtn, personBtn;

    private FragmentAdapter adapter;

    private List<Fragment> fragmentList = new ArrayList<>();

    {
        fragmentList.add(new AttendanceFragment());
        fragmentList.add(new SalaryFragment());
        fragmentList.add(new PersonFragment());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        adapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
    }

    private void init() {
        viewPager = findViewById(R.id.view_pager);
        salaryBtn = findViewById(R.id.salary_btn);
        attendanceBtn = findViewById(R.id.attendance_btn);
        personBtn = findViewById(R.id.person_btn);
        salaryBtn.setOnClickListener(v -> viewPager.setCurrentItem(0, true));
        attendanceBtn.setOnClickListener(v -> viewPager.setCurrentItem(1, true));
        personBtn.setOnClickListener(v -> viewPager.setCurrentItem(2, true));
    }
}
