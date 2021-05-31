package com.example.bookstore.Room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "FavoritesTable")
public class BookTable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String bookID;



    //Constructor
    public BookTable( String bookID) {
        this.bookID = bookID;
    }

    //Getters & Setters
    public int getId() {
        return id;
    }

    public String getBookID() {
        return bookID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }
}
