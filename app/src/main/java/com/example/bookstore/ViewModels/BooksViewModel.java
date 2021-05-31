package com.example.bookstore.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bookstore.Models.BookModel;
import com.example.bookstore.Repository.BooksRepository;
import com.example.bookstore.Room.BookTable;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BooksViewModel extends AndroidViewModel {
    private MutableLiveData<List<BookModel>> bookList;
    private LiveData<List<BookTable>> favoriteBooks;
    private BooksRepository booksRepository;

    public BooksViewModel(@NonNull @NotNull Application application) {
        super(application);
        booksRepository = new BooksRepository(application);
    }

    public MutableLiveData<List<BookModel>> getRetrofitBooks(String startIndex) {
        bookList = booksRepository.getApiBooksList(startIndex);
        return bookList;
    }

    public LiveData<List<BookTable>> getFavoriteBooksIDS() {
        return booksRepository.getFavoriteBooksIDS();
    }

    public LiveData<List<BookModel>> getFavoriteBooks(List<BookTable> favBooksIDs){
        return booksRepository.getAllFavoriteBooks(favBooksIDs);
    }

    public void insertFavoriteBook(BookTable bookTable) {
        booksRepository.insertFavoriteBook(bookTable);
    }

    public LiveData<BookTable> getFavoriteBookByID(String bookID) {
        return booksRepository.checkIfFavoriteBookExists(bookID);
    }

    public void removeFavoriteBook(String bookID) {
        booksRepository.removeFavoriteBookByID(bookID);
    }
}
