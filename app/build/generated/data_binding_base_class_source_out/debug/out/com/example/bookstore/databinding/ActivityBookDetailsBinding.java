// Generated by view binder compiler. Do not edit!
package com.example.bookstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import com.example.bookstore.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityBookDetailsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView authorsTvDetails;

  @NonNull
  public final Button buyBtnDetails;

  @NonNull
  public final ImageView coverIvDetails;

  @NonNull
  public final TextView dateTvDetails;

  @NonNull
  public final TextView descriptionTvDetails;

  @NonNull
  public final View divider2;

  @NonNull
  public final ImageButton favoriteBtnDetails;

  @NonNull
  public final Guideline guideline2;

  @NonNull
  public final Guideline guideline5;

  @NonNull
  public final TextView publisherTvDetails;

  @NonNull
  public final TextView textView4;

  @NonNull
  public final TextView titleTvDetails;

  @NonNull
  public final View view;

  private ActivityBookDetailsBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView authorsTvDetails, @NonNull Button buyBtnDetails,
      @NonNull ImageView coverIvDetails, @NonNull TextView dateTvDetails,
      @NonNull TextView descriptionTvDetails, @NonNull View divider2,
      @NonNull ImageButton favoriteBtnDetails, @NonNull Guideline guideline2,
      @NonNull Guideline guideline5, @NonNull TextView publisherTvDetails,
      @NonNull TextView textView4, @NonNull TextView titleTvDetails, @NonNull View view) {
    this.rootView = rootView;
    this.authorsTvDetails = authorsTvDetails;
    this.buyBtnDetails = buyBtnDetails;
    this.coverIvDetails = coverIvDetails;
    this.dateTvDetails = dateTvDetails;
    this.descriptionTvDetails = descriptionTvDetails;
    this.divider2 = divider2;
    this.favoriteBtnDetails = favoriteBtnDetails;
    this.guideline2 = guideline2;
    this.guideline5 = guideline5;
    this.publisherTvDetails = publisherTvDetails;
    this.textView4 = textView4;
    this.titleTvDetails = titleTvDetails;
    this.view = view;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityBookDetailsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityBookDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_book_details, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityBookDetailsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.authors_tv_details;
      TextView authorsTvDetails = rootView.findViewById(id);
      if (authorsTvDetails == null) {
        break missingId;
      }

      id = R.id.buy_btn_details;
      Button buyBtnDetails = rootView.findViewById(id);
      if (buyBtnDetails == null) {
        break missingId;
      }

      id = R.id.cover_iv_details;
      ImageView coverIvDetails = rootView.findViewById(id);
      if (coverIvDetails == null) {
        break missingId;
      }

      id = R.id.date_tv_details;
      TextView dateTvDetails = rootView.findViewById(id);
      if (dateTvDetails == null) {
        break missingId;
      }

      id = R.id.description_tv_details;
      TextView descriptionTvDetails = rootView.findViewById(id);
      if (descriptionTvDetails == null) {
        break missingId;
      }

      id = R.id.divider2;
      View divider2 = rootView.findViewById(id);
      if (divider2 == null) {
        break missingId;
      }

      id = R.id.favorite_btn_details;
      ImageButton favoriteBtnDetails = rootView.findViewById(id);
      if (favoriteBtnDetails == null) {
        break missingId;
      }

      id = R.id.guideline2;
      Guideline guideline2 = rootView.findViewById(id);
      if (guideline2 == null) {
        break missingId;
      }

      id = R.id.guideline5;
      Guideline guideline5 = rootView.findViewById(id);
      if (guideline5 == null) {
        break missingId;
      }

      id = R.id.publisher_tv_details;
      TextView publisherTvDetails = rootView.findViewById(id);
      if (publisherTvDetails == null) {
        break missingId;
      }

      id = R.id.textView4;
      TextView textView4 = rootView.findViewById(id);
      if (textView4 == null) {
        break missingId;
      }

      id = R.id.title_tv_details;
      TextView titleTvDetails = rootView.findViewById(id);
      if (titleTvDetails == null) {
        break missingId;
      }

      id = R.id.view;
      View view = rootView.findViewById(id);
      if (view == null) {
        break missingId;
      }

      return new ActivityBookDetailsBinding((ConstraintLayout) rootView, authorsTvDetails,
          buyBtnDetails, coverIvDetails, dateTvDetails, descriptionTvDetails, divider2,
          favoriteBtnDetails, guideline2, guideline5, publisherTvDetails, textView4, titleTvDetails,
          view);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}