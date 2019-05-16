package com.petits_raids.northathleticfield.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.petits_raids.northathleticfield.R;
import com.petits_raids.northathleticfield.utils.Logger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PersonFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.d("onCreateView:" + "PersonFragment");
        return inflater.inflate(R.layout.person_view_layout, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.d("onDestroyView:" + "PersonFragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.d("onDestroy:" + "PersonFragment");
    }
}
