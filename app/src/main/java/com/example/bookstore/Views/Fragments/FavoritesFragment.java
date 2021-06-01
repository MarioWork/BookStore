package com.example.bookstore.Views.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.Adapters.FavoriteBooksAdapter;
import com.example.bookstore.Models.BookModel;
import com.example.bookstore.R;
import com.example.bookstore.Room.BookTable;
import com.example.bookstore.ViewModels.BooksViewModel;
import com.example.bookstore.Views.Activities.BookDetailsActivity;
import com.example.bookstore.databinding.FragmentFavoritesBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FavoritesFragment extends Fragment {
    private FavoriteBooksAdapter.IBookClicked listener;
    private BooksViewModel booksViewModel;
    private FavoriteBooksAdapter adapter;
    FragmentFavoritesBinding binding;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.categoryTitleTvFavorites.setText("Favorites");

        //Setup Recyclerview
        onItemClick();
        setupBookRecyclerView();

        //Fill the recyclerview
        getFavoritesListIDS();


    }

    private void setupBookRecyclerView() {
        adapter = new FavoriteBooksAdapter(listener);
        binding.favoriteBooksRvFavorites.setAdapter(adapter);
    }

    private void getFavoritesListIDS() {
        booksViewModel = new ViewModelProvider(this).get(BooksViewModel.class);
        booksViewModel.getFavoriteBooksIDS().observe(getActivity(), new Observer<List<BookTable>>() {
            @Override
            public void onChanged(List<BookTable> bookTableList) {
                if (bookTableList != null && !bookTableList.isEmpty()) {
                    getFavoritesList(bookTableList);

                    binding.emptyListImageIvFeed.setVisibility(View.GONE);
                    binding.emptyListTitleTvFeed.setVisibility(View.GONE);
                    binding.emptyListdescriptionTvFeed.setVisibility(View.GONE);
                } else {
                    adapter.clear();
                    adapter.notifyDataSetChanged();
                    binding.emptyListImageIvFeed.setVisibility(View.VISIBLE);
                    binding.emptyListTitleTvFeed.setVisibility(View.VISIBLE);
                    binding.emptyListdescriptionTvFeed.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void getFavoritesList(List<BookTable> localBooksIds) {
        booksViewModel.getFavoriteBooks(localBooksIds).observe(this, new Observer<List<BookModel>>() {
            @Override
            public void onChanged(List<BookModel> bookModels) {
                if (bookModels != null && !bookModels.isEmpty()) {
                    adapter.clearAndAddItems(bookModels);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void onItemClick() {
        listener = new FavoriteBooksAdapter.IBookClicked() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), BookDetailsActivity.class);
                intent.putExtra("BookModel", adapter.getBook(position));
                startActivity(intent);
            }
        };
    }
}
