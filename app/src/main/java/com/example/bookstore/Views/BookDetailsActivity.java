package com.example.bookstore.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bookstore.Models.BookModel;
import com.example.bookstore.R;
import com.example.bookstore.Repository.BooksRepository;
import com.example.bookstore.Room.BookTable;
import com.example.bookstore.ViewModels.BooksViewModel;


public class BookDetailsActivity extends AppCompatActivity {
    private BookModel bookModel;

    //Widgets of ui
    private ImageView coverImage;
    private TextView title_tv;
    private TextView authors_tv;
    private TextView publishDate_tv;
    private TextView publisher_tv;
    private TextView description_tv;
    private Button forSale_btn;
    private ImageButton favorite_btn;

    private boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        setTitle("Book Details");

        //Receive BookModel from previous Activity
        bookModel = getIntent().getParcelableExtra("BookModel");

        initWidgets();
        verifyFavorite();

        //if a BookModel exists fill the widgets
        if (bookModel != null) {
            setupWidgetsWithData();
            verifyFavorite();
        } else {
            //if there is no BookModel return to parent page
            Intent intent = new Intent(BookDetailsActivity.this, FeedActivity.class);
            startActivity(intent);
        }
    }

    //Verifies if the book is a favorite or not and changes the icon accordingly
    private void verifyFavorite() {
        BooksViewModel booksViewModel = new BooksViewModel(getApplication());
        booksViewModel.getFavoriteBookByID(bookModel.getId()).observe(this, new Observer<BookTable>() {
            @Override
            public void onChanged(BookTable bookTable) {
                if (bookTable != null) {
                    isFavorite = true;
                    favorite_btn.setImageResource(R.drawable.favorite_filled);
                } else {
                    isFavorite = false;
                    favorite_btn.setImageResource(R.drawable.favorite_border);
                }
            }
        });
    }


    private void initWidgets() {
        coverImage = findViewById(R.id.cover_iv_details);
        title_tv = findViewById(R.id.title_tv_details);
        authors_tv = findViewById(R.id.authors_tv_details);
        publishDate_tv = findViewById(R.id.date_tv_details);
        publisher_tv = findViewById(R.id.publisher_tv_details);
        description_tv = findViewById(R.id.description_tv_details);
        forSale_btn = findViewById(R.id.buy_btn_details);
        forSale_btn.setOnClickListener(this::onClick);
        favorite_btn = findViewById(R.id.favorite_btn_details);
        favorite_btn.setOnClickListener(this::onClick);
    }

    //Initialize Verify and fill the widgets
    private void setupWidgetsWithData() {
        //Verify if there is ImageLink
        if (bookModel.getBookInfo().getBookCover().getCoverImage() != null) {
            Glide.with(coverImage.getContext())
                    .load(bookModel.getBookInfo().getBookCover().getCoverImage())
                    .placeholder(R.drawable.book_icon)
                    .into(coverImage);
        }

        //Verify if there is Title
        if (bookModel.getBookInfo().getTitle() != null) {
            title_tv.setText(bookModel.getBookInfo().getTitle());
        }

        //Verify if there is Authors
        if (bookModel.getBookInfo().getAuthors() != null) {
            //Main string for the authors
            String authors = "by " + bookModel.getBookInfo().getAuthors()[0];

            //join string array in one string
            for (int i = 1; i < bookModel.getBookInfo().getAuthors().length; i++) {
                authors += ", " + bookModel.getBookInfo().getAuthors()[i];
            }
            authors_tv.setText(authors);
        }

        //Verify if there is a Publish Date
        if (bookModel.getBookInfo().getPublishDate() != null) {
            publishDate_tv.setText(bookModel.getBookInfo().getPublishDate());
        }

        //Verify if there is a Publisher Name
        if (bookModel.getBookInfo().getPublisher() != null) {
            publisher_tv.setText(bookModel.getBookInfo().getPublisher());
        }

        //Verify if there is a Description
        if (bookModel.getBookInfo().getDescription() != null) {
            description_tv.setText(bookModel.getBookInfo().getDescription());
        }

        //Verify if the book is for sale or not
        if (bookModel.getBookSaleInfo().getForSale().equals("FOR_SALE")) {
            forSale_btn.setEnabled(true);

            if (bookModel.getBookSaleInfo().getPriceInfo().getCurrencyCode() != null
                    && bookModel.getBookSaleInfo().getPriceInfo().getPrice() != null) {

                forSale_btn.setText("Buy " + bookModel.getBookSaleInfo().getPriceInfo().getPrice()
                        + " " + bookModel.getBookSaleInfo().getPriceInfo().getCurrencyCode());
            }
        } else {
            forSale_btn.setEnabled(false);
            forSale_btn.setText("Not for sale");
        }
    }


    //Redirect user to the buy link
    private void openBuyLink() {
        Uri uri = Uri.parse(bookModel.getBookSaleInfo().getBuyLink());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.android.chrome");

        try {
            startActivity(intent);

        } catch (ActivityNotFoundException ex) { //If the chrome is not installed uses default app
            intent.setPackage(null);
            startActivity(intent);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy_btn_details:
                openBuyLink();
                break;
            case R.id.favorite_btn_details:
                favoriteButtonAction();
            default:
                break;
        }
    }

    //Favorite and Unfavorite
    private void favoriteButtonAction() {
        BooksViewModel booksViewModel = new BooksViewModel(getApplication());

        if (isFavorite == false) {
            booksViewModel.insertFavoriteBook(new BookTable(bookModel.getId()));
            isFavorite = true;
            favorite_btn.setImageResource(R.drawable.favorite_filled);
        } else {
            isFavorite = false;
            booksViewModel.removeFavoriteBook(bookModel.getId());
            favorite_btn.setImageResource(R.drawable.favorite_border);
        }
    }

}