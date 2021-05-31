package com.example.bookstore.Room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.jetbrains.annotations.NotNull;

@Database(entities = BookTable.class, version = 1, exportSchema = false)
public abstract class FavoriteBooksDatabase extends RoomDatabase {

    private static FavoriteBooksDatabase instance;

    public abstract BookTableDAO bookTableDAO();

    public static synchronized FavoriteBooksDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), FavoriteBooksDatabase.class, "favoriteBooks")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
