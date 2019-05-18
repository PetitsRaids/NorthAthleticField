package com.petits_raids.northathleticfield.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.petits_raids.northathleticfield.R;
import com.petits_raids.northathleticfield.utils.Logger;

public class TabView extends LinearLayout {

    private ImageView imageSelected, imageBackground;

    private TextView tabTitle;

    TabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.tab_view_item, this);
        Logger.d("is it add view");
        imageSelected = findViewById(R.id.tab_selected);
        imageBackground = findViewById(R.id.tab_background);
        tabTitle = findViewById(R.id.tab_title);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabView);
        setImageSelected(typedArray.getResourceId(R.styleable.TabView_tab_selected, R.mipmap.ic_launcher));
        setImageBackground(typedArray.getResourceId(R.styleable.TabView_tab_background, R.mipmap.ic_launcher_round));
        setTabTitle(typedArray.getString(R.styleable.TabView_tab_title));
        typedArray.recycle();
    }

    public void setImageSelected(int selectedId) {
        imageSelected.setImageResource(selectedId);
    }

    public void setImageBackground(int backgroundId) {
        imageBackground.setImageResource(backgroundId);
    }

    public void setTabTitle(String title) {
        tabTitle.setText(title);
    }

}
