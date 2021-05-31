package com.example.bookstore.Views;

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

import com.example.bookstore.Adapters.Book_Rv_Adapter;
import com.example.bookstore.Models.BookModel;
import com.example.bookstore.R;
import com.example.bookstore.Room.BookTable;
import com.example.bookstore.ViewModels.BooksViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FavoritesFragment extends Fragment {
    private Book_Rv_Adapter.IBookClicked listener;
    private BooksViewModel booksViewModel;
    private Book_Rv_Adapter adapter;

    //Widgets
    private RecyclerView favoriteBookRV;
    private TextView categoryTitle_tv;
    private TextView emptyListTitle_rv;
    private TextView emptyListDescription_rv;
    private ImageView emptyListImage_iv;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        categoryTitle_tv = getView().findViewById(R.id.categoryTitle_tv_favorites);
        categoryTitle_tv.setText("Favorites");
        emptyListImage_iv = getView().findViewById(R.id.emptyListImage_iv_favorites);
        emptyListTitle_rv = getView().findViewById(R.id.emptyListTitle_tv_favorites);
        emptyListDescription_rv = getView().findViewById(R.id.emptyListdescription_tv_favorties);

        //Setup Recyclerview
        onItemClick();
        setupBookRecyclerView();

        //Fill the recyclerview
        getFavoritsListIDS();


    }

    private void setupBookRecyclerView() {
        adapter = new Book_Rv_Adapter(listener);
        favoriteBookRV = getView().findViewById(R.id.favoriteBooks_rv_favorites);
        favoriteBookRV.setAdapter(adapter);
    }

    private void getFavoritsListIDS() {
        booksViewModel = new ViewModelProvider(this).get(BooksViewModel.class);
        booksViewModel.getFavoriteBooksIDS().observe(getActivity(), new Observer<List<BookTable>>() {
            @Override
            public void onChanged(List<BookTable> bookTableList) {
                if (bookTableList != null && !bookTableList.isEmpty()) {
                    getFavoritesList(bookTableList);

                    emptyListImage_iv.setVisibility(View.GONE);
                    emptyListTitle_rv.setVisibility(View.GONE);
                    emptyListDescription_rv.setVisibility(View.GONE);
                } else {
                    emptyListImage_iv.setVisibility(View.VISIBLE);
                    emptyListTitle_rv.setVisibility(View.VISIBLE);
                    emptyListDescription_rv.setVisibility(View.VISIBLE);
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
