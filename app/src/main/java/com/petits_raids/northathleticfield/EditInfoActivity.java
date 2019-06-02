package com.petits_raids.northathleticfield;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class EditInfoActivity extends AppCompatActivity {

    private EditText phoneEt, emailEt, deptEt, qqEt, hobbyEt;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        Toolbar toolbar = findViewById(R.id.edit_info_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        phoneEt = findViewById(R.id.phone_et);
        emailEt = findViewById(R.id.email_et);
        deptEt = findViewById(R.id.dept_et);
        qqEt = findViewById(R.id.qq_et);
        hobbyEt = findViewById(R.id.hobby_et);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        phoneEt.setText(preferences.getString("phone", ""));
        emailEt.setText(preferences.getString("email", ""));
        deptEt.setText(preferences.getString("dept", ""));
        qqEt.setText(preferences.getString("qq", ""));
        hobbyEt.setText(preferences.getString("hobby", ""));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_info_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.save_info:
                saveInfo();
                finish();
                break;
        }
        return false;
    }

    private void saveInfo() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("phone", phoneEt.getText().toString());
        editor.putString("email", emailEt.getText().toString());
        editor.putString("dept", deptEt.getText().toString());
        editor.putString("qq", qqEt.getText().toString());
        editor.putString("hobby", hobbyEt.getText().toString());
        editor.apply();
    }
}
