package com.petits_raids.northathleticfield;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.petits_raids.northathleticfield.adapter.FragmentAdapter;
import com.petits_raids.northathleticfield.fragment.AttendanceFragment;
import com.petits_raids.northathleticfield.fragment.PersonFragment;
import com.petits_raids.northathleticfield.fragment.SearchFragment;
import com.petits_raids.northathleticfield.utils.CalenderUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static boolean isChecked = false;

    private ViewPager viewPager;

    TabLayout tabLayout;

//    TabView salaryBtn, attendanceBtn, personBtn, leftTabView, rightTabView;

    private FragmentAdapter adapter;

    private List<Fragment> fragmentList = new ArrayList<>();

    private List<String> titleList = new ArrayList<>();

//    private List<TabView> tabViewList = new ArrayList<>();

    {
        fragmentList.add(new AttendanceFragment());
        fragmentList.add(new SearchFragment());
        fragmentList.add(new PersonFragment());

        titleList.add("出勤情况");
        titleList.add("信息查询");
        titleList.add("个人中心");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        long date = preferences.getLong("last_check_day", 0);
        if (date == CalenderUtils.getNianYueRi()){
            isChecked = true;
        }
        adapter = new FragmentAdapter(MainActivity.this, getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
    }

    private void init() {
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
//        attendanceBtn = findViewById(R.id.attendance_btn);
//        salaryBtn = findViewById(R.id.salary_btn);
//        personBtn = findViewById(R.id.person_btn);
//        tabViewList.add(attendanceBtn);
//        tabViewList.add(salaryBtn);
//        tabViewList.add(personBtn);
//        attendanceBtn.setOnClickListener(v -> viewPager.setCurrentItem(1, true));
//        salaryBtn.setOnClickListener(v -> viewPager.setCurrentItem(0, true));
//        personBtn.setOnClickListener(v -> viewPager.setCurrentItem(2, true));
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Logger.d("onPageScrolled 位置："+ position);
//                Logger.d("positionOffset："+ positionOffset);
//
//                if(positionOffset > 0){
//                    leftTabView = tabViewList.get(position);
//                    rightTabView = tabViewList.get(position + 1);
//                    leftTabView.setProgress(1 - positionOffset);
//                    rightTabView.setProgress(positionOffset);
//                }
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void gotoPager(int num) {
        viewPager.setCurrentItem(num, true);
    }

    public Fragment getFragmentPage(int num) {
        return adapter.getItem(num);
    }
}
