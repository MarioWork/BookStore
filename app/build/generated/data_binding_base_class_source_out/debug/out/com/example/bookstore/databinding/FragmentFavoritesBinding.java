// Generated by view binder compiler. Do not edit!
package com.example.bookstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.example.bookstore.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentFavoritesBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView categoryTitleTvFavorites;

  @NonNull
  public final View divider;

  @NonNull
  public final ImageView emptyListImageIvFeed;

  @NonNull
  public final TextView emptyListTitleTvFeed;

  @NonNull
  public final TextView emptyListdescriptionTvFeed;

  @NonNull
  public final RecyclerView favoriteBooksRvFavorites;

  private FragmentFavoritesBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView categoryTitleTvFavorites, @NonNull View divider,
      @NonNull ImageView emptyListImageIvFeed, @NonNull TextView emptyListTitleTvFeed,
      @NonNull TextView emptyListdescriptionTvFeed,
      @NonNull RecyclerView favoriteBooksRvFavorites) {
    this.rootView = rootView;
    this.categoryTitleTvFavorites = categoryTitleTvFavorites;
    this.divider = divider;
    this.emptyListImageIvFeed = emptyListImageIvFeed;
    this.emptyListTitleTvFeed = emptyListTitleTvFeed;
    this.emptyListdescriptionTvFeed = emptyListdescriptionTvFeed;
    this.favoriteBooksRvFavorites = favoriteBooksRvFavorites;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentFavoritesBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentFavoritesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_favorites, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentFavoritesBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.categoryTitle_tv_favorites;
      TextView categoryTitleTvFavorites = rootView.findViewById(id);
      if (categoryTitleTvFavorites == null) {
        break missingId;
      }

      id = R.id.divider;
      View divider = rootView.findViewById(id);
      if (divider == null) {
        break missingId;
      }

      id = R.id.emptyListImage_iv_feed;
      ImageView emptyListImageIvFeed = rootView.findViewById(id);
      if (emptyListImageIvFeed == null) {
        break missingId;
      }

      id = R.id.emptyListTitle_tv_feed;
      TextView emptyListTitleTvFeed = rootView.findViewById(id);
      if (emptyListTitleTvFeed == null) {
        break missingId;
      }

      id = R.id.emptyListdescription_tv_feed;
      TextView emptyListdescriptionTvFeed = rootView.findViewById(id);
      if (emptyListdescriptionTvFeed == null) {
        break missingId;
      }

      id = R.id.favoriteBooks_rv_favorites;
      RecyclerView favoriteBooksRvFavorites = rootView.findViewById(id);
      if (favoriteBooksRvFavorites == null) {
        break missingId;
      }

      return new FragmentFavoritesBinding((ConstraintLayout) rootView, categoryTitleTvFavorites,
          divider, emptyListImageIvFeed, emptyListTitleTvFeed, emptyListdescriptionTvFeed,
          favoriteBooksRvFavorites);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
