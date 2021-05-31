package com.example.bookstore.Room;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class BookTableDAO_Impl implements BookTableDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<BookTable> __insertionAdapterOfBookTable;

  private final SharedSQLiteStatement __preparedStmtOfRemoveFavoriteBookByID;

  public BookTableDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBookTable = new EntityInsertionAdapter<BookTable>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `FavoritesTable` (`id`,`bookID`) VALUES (nullif(?, 0),?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, BookTable value) {
        stmt.bindLong(1, value.getId());
        if (value.getBookID() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getBookID());
        }
      }
    };
    this.__preparedStmtOfRemoveFavoriteBookByID = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM FavoritesTable WHERE bookID = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertFavorite(final BookTable favoriteBook) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfBookTable.insert(favoriteBook);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void removeFavoriteBookByID(final String bookID) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveFavoriteBookByID.acquire();
    int _argIndex = 1;
    if (bookID == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, bookID);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfRemoveFavoriteBookByID.release(_stmt);
    }
  }

  @Override
  public LiveData<List<BookTable>> getAllFavoriteBooks() {
    final String _sql = "SELECT * FROM FavoritesTable";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"FavoritesTable"}, false, new Callable<List<BookTable>>() {
      @Override
      public List<BookTable> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfBookID = CursorUtil.getColumnIndexOrThrow(_cursor, "bookID");
          final List<BookTable> _result = new ArrayList<BookTable>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final BookTable _item;
            final String _tmpBookID;
            if (_cursor.isNull(_cursorIndexOfBookID)) {
              _tmpBookID = null;
            } else {
              _tmpBookID = _cursor.getString(_cursorIndexOfBookID);
            }
            _item = new BookTable(_tmpBookID);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<BookTable> getFavoriteBookByID(final String bookID) {
    final String _sql = "SELECT * FROM FavoritesTable WHERE bookID = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (bookID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, bookID);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"FavoritesTable"}, false, new Callable<BookTable>() {
      @Override
      public BookTable call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfBookID = CursorUtil.getColumnIndexOrThrow(_cursor, "bookID");
          final BookTable _result;
          if(_cursor.moveToFirst()) {
            final String _tmpBookID;
            if (_cursor.isNull(_cursorIndexOfBookID)) {
              _tmpBookID = null;
            } else {
              _tmpBookID = _cursor.getString(_cursorIndexOfBookID);
            }
            _result = new BookTable(_tmpBookID);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _result.setId(_tmpId);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
