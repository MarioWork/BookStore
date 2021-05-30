package com.example.bookstore.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookInfoModel {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("authors")
    private String[]authors;

    @SerializedName("imageLinks")
    @Expose
    private BookCoverLinksModel bookCover;


    //Constructor
    public BookInfoModel(String title, BookCoverLinksModel bookCover,String[] authors) {
        this.title = title;
        this.bookCover = bookCover;
        this.authors = authors;
    }

    //Getters
    public String getTitle() {
        return title;
    }

    public BookCoverLinksModel getBookCover() {
        return bookCover;
    }

    public String[] getAuthors() {
        return authors;
    }
}
