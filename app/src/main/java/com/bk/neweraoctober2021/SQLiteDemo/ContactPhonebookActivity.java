package com.bk.neweraoctober2021.SQLiteDemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.bk.neweraoctober2021.R;
import com.bk.neweraoctober2021.SQLiteDemo.Room.ContactDatabase;
import com.bk.neweraoctober2021.SQLiteDemo.Room.ContactPOJO;

import java.util.ArrayList;
import java.util.List;

//Main Phonebook Activity
public class ContactPhonebookActivity extends AppCompatActivity {
    private ArrayList<ContactPOJO> contactList = new ArrayList<>();
    private ContactDBHelper helper;
    private ListView listView;
    private Button btnAddContact;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_phonebook);
//        helper = new ContactDBHelper(this);
        findViews();
        setListeners();
        getDataFromDatabase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.contactList.clear();
        getDataFromDatabase();
    }

    private void findViews(){
        btnAddContact = findViewById(R.id.btnAddContact);
        listView = findViewById(R.id.listview);
    }

    private void setListeners(){
        ContactAdapter adapter = new ContactAdapter(this, contactList);
        listView.setAdapter(adapter);

        this.adapter = (ContactAdapter) listView.getAdapter();

        btnAddContact.setOnClickListener( view -> {
            Intent i = new Intent(this, AddContactActivity.class);
            startActivity(i);
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ContactPOJO contact = contactList.get(i);
                Intent intent = new Intent(ContactPhonebookActivity.this, ViewContactActivity.class);
                intent.putExtra("id", contact.getId());
                intent.putExtra("name", contact.getName());
                intent.putExtra("number", contact.getNumber());
                startActivity(intent);
            }
        });
    }

    private void getDataFromDatabase(){
        new Thread(() -> {
            ContactDatabase database = ContactDatabase.getInstance(this);
            List<ContactPOJO> newList = database.contactDAO().getAllContacts();
            runOnUiThread( () -> {
                adapter.updateData(newList);
            });
        }).start();
    }
}