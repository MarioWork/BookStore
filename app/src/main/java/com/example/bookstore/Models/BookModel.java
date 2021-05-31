package com.example.bookstore.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookModel implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("volumeInfo")
    @Expose
    private BookInfoModel bookInfo;

    @SerializedName("saleInfo")
    @Expose
    private BookSaleInfoModel bookSaleInfo;

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

    public BookSaleInfoModel getBookSaleInfo() {
        return bookSaleInfo;
    }


    //Parcelable
    protected BookModel(Parcel in) {
        id = in.readString();
        bookInfo = in.readParcelable(BookInfoModel.class.getClassLoader());
        bookSaleInfo = in.readParcelable(BookSaleInfoModel.class.getClassLoader());
    }

    public static final Creator<BookModel> CREATOR = new Creator<BookModel>() {
        @Override
        public BookModel createFromParcel(Parcel in) {
            return new BookModel(in);
        }

        @Override
        public BookModel[] newArray(int size) {
            return new BookModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeParcelable(bookInfo, flags);
        dest.writeParcelable(bookSaleInfo, flags);
    }


}
