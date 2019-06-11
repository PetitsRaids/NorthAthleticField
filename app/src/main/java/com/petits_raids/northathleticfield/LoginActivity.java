package com.petits_raids.northathleticfield;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = getSharedPreferences("shared_login", MODE_PRIVATE);
        EditText usernameET = findViewById(R.id.login_name);
        EditText passwordET = findViewById(R.id.password);
        CheckBox checkBox = findViewById(R.id.whither_remember);
        Button loginBtn = findViewById(R.id.login_btn);

        usernameET.setText(preferences.getString("username", ""));
        passwordET.setText(preferences.getString("password", ""));
        checkBox.setChecked(preferences.getBoolean("remember", false));
        loginBtn.setOnClickListener(v -> {
            String username = usernameET.getText().toString();
            String password = passwordET.getText().toString();
            if (password.equals(preferences.getString("password", null))) {
                if (checkBox.isChecked()) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.putBoolean("remember", true);
                    editor.apply();
                }
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, R.string.login_fail_mismatch, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
