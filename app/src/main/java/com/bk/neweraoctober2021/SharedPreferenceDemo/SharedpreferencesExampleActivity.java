package com.bk.neweraoctober2021.SharedPreferenceDemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bk.neweraoctober2021.R;

public class SharedpreferencesExampleActivity extends AppCompatActivity {
    private static final String PREF_NAME = "MyPref";
    private static final String KEY_MY_STRING = "MyString";

    private EditText edtSave;
    private TextView tvDisplay;
    private Button btnSave, btnDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpreferences_example);
        findViews();
        setListeners();
    }

    private void findViews(){
        edtSave = findViewById(R.id.edtSave);
        tvDisplay = findViewById(R.id.tvDisplay);
        btnSave = findViewById(R.id.btnSave);
        btnDisplay = findViewById(R.id.btnDisplay);
    }

    private void setListeners(){
        btnSave.setOnClickListener(view -> {
            String stringToSave = edtSave.getText().toString();

            SharedPreferences pref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString(KEY_MY_STRING, stringToSave);
            editor.apply();
            Toast.makeText(this, "String Saved", Toast.LENGTH_SHORT).show();
        });

        btnDisplay.setOnClickListener(view -> {
            SharedPreferences pref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            String savedString = pref.getString(KEY_MY_STRING, "");
            tvDisplay.setText(savedString);
        });
    }
}