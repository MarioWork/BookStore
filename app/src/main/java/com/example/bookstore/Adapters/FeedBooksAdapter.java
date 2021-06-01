package com.example.bookstore.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookstore.Models.BookModel;
import com.example.bookstore.R;

import org.jetbrains.annotations.NotNull;

public class FeedBooksAdapter extends PagedListAdapter<BookModel, FeedBooksAdapter.TestViewHolder> {

    private FeedBooksAdapter.IBookClicked listener;

    //Constructor
    public FeedBooksAdapter(FeedBooksAdapter.IBookClicked listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    //Get the book at a certain position
    public BookModel getBook(int position) {
        return getItem(position);
    }

    @NotNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_rv_item, parent, false);
        return new FeedBooksAdapter.TestViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FeedBooksAdapter.TestViewHolder holder, int position) {


        //Cover Thumbnail imageview
        if (getItem(position).getBookInfo().getBookCover() != null) {
            Glide.with(holder.itemView.getContext())
                    .load(getItem(position).getBookInfo().getBookCover().getCoverImage())
                    .placeholder(R.drawable.book_icon)
                    .into(holder.bookCover);
        }


        //Title Textview
        if (getItem(position).getBookInfo().getTitle() != null) {
            holder.title.setText(getItem(position).getBookInfo().getTitle());
        }


        //Authors Textview
        if (getItem(position).getBookInfo().getAuthors() != null) {
            holder.authors.setText("by " + getItem(position).getBookInfo().getAuthors()[0]);
        }
    }

    //Compare items
    private static DiffUtil.ItemCallback<BookModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<BookModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull @NotNull BookModel oldItem, @NonNull @NotNull BookModel newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull BookModel oldItem, @NonNull @NotNull BookModel newItem) {
            return oldItem.compare(newItem);
        }
    };


    public class TestViewHolder extends RecyclerView.ViewHolder {
        private ImageView bookCover;
        private TextView title, authors;

        public TestViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            bookCover = itemView.findViewById(R.id.bookCover_iv_listItem);
            title = itemView.findViewById(R.id.title_tv_listItem);
            authors = itemView.findViewById(R.id.authors_tv_listItem);

            //OnItemClick
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(getAdapterPosition());
                }
            });


        }


    }


    public interface IBookClicked {
        void onClick(int position);
    }
}
