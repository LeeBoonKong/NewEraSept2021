package com.bk.neweraoctober2021.SQLiteDemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.bk.neweraoctober2021.R;

import java.util.ArrayList;

//Main Phonebook Activity
public class ContactPhonebookActivity extends AppCompatActivity {
    private ArrayList<Contact> contactList;
    private ContactDBHelper helper;
    private ListView listView;
    private Button btnAddContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_phonebook);
        helper = new ContactDBHelper(this);
        getDataFromDatabase();
        findViews();
        setListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ContactAdapter adapter = (ContactAdapter) listView.getAdapter();
        this.contactList.clear();
        getDataFromDatabase();
        adapter.updateData(contactList);
    }

    private void findViews(){
        btnAddContact = findViewById(R.id.btnAddContact);
        listView = findViewById(R.id.listview);
    }

    private void setListeners(){
        ContactAdapter adapter = new ContactAdapter(this, contactList);
        listView.setAdapter(adapter);

        btnAddContact.setOnClickListener( view -> {
            Intent i = new Intent(this, AddContactActivity.class);
            startActivity(i);
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact contact = contactList.get(i);
                Intent intent = new Intent(ContactPhonebookActivity.this, ViewContactActivity.class);
                intent.putExtra("id", contact.getId());
                intent.putExtra("name", contact.getName());
                intent.putExtra("number", contact.getNumber());
                startActivity(intent);
            }
        });
    }

    private void getDataFromDatabase(){
        contactList = helper.getAllContacts();
    }
}