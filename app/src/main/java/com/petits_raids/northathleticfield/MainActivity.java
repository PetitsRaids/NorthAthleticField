package com.petits_raids.northathleticfield;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
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

    BottomNavigationView bottomNavigationView;

    MenuItem menuItem;

    private FragmentAdapter adapter;

    private List<Fragment> fragmentList = new ArrayList<>();

    private List<String> titleList = new ArrayList<>();

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
        if (date == CalenderUtils.getNianYueRi()) {
            isChecked = true;
        }
        adapter = new FragmentAdapter(MainActivity.this, getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
    }

    private void init() {
        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.attendance_view:
                    viewPager.setCurrentItem(0, true);
                    break;
                case R.id.search_view:
                    viewPager.setCurrentItem(1, true);
                    break;
                case R.id.person_view:
                    viewPager.setCurrentItem(2, true);
                    break;
                default:
                    break;
            }
            return true;
        });
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
