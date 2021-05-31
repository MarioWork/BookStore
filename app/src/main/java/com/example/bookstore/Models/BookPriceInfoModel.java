package com.example.bookstore.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookPriceInfoModel implements Parcelable {

    @SerializedName("amount")
    @Expose
    private String price;

    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;

    //Constructor
    public BookPriceInfoModel(String price, String currencyCode) {
        this.price = price;
        this.currencyCode = currencyCode;
    }


    //Getters
    public String getPrice() {
        return price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }


    //Parcelable
    protected BookPriceInfoModel(Parcel in) {
        price = in.readString();
        currencyCode = in.readString();
    }


    public static final Creator<BookPriceInfoModel> CREATOR = new Creator<BookPriceInfoModel>() {
        @Override
        public BookPriceInfoModel createFromParcel(Parcel in) {
            return new BookPriceInfoModel(in);
        }

        @Override
        public BookPriceInfoModel[] newArray(int size) {
            return new BookPriceInfoModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(price);
        dest.writeString(currencyCode);
    }
}
