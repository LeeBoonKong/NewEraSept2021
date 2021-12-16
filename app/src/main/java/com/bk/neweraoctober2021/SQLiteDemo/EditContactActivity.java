package com.bk.neweraoctober2021.SQLiteDemo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bk.neweraoctober2021.R;

public class EditContactActivity extends AppCompatActivity {
    private EditText edtName, edtNumber;
    private Button btnSave;
    private Contact contact = new Contact();
    private ContactDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        helper = new ContactDBHelper(this);
        getDataFromExtra();
        findViews();
        setListeners();
    }

    private void getDataFromExtra(){
        int id = getIntent().getExtras().getInt("id");
        String name = getIntent().getExtras().getString("name");
        String number = getIntent().getExtras().getString("number");

        contact.setId(id);;
        contact.setName(name);
        contact.setNumber(number);
    }

    private void findViews(){
        edtName = findViewById(R.id.edtName);
        edtNumber = findViewById(R.id.edtNumber);
        btnSave = findViewById(R.id.btnSave);

        edtName.setText(contact.getName());
        edtNumber.setText(contact.getNumber());
    }

    private void setListeners(){
        btnSave.setOnClickListener(view -> {
            contact.setName(edtName.getText().toString());
            contact.setNumber(edtNumber.getText().toString());

            helper.updateContact(contact);
            Toast.makeText(this, "Contact Updated", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}