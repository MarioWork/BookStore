package com.example.bookstore.Retrofit;

import com.example.bookstore.Models.BookShelfModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface IBooksApi {

    @GET
    Call<BookShelfModel> getBooks(@Url String url);
}
