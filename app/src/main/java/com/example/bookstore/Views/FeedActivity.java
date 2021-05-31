package com.example.bookstore.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookstore.Adapters.Book_Rv_Adapter;
import com.example.bookstore.Models.BookModel;

import com.example.bookstore.R;
import com.example.bookstore.Room.BookTable;
import com.example.bookstore.ViewModels.BooksViewModel;

import java.util.List;

import static com.example.bookstore.Utils.AppConstants.CATEGORY;


public class FeedActivity extends AppCompatActivity {

    Book_Rv_Adapter.IBookClicked listener;
    private BooksViewModel booksViewModel;
    private Book_Rv_Adapter adapter;

    //Widgets
    private RecyclerView bookRV;
    private TextView categoryTitle_tv;


    //Retrofit search start index
    private int startIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Book Library");
        categoryTitle_tv = findViewById(R.id.categoryTitle);
        categoryTitle_tv.setText("Mobile Development");

        //Get books when activity is created
        getAllBooksList();

        //Initialize the recyclerview
        onItemClick();
        setupBookRecyclerView();
    }

    private void getAllBooksList() {
        booksViewModel = new ViewModelProvider(this).get(BooksViewModel.class);
        booksViewModel.getRetrofitBooks(String.valueOf(startIndex)).observe(this, new Observer<List<BookModel>>() {
            @Override
            public void onChanged(List<BookModel> bookModels) {
                if (bookModels != null) {
                    adapter.addBookItems(bookModels); //Add the new items
                    //Update the RecyclerView
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }


    private void setupBookRecyclerView() {
        adapter = new Book_Rv_Adapter(listener);
        bookRV = findViewById(R.id.book_rv);
        bookRV.setAdapter(adapter);
    }

    //TODO
    private void onItemClick() {
        listener = new Book_Rv_Adapter.IBookClicked() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(FeedActivity.this, BookDetailsActivity.class);
                intent.putExtra("BookModel", adapter.getBook(position));
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


    //Variable to check if favorites or all books are selected
    //true = favorite list is showing
    //false = All books list is showing
    Boolean filter = false;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.filter_action:

                if (filter) { //The Favorites List is currently showing
                    //Swap to All Books and Change the Action Icon back to the Favorites Icon
                    filter = false;
                    item.setIcon(R.drawable.favorite_icon);
                    categoryTitle_tv.setText(CATEGORY);
                    getAllBooksList();
                } else { //All Books List is currently showing
                    //Swap to Favorite Books List and Change the Action Icon back to the All Books Icon
                    filter = true;
                    item.setIcon(R.drawable.all_books_list);
                    categoryTitle_tv.setText("Favorties");
                    filterListByFavorites();
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void filterListByFavorites() {
        booksViewModel.getFavoriteBooks().observe(this, new Observer<List<BookTable>>() {
            @Override
            public void onChanged(List<BookTable> bookTables) {
                if (bookTables != null && !bookTables.isEmpty()) {
                    Toast.makeText(FeedActivity.this, "FAV_BOOK: " + bookTables.get(0).getBookID(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}