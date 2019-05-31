package com.petits_raids.northathleticfield.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.petits_raids.northathleticfield.R;
import com.petits_raids.northathleticfield.utils.Logger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class SearchFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.salary_view_layout, container, false);
        Toolbar toolbar = view.findViewById(R.id.search_toolbar);

        setHasOptionsMenu(true);
        toolbar.inflateMenu(R.menu.search_menu);
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.search){
                Toast.makeText(getContext(), "hhhhhhhhhhhh", Toast.LENGTH_SHORT).show();
            }
            return true;
        });
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
