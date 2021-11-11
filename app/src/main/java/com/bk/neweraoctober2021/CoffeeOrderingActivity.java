package com.bk.neweraoctober2021;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CoffeeOrderingActivity extends AppCompatActivity {
    private Button btnAdd, btnMinus, btnMakeOrder;
    private TextView tvAmount;
    private CheckBox checkCream, checkCookie, checkChocolate;
    private int amount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_ordering);
        findViews();
        setListeners();
    }

    private void findViews(){
        btnAdd = findViewById(R.id.btnAdd);
        btnMinus = findViewById(R.id.btnMinus);
        btnMakeOrder = findViewById(R.id.btnMakeOrder);
        tvAmount = findViewById(R.id.tvAmount);
        checkCream = findViewById(R.id.checkCream);
        checkCookie = findViewById(R.id.checkCookie);
        checkChocolate = findViewById(R.id.checkChocolate);
    }

    private void setListeners(){
        btnAdd.setOnClickListener(view -> {
            amount++;
            tvAmount.setText(Integer.toString(amount));
        });

        btnMinus.setOnClickListener(view -> {
            if(amount > 1){
                amount--;
                tvAmount.setText(Integer.toString(amount));
            }
        });

        btnMakeOrder.setOnClickListener(view -> {
            int totalPrice;
            int basePrice = 5;

            if(checkCream.isChecked()){
                basePrice += 1;
            }

            if (checkCookie.isChecked()){
                basePrice += 2;
            }

            if(checkChocolate.isChecked()){
                basePrice += 3;
            }

            totalPrice = basePrice * amount;

            Toast.makeText(this, Integer.toString(totalPrice), Toast.LENGTH_SHORT).show();
        });
    }
}