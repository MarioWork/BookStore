package com.example.bookstore.Retrofit;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.bookstore.Models.BookModel;
import com.example.bookstore.Models.BookShelfModel;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bookstore.Utils.AppConstants.MAX_RESULTS_PER_CALL;

public class AllBooksDataSource extends PageKeyedDataSource<Integer, BookModel> {
    private static final int first_page = 0;



    @Override
    public void loadInitial(@NonNull @NotNull PageKeyedDataSource.LoadInitialParams<Integer> params, @NonNull @NotNull PageKeyedDataSource.LoadInitialCallback<Integer, BookModel> callback) {

        Call<BookShelfModel> call =
                RetrofitRequest.getRetrofitInstance().create(IBooksApi.class)
                        .getAllBooks("mobile development", first_page * MAX_RESULTS_PER_CALL, MAX_RESULTS_PER_CALL);

        call.enqueue(new Callback<BookShelfModel>() {
            @Override
            public void onResponse(Call<BookShelfModel> call, Response<BookShelfModel> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                if (response != null) {
                    callback.onResult(response.body().getBookModelList(), null, first_page + 1);
                }
            }

            @Override
            public void onFailure(Call<BookShelfModel> call, Throwable t) {
            }
        });


    }

    @Override
    public void loadBefore(@NonNull @NotNull PageKeyedDataSource.LoadParams<Integer> params, @NonNull @NotNull PageKeyedDataSource.LoadCallback<Integer, BookModel> callback) {
        Call<BookShelfModel> call =
                RetrofitRequest.getRetrofitInstance().create(IBooksApi.class)
                        .getAllBooks("mobile development", params.key * MAX_RESULTS_PER_CALL, MAX_RESULTS_PER_CALL);


        call.enqueue(new Callback<BookShelfModel>() {
            @Override
            public void onResponse(Call<BookShelfModel> call, Response<BookShelfModel> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                if (response != null) {
                    Integer key = params.key > 1 ? params.key - 1 : null;
                    callback.onResult(response.body().getBookModelList(), key);
                }
            }

            @Override
            public void onFailure(Call<BookShelfModel> call, Throwable t) {
            }
        });
    }

    @Override
    public void loadAfter(@NonNull @NotNull PageKeyedDataSource.LoadParams<Integer> params, @NonNull @NotNull PageKeyedDataSource.LoadCallback<Integer, BookModel> callback) {
        Call<BookShelfModel> call =
                RetrofitRequest.getRetrofitInstance().create(IBooksApi.class)
                        .getAllBooks("mobile development", params.key * MAX_RESULTS_PER_CALL, MAX_RESULTS_PER_CALL);



        call.enqueue(new Callback<BookShelfModel>() {
            @Override
            public void onResponse(Call<BookShelfModel> call, Response<BookShelfModel> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                if (response != null) {
                    Integer key = params.requestedLoadSize > params.key ? params.key + 1 : null;
                    Log.d("Teste","size: "+params.requestedLoadSize);
                    callback.onResult(response.body().getBookModelList(), key);
                }
            }

            @Override
            public void onFailure(Call<BookShelfModel> call, Throwable t) {
            }
        });
    }
}
