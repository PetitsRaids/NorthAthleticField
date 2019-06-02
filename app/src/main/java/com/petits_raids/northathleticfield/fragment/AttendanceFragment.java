package com.petits_raids.northathleticfield.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.petits_raids.northathleticfield.MainActivity;
import com.petits_raids.northathleticfield.R;
import com.petits_raids.northathleticfield.utils.CalenderUtils;

import java.util.HashMap;
import java.util.Map;

public class AttendanceFragment extends Fragment {

    private CalendarView calendarView;

    private Toolbar toolbar;

    private Map<String, Calendar> map = new HashMap<>();

    private SharedPreferences sharedPreferences;

    private String allCheckedDays;

    private int year;

    private int month;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.attendance_view_layout, container, false);
        calendarView = view.findViewById(R.id.calendarView);
        toolbar = view.findViewById(R.id.attendance_toolbar);
        setHasOptionsMenu(true);

        initData();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        toolbar.inflateMenu(R.menu.check_menu);
        if (MainActivity.isChecked) {
            setMenuChecked();
        }
        toolbar.setOnMenuItemClickListener(item -> {
            if (!MainActivity.isChecked) {
                Calendar calendar = getSchemeCalendar(year, calendarView.getCurMonth(), calendarView.getCurDay(), 0xffee00ee, "签");
                calendarView.addSchemeDate(calendar);
                map.put(calendar.toString(), calendar);
                Toast.makeText(getContext(), R.string.checked, Toast.LENGTH_SHORT).show();
                item.setTitle(R.string.checked);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putLong("last_check_day", CalenderUtils.getNianYueRi());
                if (month != calendarView.getCurMonth()) {
                    editor.putInt("month", calendarView.getCurMonth());
                    editor.putString("days", String.valueOf(calendarView.getCurDay()));
                } else {
                    editor.putString("days", allCheckedDays + "," + calendarView.getCurDay());
                }
                editor.apply();
                MainActivity.isChecked = true;
                MainActivity mainActivity = (MainActivity) getActivity();
                PersonFragment personFragment = (PersonFragment) mainActivity.getFragmentPage(2);
                personFragment.setChecked();
            } else {
                Toast.makeText(getContext(), R.string.checked_hint, Toast.LENGTH_SHORT).show();
            }
            return true;
        });
    }

    private void initData() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        year = calendarView.getCurYear();
        month = sharedPreferences.getInt("month", -1);
        if (month != -1) {
            allCheckedDays = sharedPreferences.getString("days", "");
            String[] days = allCheckedDays.split(",");
            if (days.length > 0) {
                for (String day : days) {
                    map.put(getSchemeCalendar(year, month, Integer.parseInt(day), 0xffee00ee, "签").toString(),
                            getSchemeCalendar(year, month, Integer.parseInt(day), 0xffee00ee, "签"));
                }
                //此方法在巨大的数据量上不影响遍历性能，推荐使用
                calendarView.setSchemeDate(map);
            }
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

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        calendar.addScheme(new Calendar.Scheme());
//        calendar.addScheme(0xFF008800, "假");
//        calendar.addScheme(0xFF008800, "节");
        return calendar;
    }

    private void setMenuChecked() {
        toolbar.getMenu().getItem(0).setTitle(R.string.checked);
    }
}
