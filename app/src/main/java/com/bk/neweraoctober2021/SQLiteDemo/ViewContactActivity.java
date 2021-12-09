package com.bk.neweraoctober2021.SQLiteDemo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bk.neweraoctober2021.R;

public class ViewContactActivity extends AppCompatActivity {
    private TextView tvName, tvNumber;
    private Button btnDelete, btnEdit;
    private Contact contact = new Contact();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        getContactFromExtra();
        findViews();
        setListeners();
    }

    private void getContactFromExtra(){
        int id = getIntent().getExtras().getInt("id");
        String name = getIntent().getExtras().getString("name");
        String number = getIntent().getExtras().getString("number");

        contact.setId(id);
        contact.setName(name);
        contact.setNumber(number);
    }

    private void findViews(){
        tvName = findViewById(R.id.tvName);
        tvNumber = findViewById(R.id.tvNumber);
        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.btnEdit);

        tvName.setText(contact.getName());
        tvNumber.setText(contact.getNumber());
    }

    private void setListeners(){

    }
}