package com.petits_raids.northathleticfield.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.petits_raids.northathleticfield.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {

    private List<String> titleList;

    private List<Fragment> fragmentList;

    private Context mContext;

    public FragmentAdapter(Context context, FragmentManager manager, List<Fragment> fragmentList, List<String> titleList) {
        super(manager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
        this.fragmentList = fragmentList;
        this.titleList = titleList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    public View getTabView(int position){
        return LayoutInflater.from(mContext).inflate(R.layout.tab_view_item, null);
    }
}
