package com.bk.neweraoctober2021.SQLiteDemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

class ContactDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SampleApp.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE_NUMBER = "phoneNumber";

    public ContactDBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PHONE_NUMBER + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //CRUD Implement

    //Create Data
    public void insertContact(Contact contact){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, contact.getName());
        contentValues.put(COLUMN_PHONE_NUMBER, contact.getNumber());
        db.insert(TABLE_NAME, null, contentValues);
    }

    //Read Data
    public ArrayList<Contact> getAllContacts(){
        ArrayList<Contact> contactList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        result.moveToFirst();

        while (!result.isAfterLast()){
            Contact contact = new Contact();
            int id = result.getInt(result.getColumnIndex(COLUMN_ID));
            String name = result.getString(result.getColumnIndex(COLUMN_NAME));
            String number = result.getString(result.getColumnIndex(COLUMN_PHONE_NUMBER));

            contact.setId(id);
            contact.setName(name);
            contact.setNumber(number);

            contactList.add(contact);
            result.moveToNext();
        }

        result.close();
        return contactList;
    }

    //Update Data
    public void updateContact(Contact contact){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, contact.getName());
        contentValues.put(COLUMN_PHONE_NUMBER, contact.getNumber());

        String id = Integer.toString(contact.getId());

        db.update(TABLE_NAME, contentValues, "id=" + id, null);
    }

    //Delete Data
    public void deleteContact(int id){
        String stringId = Integer.toString(id);

        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, "id=" + stringId, null);
    }
}
