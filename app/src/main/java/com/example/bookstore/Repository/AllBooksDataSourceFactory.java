package com.example.bookstore.Repository;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.bookstore.Models.BookModel;
import com.example.bookstore.Repository.AllBooksDataSource;

import org.jetbrains.annotations.NotNull;


public class AllBooksDataSourceFactory extends DataSource.Factory {

    @NotNull
    @Override
    public DataSource create() {
        return new AllBooksDataSource();
    }

}
