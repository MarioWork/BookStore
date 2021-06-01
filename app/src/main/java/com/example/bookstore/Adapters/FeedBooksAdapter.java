package com.example.bookstore.Adapters;

import android.text.Layout;
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
import com.example.bookstore.databinding.BookRvItemBinding;
import com.example.bookstore.databinding.FragmentFavoritesBinding;

import org.jetbrains.annotations.NotNull;

public class FeedBooksAdapter extends PagedListAdapter<BookModel, FeedBooksAdapter.FeedBooksViewHolder> {

    private FeedBooksAdapter.IBookClicked listener;

    //Constructor
    public FeedBooksAdapter(FeedBooksAdapter.IBookClicked listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    @NotNull
    @Override
    public FeedBooksViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new FeedBooksViewHolder(BookRvItemBinding
                .inflate(LayoutInflater
                        .from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FeedBooksViewHolder holder, int position) {
        //Cover Thumbnail imageview
        if (getItem(position).getBookInfo().getBookCover() != null) {
            Glide.with(holder.binding.bookCoverIvListItem.getContext())
                    .load(getItem(position).getBookInfo().getBookCover().getCoverImage())
                    .placeholder(R.drawable.book_icon)
                    .into(holder.binding.bookCoverIvListItem);
        }


        //Title Textview
        if (getItem(position).getBookInfo().getTitle() != null) {
            holder.binding.titleTvListItem.setText(getItem(position).getBookInfo().getTitle());
        }


        //Authors Textview
        if (getItem(position).getBookInfo().getAuthors() != null) {
            holder.binding.authorsTvListItem.setText("by " + getItem(position).getBookInfo().getAuthors()[0]);
        }
    }

    //Get the book at a certain position
    public BookModel getBook(int position) {
        return getItem(position);
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


    public class FeedBooksViewHolder extends RecyclerView.ViewHolder {
        BookRvItemBinding binding;

        public FeedBooksViewHolder(@NonNull BookRvItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            //OnItemClick
            binding.cardview.setOnClickListener(new View.OnClickListener() {
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
