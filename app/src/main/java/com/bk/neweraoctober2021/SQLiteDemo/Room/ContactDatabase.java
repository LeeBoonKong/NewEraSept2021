package com.bk.neweraoctober2021.SQLiteDemo.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database( entities = ContactPOJO.class, exportSchema = false, version = 1)
public abstract class ContactDatabase extends RoomDatabase {
    private static final String databaseName = "SampleApp.db";
    private static ContactDatabase instance;

    public static synchronized ContactDatabase getInstance(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), ContactDatabase.class,
                    databaseName)
            .fallbackToDestructiveMigration()
            .build();

        }

        return instance;
    }

    public abstract ContactDAO contactDAO();
}
