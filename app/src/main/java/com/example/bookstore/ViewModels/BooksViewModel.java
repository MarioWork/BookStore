package com.example.bookstore.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.bookstore.Models.BookModel;
import com.example.bookstore.Repository.BooksRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BooksViewModel extends AndroidViewModel {
    private MutableLiveData<List<BookModel>> booklist;
    private BooksRepository booksRepository;

    public BooksViewModel(@NonNull @NotNull Application application) {
        super(application);
        booksRepository = new BooksRepository(application);
    }

    public MutableLiveData<List<BookModel>> getRetrofitBooks(String startIndex) {
        booklist = booksRepository.getApiBooksList(startIndex);
        return booklist;
    }


}
