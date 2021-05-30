package com.example.bookstore.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookModel {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("volumeInfo")
    @Expose
    private BookInfoModel bookInfo;


    //Constructor
    public BookModel(String id, BookInfoModel bookInfo) {
        this.id = id;
        this.bookInfo = bookInfo;
    }


    //Getters

    public String getId() {
        return id;
    }

    public BookInfoModel getBookInfo() {
        return bookInfo;
    }


}
