package com.example.bookstore.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.bookstore.Models.BookModel;
import com.example.bookstore.Repository.BooksRepository;
import com.example.bookstore.Retrofit.AllBooksDataSourceFactory;
import com.example.bookstore.Room.BookTable;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.example.bookstore.Utils.AppConstants.MAX_RESULTS_PER_CALL;

public class BooksViewModel extends AndroidViewModel {

    private BooksRepository booksRepository;

    public BooksViewModel(@NonNull @NotNull Application application) {
        super(application);
        booksRepository = new BooksRepository(application);
    }


    public LiveData<PagedList<BookModel>> getRetrofitBooks() {
        AllBooksDataSourceFactory allBooksDataSourceFactory = new AllBooksDataSourceFactory();
        PagedList.Config config =
                (new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setPageSize(MAX_RESULTS_PER_CALL))
                        .build();

        return new LivePagedListBuilder(allBooksDataSourceFactory, config).build();
    }

    public LiveData<List<BookTable>> getFavoriteBooksIDS() {
        return booksRepository.getFavoriteBooksIdsFromLocal();
    }

    public LiveData<List<BookModel>> getFavoriteBooks(List<BookTable> favBooksIDs) {
        return booksRepository.getAllFavoriteBooksFromAPI(favBooksIDs);
    }

    public void insertFavoriteBook(BookTable bookTable) {
        booksRepository.insertFavoriteBook(bookTable);
    }

    public LiveData<BookTable> getFavoriteBookByID(String bookID) {
        return booksRepository.checkIfFavoriteBookExistsLocaly(bookID);
    }

    public void removeFavoriteBook(String bookID) {
        booksRepository.removeFavoriteBookByID(bookID);
    }
}
