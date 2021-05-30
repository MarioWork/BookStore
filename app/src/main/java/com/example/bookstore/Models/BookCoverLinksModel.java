package com.example.bookstore.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookCoverLinksModel {

    @SerializedName("thumbnail")
    @Expose
    private String coverImage = "https://i.imgur.com/eFuERif.jpeg";


    //Constructor
    public BookCoverLinksModel(String coverImage) {
        this.coverImage = coverImage;
    }

    //Getters
    public String getCoverImage() {
        return coverImage;
    }
}
