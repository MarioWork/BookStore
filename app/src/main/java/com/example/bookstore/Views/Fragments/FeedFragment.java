package com.example.bookstore.Views.Fragments;


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
import androidx.paging.PagedList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.Adapters.FeedBooksAdapter;
import com.example.bookstore.Models.BookModel;
import com.example.bookstore.R;
import com.example.bookstore.ViewModels.BooksViewModel;
import com.example.bookstore.Views.Activities.BookDetailsActivity;
import com.example.bookstore.databinding.FragmentFeedBinding;

import static com.example.bookstore.Utils.AppConstants.CATEGORY;

import org.jetbrains.annotations.NotNull;


public class FeedFragment extends Fragment {

    private FeedBooksAdapter.IBookClicked listener;
    private BooksViewModel booksViewModel;
    FeedBooksAdapter adapter;

    FragmentFeedBinding binding;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentFeedBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.categoryTitleTvFeedFrag.setText(CATEGORY);

        //Setup RecyclerView
        onItemClick();
        setupBookRecyclerView();

        getAllBooksList();

    }

    private void setupBookRecyclerView() {
        adapter = new FeedBooksAdapter(listener);
        binding.bookRvFeed.setAdapter(adapter);
    }

    private void getAllBooksList() {
        booksViewModel = new ViewModelProvider(this).get(BooksViewModel.class);

        booksViewModel.getRetrofitBooks().observe(getActivity(), new Observer<PagedList<BookModel>>() {
            @Override
            public void onChanged(PagedList<BookModel> bookModels) {
                if (bookModels != null) {
                    adapter.submitList(bookModels);
                }
            }
        });
    }

    private void onItemClick() {
        listener = new FeedBooksAdapter.IBookClicked() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), BookDetailsActivity.class);
                intent.putExtra("BookModel", adapter.getBook(position));
                startActivity(intent);
            }
        };
    }


}
