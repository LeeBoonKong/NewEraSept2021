package com.bk.neweraoctober2021.FragmentExample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bk.neweraoctober2021.R;

import org.jetbrains.annotations.NotNull;

public class SettingsFragment extends Fragment {
    private Button btnClickMe;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setListeners();
    }

    private void findViews(View view){
        btnClickMe = view.findViewById(R.id.btnClickMe);
    }

    private void setListeners(){
        btnClickMe.setOnClickListener(view -> {
            Toast.makeText(getContext(), "Clicked on SettingsFragment", Toast.LENGTH_SHORT).show();
        });
    }
}