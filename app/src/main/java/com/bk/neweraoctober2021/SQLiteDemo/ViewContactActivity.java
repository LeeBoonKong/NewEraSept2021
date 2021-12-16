package com.bk.neweraoctober2021.SQLiteDemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bk.neweraoctober2021.R;
import com.bk.neweraoctober2021.SQLiteDemo.Room.ContactDAO;
import com.bk.neweraoctober2021.SQLiteDemo.Room.ContactDatabase;
import com.bk.neweraoctober2021.SQLiteDemo.Room.ContactPOJO;

public class ViewContactActivity extends AppCompatActivity {
    private TextView tvName, tvNumber;
    private Button btnDelete, btnEdit;
    private ContactPOJO contact = new ContactPOJO();
    private ContactDBHelper helper;
    private ContactDAO contactDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
//        helper = new ContactDBHelper(this);
        contactDAO = ContactDatabase.getInstance(this).contactDAO();
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
        btnEdit.setOnClickListener( view -> {
            Intent intent = new Intent(ViewContactActivity.this, EditContactActivity.class);
            intent.putExtra("id", contact.getId());
            intent.putExtra("name", contact.getName());
            intent.putExtra("number", contact.getNumber());
            startActivity(intent);
            finish();
        });

        btnDelete.setOnClickListener( view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Delete Contact")
                    .setMessage("Are you sure to delete " + contact.getName() + " from your contact?")
                    .setPositiveButton("OK", (dialogInterface, i) -> {
                        deleteContact(contact);
                        Toast.makeText(this, "Contact Deleted", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .setNegativeButton("Cancel", null)
            .show();
        });
    }

    private void deleteContact(ContactPOJO contact){
        new Thread(() -> contactDAO.deleteContact(contact)).start();
    }
}