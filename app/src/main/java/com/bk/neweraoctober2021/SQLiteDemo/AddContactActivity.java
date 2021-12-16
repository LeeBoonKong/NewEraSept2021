package com.bk.neweraoctober2021.SQLiteDemo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bk.neweraoctober2021.R;
import com.bk.neweraoctober2021.SQLiteDemo.Room.ContactDatabase;
import com.bk.neweraoctober2021.SQLiteDemo.Room.ContactPOJO;

public class AddContactActivity extends AppCompatActivity {
    private ContactDBHelper helper;
    private EditText edtName, edtNumber;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
//        helper = new ContactDBHelper(this);
        findViews();
        setListeners();
    }

    private void findViews(){
        edtName = findViewById(R.id.edtName);
        edtNumber = findViewById(R.id.edtNumber);
        btnSave = findViewById(R.id.btnSave);
    }

    private void setListeners(){
        btnSave.setOnClickListener(view -> {
            String name = edtName.getText().toString();
            String number = edtNumber.getText().toString();

            if(name.isEmpty() || number.isEmpty()){
                Toast.makeText(this, "Name or Number cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                ContactPOJO contact = new ContactPOJO(name, number);
                addContact(contact);
            }
        });
    }

    private void addContact(ContactPOJO contact){
        new Thread(() -> {
            ContactDatabase.getInstance(this).contactDAO().insertContact(contact);
            runOnUiThread(this::finish);
        }).start();
    }

}