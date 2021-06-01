package com.example.bookstore.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.bookstore.Models.BookModel;
import com.example.bookstore.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteBooksAdapter extends RecyclerView.Adapter<FavoriteBooksAdapter.ViewHolder> {

    private List<BookModel> bookList;
    private IBookClicked listener;

    //Constructor
    public FavoriteBooksAdapter(IBookClicked listener) {
        bookList = new ArrayList<>();
        this.listener = listener;
    }

    //Get book in a certain position
    public BookModel getBook(int position) {
        return bookList.get(position);
    }

    public void clearAndAddItems(List<BookModel> books) {
        bookList.clear();
        bookList.addAll(books);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_rv_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteBooksAdapter.ViewHolder holder, int position) {

        //Cover Thumbnail imageview
        if (bookList.get(position).getBookInfo().getBookCover().getCoverImage() != null) {
            Glide.with(holder.itemView.getContext())
                    .load(bookList.get(position).getBookInfo().getBookCover().getCoverImage())
                    .placeholder(R.drawable.book_icon)
                    .into(holder.bookCover);
        }


        //Title Textview
        if (bookList.get(position).getBookInfo().getTitle() != null) {
            holder.title.setText(bookList.get(position).getBookInfo().getTitle());
        }


        //Authors Textview
        if (bookList.get(position).getBookInfo().getAuthors() != null) {
            holder.authors.setText("by " + bookList.get(position).getBookInfo().getAuthors()[0]);
        }
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    //View Holder Class
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView bookCover;
        private TextView title, authors;


        //List Item
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookCover = itemView.findViewById(R.id.bookCover_iv_listItem);
            title = itemView.findViewById(R.id.title_tv_listItem);
            authors = itemView.findViewById(R.id.authors_tv_listItem);

            itemView.setOnClickListener(this);
        }

        //OnRecyclerViewItemClick
        @Override
        public void onClick(View v) {
            listener.onClick(getAdapterPosition());
        }
    }

    public interface IBookClicked {
        void onClick(int position);
    }
}

