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
    public BookModel(String id, BookInfoModel bookInfo, BookSaleInfoModel bookSaleInfo) {
        this.id = id;
        this.bookInfo = bookInfo;
        this.bookSaleInfo = bookSaleInfo;
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


    //compare two books contents
    public boolean compare(BookModel newBookModel) {
        if (!this.getId().equals(newBookModel.getId())) {
            return false;
        }

        if (!this.getBookInfo().getTitle().equals(newBookModel.getBookInfo().getTitle())) {
            return false;
        }

        if (!this.getBookInfo().getDescription().equals(newBookModel.getBookInfo().getDescription())) {
            return false;
        }


        if (!this.getBookInfo().getPublisher().equals(newBookModel.getBookInfo().getPublisher())) {
            return false;
        }


        if (!this.getBookInfo().getPublishDate().equals(newBookModel.getBookInfo().getPublishDate())) {
            return false;
        }

        if (!this.getBookInfo().getBookCover().getCoverImage().equals(newBookModel.getBookInfo().getBookCover().getCoverImage())) {
            return false;
        }


        if (!this.getBookInfo().getAuthors().equals(newBookModel.getBookInfo().getAuthors())) {
            return false;
        }


        if (!this.getBookSaleInfo().getPriceInfo().equals(newBookModel.getBookSaleInfo().getPriceInfo())) {
            return false;
        }

        if (!this.getBookSaleInfo().getBuyLink().equals(newBookModel.getBookSaleInfo().getBuyLink())) {
            return false;
        }

        if (!this.getBookSaleInfo().getForSale().equals(newBookModel.getBookSaleInfo().getForSale())) {
            return false;
        }

        return true;

    }

}
