package com.example.bookstore.Repository;


import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.bookstore.Retrofit.IBooksApi;
import com.example.bookstore.Models.BookModel;
import com.example.bookstore.Models.BookShelfModel;
import com.example.bookstore.Retrofit.RetrofitRequest;
import com.example.bookstore.Room.BookTable;
import com.example.bookstore.Room.BookTableDAO;
import com.example.bookstore.Room.FavoriteBooksDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bookstore.Utils.AppConstants.MAX_RESULTS_PER_CALL;


public class BooksRepository {
    //Retrofit
    private final IBooksApi booksApi;

    //Room
    private MutableLiveData<List<BookModel>> favoriteBooks;
    private BookTableDAO bookTableDAO;

    //Constructor
    public BooksRepository(Application application) {
        booksApi = RetrofitRequest.getRetrofitInstance().create(IBooksApi.class);
        favoriteBooks = new MutableLiveData<>();

        FavoriteBooksDatabase database =
                FavoriteBooksDatabase.getInstance(application.getApplicationContext());
        bookTableDAO = database.bookTableDAO();
    }

    //Get Favorite Books IDs from Local DataBase (room)
    public LiveData<List<BookTable>> getFavoriteBooksIdsFromLocal() {
        return bookTableDAO.getAllFavoriteBooks();
    }

    //Use Favorite Books IDs to get API information about them
    public LiveData<List<BookModel>> getAllFavoriteBooksFromAPI(List<BookTable> bookIds) {
        List<BookModel> finalFavoriteBooksList = new ArrayList<>();

        for (BookTable favBook : bookIds) {
            Call<BookModel> call = booksApi.getBookByID("v1/volumes/" + favBook.getBookID());

            call.enqueue(new Callback<BookModel>() {
                @Override
                public void onResponse(Call<BookModel> call, Response<BookModel> response) {
                    if (!response.isSuccessful()) {
                        return;
                    }
                    finalFavoriteBooksList.add(new BookModel(response.body().getId(),
                            response.body().getBookInfo(),
                            response.body().getBookSaleInfo()));

                    favoriteBooks.postValue(finalFavoriteBooksList);
                }

                @Override
                public void onFailure(Call<BookModel> call, Throwable t) {
                }
            });

        }

        return favoriteBooks;
    }


    //Checks if the book exists in the local database (room)
    public LiveData<BookTable> checkIfFavoriteBookExistsLocaly(String bookID) {
        return bookTableDAO.getFavoriteBookByID(bookID);
    }

    //Insert new Favorite book into local database (room)
    public void insertFavoriteBook(BookTable bookTable) {
        new InsertFavoriteBookAsyncTask(bookTableDAO).execute(bookTable);
    }

    //Remove Favorite
    public void removeFavoriteBookByID(String bookID) {
        new RemoveFavoriteBookAsyncTask(bookTableDAO).execute(bookID);
    }


    //Asynctask Class for inserting a favorite Book
    private static class InsertFavoriteBookAsyncTask extends AsyncTask<BookTable, Void, Void> {

        private BookTableDAO bookTableDAO;

        private InsertFavoriteBookAsyncTask(BookTableDAO bookTableDAO) {
            this.bookTableDAO = bookTableDAO;
        }

        @Override
        protected Void doInBackground(BookTable... bookTables) {
            bookTableDAO.insertFavorite(bookTables[0]);
            return null;
        }
    }

    //Asynctask Class for removing a favorite Book
    private static class RemoveFavoriteBookAsyncTask extends AsyncTask<String, Void, Void> {

        private BookTableDAO bookTableDAO;

        public RemoveFavoriteBookAsyncTask(BookTableDAO bookTableDAO) {
            this.bookTableDAO = bookTableDAO;
        }

        @Override
        protected Void doInBackground(String... strings) {
            bookTableDAO.removeFavoriteBookByID(strings[0]);
            return null;
        }
    }


}
