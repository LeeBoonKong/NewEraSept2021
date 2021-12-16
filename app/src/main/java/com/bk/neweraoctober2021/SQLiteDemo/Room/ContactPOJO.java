package com.bk.neweraoctober2021.SQLiteDemo.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity( tableName = "contacts")
public class ContactPOJO {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo( name = "name")
    private String name;
    @ColumnInfo( name = "phoneNumber")
    private String number;

    public ContactPOJO(int id, String name, String number){
        this.id = id;
        this.name = name;
        this.number = number;
    }

    @Ignore
    public ContactPOJO(){
    }

    @Ignore
    public ContactPOJO(String name, String number){
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }
}
