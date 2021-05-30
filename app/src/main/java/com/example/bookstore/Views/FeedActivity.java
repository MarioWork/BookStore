package com.example.bookstore.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.AbsListView;
import android.widget.Toast;

import com.example.bookstore.Adapters.Book_Rv_Adapter;
import com.example.bookstore.Models.BookModel;

import com.example.bookstore.R;
import com.example.bookstore.ViewModels.BooksViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.example.bookstore.Utils.AppConstants.MAX_RESULTS;


public class FeedActivity extends AppCompatActivity {

    Book_Rv_Adapter.IBookClicked listener;

    private RecyclerView bookRV;
    private BooksViewModel retrofitBooksViewModel;
    private List<BookModel> bookList;
    private Book_Rv_Adapter adapter;

    //Retrofit search start index
    private int startIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Book Library");

        //TODO
        Intent intent = new Intent(FeedActivity.this, BookDetailsActivity.class);
        startActivity(intent);


        bookList = new ArrayList<>();

        //Get books when activity is created
        getBooksList();

        //Initialize the recyclerview
        onItemClick();
        setupBookRecyclerView();

    }

    private void getBooksList() {
        retrofitBooksViewModel = new ViewModelProvider(this).get(BooksViewModel.class);
        retrofitBooksViewModel.getRetrofitBooks(String.valueOf(startIndex)).observe(this, new Observer<List<BookModel>>() {
            @Override
            public void onChanged(List<BookModel> bookModels) {
                if (bookModels != null) {
                    bookList.addAll(bookModels); //Add the new items

                    //Update the RecyclerView
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }


    private void setupBookRecyclerView() {
        adapter = new Book_Rv_Adapter(bookList, listener);
        bookRV = findViewById(R.id.book_rv);
        bookRV.setAdapter(adapter);
    }

    //TODO
    private void onItemClick() {
        listener = new Book_Rv_Adapter.IBookClicked() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(FeedActivity.this, BookDetailsActivity.class);
               // intent.putExtra("BookModel", (Parcelable) adapter.getItem(position));
                startActivity(intent);
            }
        };
    }

    //Setup Actionbar Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.feed_activity_menu, menu);
        return true;
    }
}