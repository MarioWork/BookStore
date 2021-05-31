package com.example.bookstore.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookCoverLinksModel implements Parcelable {

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


    //Parcelable
    protected BookCoverLinksModel(Parcel in) {
        coverImage = in.readString();
    }

    public static final Creator<BookCoverLinksModel> CREATOR = new Creator<BookCoverLinksModel>() {
        @Override
        public BookCoverLinksModel createFromParcel(Parcel in) {
            return new BookCoverLinksModel(in);
        }

        @Override
        public BookCoverLinksModel[] newArray(int size) {
            return new BookCoverLinksModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(coverImage);
    }
}
