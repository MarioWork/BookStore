package com.example.bookstore.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookSaleInfoModel implements Parcelable {

    @SerializedName("saleability")
    @Expose
    private String forSale;

    @SerializedName("buyLink")
    @Expose
    private String buyLink;

    @SerializedName("retailPrice")
    @Expose
    private BookPriceInfoModel priceInfo;

    //Constructor
    public BookSaleInfoModel(String forSale, String buyLink) {
        this.forSale = forSale;
        this.buyLink = buyLink;
    }
    
    //Getters
    public String getForSale() {
        return forSale;
    }

    public String getBuyLink() {
        return buyLink;
    }

    public BookPriceInfoModel getPriceInfo() {
        return priceInfo;
    }

    //Parcelable
    protected BookSaleInfoModel(Parcel in) {
        forSale = in.readString();
        buyLink = in.readString();
        priceInfo = in.readParcelable(BookPriceInfoModel.class.getClassLoader());
    }

    public static final Creator<BookSaleInfoModel> CREATOR = new Creator<BookSaleInfoModel>() {
        @Override
        public BookSaleInfoModel createFromParcel(Parcel in) {
            return new BookSaleInfoModel(in);
        }

        @Override
        public BookSaleInfoModel[] newArray(int size) {
            return new BookSaleInfoModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(forSale);
        dest.writeString(buyLink);
        dest.writeParcelable(priceInfo, flags);
    }


}
