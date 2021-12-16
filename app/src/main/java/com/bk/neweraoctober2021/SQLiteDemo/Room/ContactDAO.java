package com.bk.neweraoctober2021.SQLiteDemo.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDAO {
    @Query("Select * from contacts")
    List<ContactPOJO> getAllContacts();

    @Insert
    void insertContact(ContactPOJO contactPOJO);

    @Update
    void updateContact(ContactPOJO contactPOJO);

    @Delete
    void deleteContact(ContactPOJO contactPOJO);
}
