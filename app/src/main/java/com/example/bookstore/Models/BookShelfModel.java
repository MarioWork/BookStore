package com.example.bookstore.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookShelfModel {

    @SerializedName("items")
    @Expose
    private List<BookModel> bookModelList;


    //Constructor
    public BookShelfModel(List<BookModel> bookModelList) {
        this.bookModelList = bookModelList;
    }

    //Getters
    public List<BookModel> getBookModelList() {
        return bookModelList;
    }
}
