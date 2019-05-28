package com.petits_raids.northathleticfield;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText usernameET = findViewById(R.id.login_name);
        EditText passwordET = findViewById(R.id.password);
        Button loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(v -> {
            String username = usernameET.getText().toString();
            String password = passwordET.getText().toString();
            if(true){
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }else{
                Toast.makeText(this, R.string.login_fail_mismatch, Toast.LENGTH_SHORT).show();
            }

        });
    }
}
