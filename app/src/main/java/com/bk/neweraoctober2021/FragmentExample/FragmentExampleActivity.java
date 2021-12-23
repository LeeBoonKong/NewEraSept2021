package com.bk.neweraoctober2021.FragmentExample;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.bk.neweraoctober2021.R;

public class FragmentExampleActivity extends AppCompatActivity {
    private LinearLayout linearHome, linearSettings, linearProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_example);
        findViews();
        setListeners();
        linearHome.performClick();
    }

    private void findViews(){
        linearHome = findViewById(R.id.linearHome);
        linearSettings = findViewById(R.id.linearSettings);
        linearProfile = findViewById(R.id.linearProfile);
    }

    private void setListeners(){
        linearHome.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, new HomeFragment())
                    .commit();

            linearHome.setBackgroundColor(getResources().getColor(R.color.teal_200));
            linearSettings.setBackgroundColor(getResources().getColor(R.color.white));
            linearProfile.setBackgroundColor(getResources().getColor(R.color.white));
        });

        linearSettings.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new SettingsFragment())
                    .commit();

            linearSettings.setBackgroundColor(getResources().getColor(R.color.teal_200));
            linearHome.setBackgroundColor(getResources().getColor(R.color.white));
            linearProfile.setBackgroundColor(getResources().getColor(R.color.white));
        });

        linearProfile.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new ProfileFragment())
                    .commit();

            linearProfile.setBackgroundColor(getResources().getColor(R.color.teal_200));
            linearHome.setBackgroundColor(getResources().getColor(R.color.white));
            linearSettings.setBackgroundColor(getResources().getColor(R.color.white));
        });
    }
}