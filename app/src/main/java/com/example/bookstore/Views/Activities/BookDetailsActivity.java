package com.example.bookstore.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.ActivityNotFoundException;
import android.content.Intent;
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
import com.example.bookstore.Room.BookTable;
import com.example.bookstore.ViewModels.BooksViewModel;
import com.example.bookstore.databinding.ActivityBookDetailsBinding;


public class BookDetailsActivity extends AppCompatActivity {
    private BookModel bookModel;
    ActivityBookDetailsBinding binding;
    private boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBookDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setTitle("Book Details");

        //Receive BookModel from previous Activity
        bookModel = getIntent().getParcelableExtra("BookModel");


        binding.buyBtnDetails.setOnClickListener(this::onClick);
        binding.favoriteBtnDetails.setOnClickListener(this::onClick);


        verifyFavorite();

        //if a BookModel exists fill the widgets
        if (bookModel != null) {
            setupWidgetsWithData();
            verifyFavorite();
        } else {
            //if there is no BookModel return to parent page
            Intent intent = new Intent(BookDetailsActivity.this, MainActivity.class);
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
                    binding.favoriteBtnDetails.setImageResource(R.drawable.favorite_filled);
                } else {
                    isFavorite = false;
                    binding.favoriteBtnDetails.setImageResource(R.drawable.favorite_border);
                }
            }
        });
    }


    //Initialize Verify and fill the widgets
    private void setupWidgetsWithData() {
        //Verify if there is ImageLink
        if (bookModel.getBookInfo().getBookCover() != null) {
            Glide.with(binding.coverIvDetails.getContext())
                    .load(bookModel.getBookInfo().getBookCover().getCoverImage())
                    .placeholder(R.drawable.book_icon)
                    .into(binding.coverIvDetails);
        }

        //Verify if there is Title
        if (bookModel.getBookInfo().getTitle() != null) {
            binding.titleTvDetails.setText(bookModel.getBookInfo().getTitle());
        }

        //Verify if there is Authors
        if (bookModel.getBookInfo().getAuthors() != null) {
            //Main string for the authors
            String authors = "by " + bookModel.getBookInfo().getAuthors()[0];

            //join string array in one string
            for (int i = 1; i < bookModel.getBookInfo().getAuthors().length; i++) {
                authors += ", " + bookModel.getBookInfo().getAuthors()[i];
            }
            binding.authorsTvDetails.setText(authors);
        }

        //Verify if there is a Publish Date
        if (bookModel.getBookInfo().getPublishDate() != null) {
            binding.dateTvDetails.setText(bookModel.getBookInfo().getPublishDate());
        }

        //Verify if there is a Publisher Name
        if (bookModel.getBookInfo().getPublisher() != null) {
            binding.publisherTvDetails.setText(bookModel.getBookInfo().getPublisher());
        }

        //Verify if there is a Description
        if (bookModel.getBookInfo().getDescription() != null) {
            binding.descriptionTvDetails.setText(bookModel.getBookInfo().getDescription());
        }

        //Verify if the book is for sale or not
        if (bookModel.getBookSaleInfo().getForSale().equals("FOR_SALE")) {
            binding.buyBtnDetails.setEnabled(true);

            if (bookModel.getBookSaleInfo().getPriceInfo().getCurrencyCode() != null
                    && bookModel.getBookSaleInfo().getPriceInfo().getPrice() != null) {
                String buttonText;
                if (bookModel.getBookSaleInfo().getPriceInfo().getCurrencyCode().equals("EUR")) {
                    buttonText = "Buy " + bookModel.getBookSaleInfo().getPriceInfo().getPrice().replace(".", ",")
                            + "€";
                    binding.buyBtnDetails.setText(buttonText);
                } else {
                    buttonText = "Buy " + bookModel.getBookSaleInfo().getPriceInfo().getPrice().replace(".", ",")
                            + bookModel.getBookSaleInfo().getPriceInfo().getCurrencyCode();
                    binding.buyBtnDetails.setText(buttonText);
                }

            }
        } else {
            binding.buyBtnDetails.setEnabled(false);
            binding.buyBtnDetails.setText("Not for sale");
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
            binding.favoriteBtnDetails.setImageResource(R.drawable.favorite_filled);
        } else {
            isFavorite = false;
            booksViewModel.removeFavoriteBook(bookModel.getId());
            binding.favoriteBtnDetails.setImageResource(R.drawable.favorite_border);
        }
    }

}