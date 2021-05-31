package com.example.bookstore.Views;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.Adapters.Book_Rv_Adapter;
import com.example.bookstore.Models.BookModel;
import com.example.bookstore.R;
import com.example.bookstore.ViewModels.BooksViewModel;

import static com.example.bookstore.Utils.AppConstants.CATEGORY;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class FeedFragment extends Fragment {

    private Book_Rv_Adapter.IBookClicked listener;
    private BooksViewModel booksViewModel;
    private Book_Rv_Adapter adapter;

    //Widgets
    private RecyclerView bookRV;
    private TextView categoryTitle_tv;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        categoryTitle_tv = getView().findViewById(R.id.categoryTitle_tv_feedFrag);
        categoryTitle_tv.setText(CATEGORY);

        //Setup RecyclerView
        onItemClick();
        setupBookRecyclerView();


        getAllBooksList();
    }
    private void setupBookRecyclerView() {
        adapter = new Book_Rv_Adapter(listener);
        bookRV = getView().findViewById(R.id.book_rv_feed);
        bookRV.setAdapter(adapter);
    }

    private void getAllBooksList() {
        booksViewModel = new ViewModelProvider(this).get(BooksViewModel.class);
        booksViewModel.getRetrofitBooks(String.valueOf(0)).observe(getActivity(), new Observer<List<BookModel>>() {
            @Override
            public void onChanged(List<BookModel> bookModels) {
                if (bookModels != null && !bookModels.isEmpty()) {
                    adapter.clearAndAddItems(bookModels); //Add the new items
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void onItemClick() {
        listener = new Book_Rv_Adapter.IBookClicked() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), BookDetailsActivity.class);
                intent.putExtra("BookModel", adapter.getBook(position));
                startActivity(intent);
            }
        };
    }
}
