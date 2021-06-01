package com.example.bookstore.Views.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
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
        binding = FragmentFeedBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.categoryTitleTvFeedFrag.setText(CATEGORY);

        binding.scrollTopButtonFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.bookRvFeed.smoothScrollToPosition(0);
                binding.scrollTopButtonFeed.setVisibility(View.GONE);
            }
        });

        //Setup RecyclerView
        onItemClick();
        setupBookRecyclerView();

        getAllBooksList();

    }

    private void setupBookRecyclerView() {
        adapter = new FeedBooksAdapter(listener,binding.progressCircular);
        binding.bookRvFeed.setAdapter(adapter);

        binding.bookRvFeed.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) { //Not Scrolling
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            binding.scrollTopButtonFeed.setVisibility(View.GONE);
                        }
                    }, 2000);
                }
            }

            //Scroll to the top function
            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 40) {
                    binding.scrollTopButtonFeed.setVisibility(View.VISIBLE);
                } else if (dy < 0) {
                    binding.scrollTopButtonFeed.setVisibility(View.GONE);
                }
            }
        });
    }

    private void getAllBooksList() {
        booksViewModel = new ViewModelProvider(this).get(BooksViewModel.class);

        booksViewModel.getRetrofitBooks().observe(getActivity(), new Observer<PagedList<BookModel>>() {
            @Override
            public void onChanged(PagedList<BookModel> bookModels) {
                if (bookModels != null) {
                    try {
                        adapter.submitList(bookModels);
                    }catch (Exception ex){
                        Log.d("error",ex.getMessage());
                    }
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
