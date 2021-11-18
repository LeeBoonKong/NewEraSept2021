package com.bk.neweraoctober2021.ListviewExample;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.bk.neweraoctober2021.R;

import java.util.ArrayList;

public class ListviewExampleActivity extends AppCompatActivity {
    private ArrayList<String> fruitList = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_example);
        initFruitList();
        findViews();
        setListeners();
    }

    private void initFruitList(){
        fruitList.add("Mango");
        fruitList.add("Watermelon");
        fruitList.add("Durian");
        fruitList.add("Mangosteen");
        fruitList.add("Pineapple");
    }

    private void findViews(){
        listView = findViewById(R.id.listview);
    }

    private void setListeners(){
        FruitAdapter adapter = new FruitAdapter(this, fruitList);
        listView.setAdapter(adapter);
    }
}