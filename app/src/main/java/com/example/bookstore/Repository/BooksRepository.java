package com.example.bookstore.Repository;


import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;


import com.example.bookstore.Retrofit.IBooksApi;
import com.example.bookstore.Models.BookModel;
import com.example.bookstore.Models.BookShelfModel;
import com.example.bookstore.Retrofit.RetrofitRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bookstore.Utils.AppConstants.MAX_RESULTS;


public class BooksRepository {
    private MutableLiveData<List<BookModel>> bookList;
    private final IBooksApi booksApi;
    private Application application;

    //Constructor
    public BooksRepository(Application application) {
        booksApi = RetrofitRequest.getRetrofitInstance().create(IBooksApi.class);
        bookList = new MutableLiveData<>();
        this.application = application;
    }

    //Receive books from API
    public MutableLiveData<List<BookModel>> getApiBooksList(String startIndex) {

        Call<BookShelfModel> call = booksApi.getBooks("v1/volumes?q=mobile%20development&maxResults=" + MAX_RESULTS + "&startIndex=" + startIndex);

        call.enqueue(new Callback<BookShelfModel>() {
            @Override
            public void onResponse(Call<BookShelfModel> call, Response<BookShelfModel> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                bookList.postValue(response.body().getBookModelList());
            }

            @Override
            public void onFailure(Call<BookShelfModel> call, Throwable t) {
                bookList.postValue(null);
                Toast.makeText(application.getApplicationContext(), "Something went wrong...", Toast.LENGTH_LONG).show();
            }
        });

        return bookList;
    }

}
