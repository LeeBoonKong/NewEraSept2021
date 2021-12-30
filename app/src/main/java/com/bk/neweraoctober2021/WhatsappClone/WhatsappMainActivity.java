package com.bk.neweraoctober2021.WhatsappClone;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bk.neweraoctober2021.R;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

public class WhatsappMainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp_main);
        findViews();
        setListeners();
        setupTabLayout();
    }

    private void findViews(){
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
    }

    private void setListeners(){

    }

    private void setupTabLayout(){
        WhatsappPagerAdapter adapter = new WhatsappPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    private class WhatsappPagerAdapter extends FragmentPagerAdapter {

        public WhatsappPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @NonNull
        @NotNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch (position){
                case 0:
                    fragment = new ChatFragment();
                    break;
                case 1:
                    fragment = new CallsFragment();
                    break;
                case 2:
                    fragment = new StatusFragment();
                    break;
                default:
                    fragment = new ChatFragment();
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @org.jetbrains.annotations.Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "CHAT";
                case 1:
                    return "CALLS";
                case 2:
                    return "STATUS";
                default:
                    return "CHAT";
            }
        }
    }
}