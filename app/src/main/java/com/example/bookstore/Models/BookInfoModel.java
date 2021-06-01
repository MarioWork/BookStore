package com.example.bookstore.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookInfoModel implements Parcelable {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("authors")
    @Expose
    private String[] authors;

    @SerializedName("publisher")
    @Expose
    private String publisher;

    @SerializedName("publishedDate")
    @Expose
    private String publishDate;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("imageLinks")
    @Expose
    private BookCoverLinksModel bookCover;


    //Constructor
    public BookInfoModel(String title, String[] authors, String publisher, String publishDate, String description, BookCoverLinksModel bookCover) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.description = description;
        this.bookCover = new BookCoverLinksModel("");
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

    public String getPublisher() {
        return publisher;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getDescription() {
        return description;
    }


    //Parcelable
    protected BookInfoModel(Parcel in) {
        title = in.readString();
        authors = in.createStringArray();
        publisher = in.readString();
        publishDate = in.readString();
        description = in.readString();
        bookCover = in.readParcelable(BookCoverLinksModel.class.getClassLoader());
    }

    public static final Creator<BookInfoModel> CREATOR = new Creator<BookInfoModel>() {
        @Override
        public BookInfoModel createFromParcel(Parcel in) {
            return new BookInfoModel(in);
        }

        @Override
        public BookInfoModel[] newArray(int size) {
            return new BookInfoModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeStringArray(authors);
        dest.writeString(publisher);
        dest.writeString(publishDate);
        dest.writeString(description);
        dest.writeParcelable(bookCover, flags);
    }
}
