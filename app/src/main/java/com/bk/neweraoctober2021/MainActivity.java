package com.bk.neweraoctober2021;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private String email="october2021@email.com";
    private String password="password123";
    private EditText edtEmail, edtPassword;
    private Button btnLogin, btnSignup, btnWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListeners();
    }

    private void findViews(){
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);
        btnWebsite = findViewById(R.id.btnWebsite);
    }

    private void setListeners(){
        btnLogin.setOnClickListener(view -> {
            String inputEmail = edtEmail.getText().toString();
            String inputPassword = edtPassword.getText().toString();

            if(inputEmail.equals(email)){
                if(inputPassword.equals(password)){
                    Intent intent = new Intent(MainActivity.this, CoffeeOrderingActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Wrong Email", Toast.LENGTH_SHORT).show();
            }


        });

        btnWebsite.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.android.com"));
            startActivity(i);
        });
    }
}