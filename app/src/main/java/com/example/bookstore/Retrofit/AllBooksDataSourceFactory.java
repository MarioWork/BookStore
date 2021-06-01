package com.example.bookstore.Retrofit;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.bookstore.Models.BookModel;

import org.jetbrains.annotations.NotNull;


public class AllBooksDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, BookModel>> dataSourceList;

    @NotNull
    @Override
    public DataSource create() {
        dataSourceList = new MutableLiveData<>();
        AllBooksDataSource allBooksDataSource = new AllBooksDataSource();
        dataSourceList.postValue(allBooksDataSource);
        return allBooksDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, BookModel>> getDataSourceList() {
        return dataSourceList;
    }
}
