package com.haidragon.musicapp.data.local.room.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.haidragon.musicapp.domain.model.Album;
import com.haidragon.musicapp.domain.model.Playlist;
import com.haidragon.musicapp.domain.model.Song;
import com.haidragon.musicapp.domain.model.SongQueue;
import com.remotetechs.musicapp.data.local.room.typeconver.MusicTypeConverter;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MusicDao_Impl implements MusicDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Song> __insertionAdapterOfSong;

  private final EntityInsertionAdapter<Album> __insertionAdapterOfAlbum;

  private final EntityInsertionAdapter<Playlist> __insertionAdapterOfPlaylist;

  private final MusicTypeConverter __musicTypeConverter = new MusicTypeConverter();

  private final EntityInsertionAdapter<SongQueue> __insertionAdapterOfSongQueue;

  private final EntityDeletionOrUpdateAdapter<Song> __deletionAdapterOfSong;

  private final EntityDeletionOrUpdateAdapter<Album> __deletionAdapterOfAlbum;

  private final EntityDeletionOrUpdateAdapter<SongQueue> __updateAdapterOfSongQueue;

  private final SharedSQLiteStatement __preparedStmtOfDeleteSongById;

  private final SharedSQLiteStatement __preparedStmtOfUpdatePlaylistFilePathByName;

  public MusicDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSong = new EntityInsertionAdapter<Song>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `table_song` (`id`,`title`,`artist`,`coverArt`,`filePath`,`nameAlbum`,`duration`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Song entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitle());
        }
        if (entity.getArtist() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getArtist());
        }
        if (entity.getCoverArt() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCoverArt());
        }
        if (entity.getFilePath() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getFilePath());
        }
        if (entity.getNameAlbum() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getNameAlbum());
        }
        statement.bindLong(7, entity.getDuration());
      }
    };
    this.__insertionAdapterOfAlbum = new EntityInsertionAdapter<Album>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `table_album` (`id`,`nameAlbum`) VALUES (nullif(?, 0),?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Album entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getNameAlbum() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getNameAlbum());
        }
      }
    };
    this.__insertionAdapterOfPlaylist = new EntityInsertionAdapter<Playlist>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `table_playlist` (`id`,`listSongInPlaylist`,`name`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Playlist entity) {
        statement.bindLong(1, entity.getId());
        final String _tmp = __musicTypeConverter.fromSongList(entity.getListSongInPlaylist());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, _tmp);
        }
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getName());
        }
      }
    };
    this.__insertionAdapterOfSongQueue = new EntityInsertionAdapter<SongQueue>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `Song_queue` (`id`,`listSongQueue`,`songPlay`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SongQueue entity) {
        statement.bindLong(1, entity.getId());
        final String _tmp = __musicTypeConverter.fromSongList(entity.getListSongQueue());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, _tmp);
        }
        final String _tmp_1 = __musicTypeConverter.fromSong(entity.getSongPlay());
        if (_tmp_1 == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, _tmp_1);
        }
      }
    };
    this.__deletionAdapterOfSong = new EntityDeletionOrUpdateAdapter<Song>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `table_song` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Song entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__deletionAdapterOfAlbum = new EntityDeletionOrUpdateAdapter<Album>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `table_album` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Album entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfSongQueue = new EntityDeletionOrUpdateAdapter<SongQueue>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `Song_queue` SET `id` = ?,`listSongQueue` = ?,`songPlay` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SongQueue entity) {
        statement.bindLong(1, entity.getId());
        final String _tmp = __musicTypeConverter.fromSongList(entity.getListSongQueue());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, _tmp);
        }
        final String _tmp_1 = __musicTypeConverter.fromSong(entity.getSongPlay());
        if (_tmp_1 == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, _tmp_1);
        }
        statement.bindLong(4, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteSongById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM table_song WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdatePlaylistFilePathByName = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE table_playlist SET listSongInPlaylist = ? WHERE name = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertSong(final Song song) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSong.insert(song);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAlbum(final Album album) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfAlbum.insert(album);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Object insertPlaylist(final Playlist playlist,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPlaylist.insert(playlist);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertSongQueue(final SongQueue songQueue,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSongQueue.insert(songQueue);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteSong(final Song song, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfSong.handle(song);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAlbum(final Album album, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfAlbum.handle(album);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateSongQueue(final SongQueue songQueue,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfSongQueue.handle(songQueue);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public void deleteSongById(final int id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteSongById.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteSongById.release(_stmt);
    }
  }

  @Override
  public Object updatePlaylistFilePathByName(final String namePlaylist,
      final List<Song> listSongInPlaylist, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdatePlaylistFilePathByName.acquire();
        int _argIndex = 1;
        final String _tmp = __musicTypeConverter.fromSongList(listSongInPlaylist);
        if (_tmp == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, _tmp);
        }
        _argIndex = 2;
        if (namePlaylist == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, namePlaylist);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdatePlaylistFilePathByName.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Song>> realSong() {
    final String _sql = "SELECT * FROM table_song";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"table_song"}, new Callable<List<Song>>() {
      @Override
      @NonNull
      public List<Song> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(_cursor, "artist");
          final int _cursorIndexOfCoverArt = CursorUtil.getColumnIndexOrThrow(_cursor, "coverArt");
          final int _cursorIndexOfFilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "filePath");
          final int _cursorIndexOfNameAlbum = CursorUtil.getColumnIndexOrThrow(_cursor, "nameAlbum");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final List<Song> _result = new ArrayList<Song>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Song _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpArtist;
            if (_cursor.isNull(_cursorIndexOfArtist)) {
              _tmpArtist = null;
            } else {
              _tmpArtist = _cursor.getString(_cursorIndexOfArtist);
            }
            final String _tmpCoverArt;
            if (_cursor.isNull(_cursorIndexOfCoverArt)) {
              _tmpCoverArt = null;
            } else {
              _tmpCoverArt = _cursor.getString(_cursorIndexOfCoverArt);
            }
            final String _tmpFilePath;
            if (_cursor.isNull(_cursorIndexOfFilePath)) {
              _tmpFilePath = null;
            } else {
              _tmpFilePath = _cursor.getString(_cursorIndexOfFilePath);
            }
            final String _tmpNameAlbum;
            if (_cursor.isNull(_cursorIndexOfNameAlbum)) {
              _tmpNameAlbum = null;
            } else {
              _tmpNameAlbum = _cursor.getString(_cursorIndexOfNameAlbum);
            }
            final int _tmpDuration;
            _tmpDuration = _cursor.getInt(_cursorIndexOfDuration);
            _item = new Song(_tmpId,_tmpTitle,_tmpArtist,_tmpCoverArt,_tmpFilePath,_tmpNameAlbum,_tmpDuration);
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
  public Song getSongById(final int id) {
    final String _sql = "SELECT * FROM table_song WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(_cursor, "artist");
      final int _cursorIndexOfCoverArt = CursorUtil.getColumnIndexOrThrow(_cursor, "coverArt");
      final int _cursorIndexOfFilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "filePath");
      final int _cursorIndexOfNameAlbum = CursorUtil.getColumnIndexOrThrow(_cursor, "nameAlbum");
      final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
      final Song _result;
      if (_cursor.moveToFirst()) {
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        final String _tmpArtist;
        if (_cursor.isNull(_cursorIndexOfArtist)) {
          _tmpArtist = null;
        } else {
          _tmpArtist = _cursor.getString(_cursorIndexOfArtist);
        }
        final String _tmpCoverArt;
        if (_cursor.isNull(_cursorIndexOfCoverArt)) {
          _tmpCoverArt = null;
        } else {
          _tmpCoverArt = _cursor.getString(_cursorIndexOfCoverArt);
        }
        final String _tmpFilePath;
        if (_cursor.isNull(_cursorIndexOfFilePath)) {
          _tmpFilePath = null;
        } else {
          _tmpFilePath = _cursor.getString(_cursorIndexOfFilePath);
        }
        final String _tmpNameAlbum;
        if (_cursor.isNull(_cursorIndexOfNameAlbum)) {
          _tmpNameAlbum = null;
        } else {
          _tmpNameAlbum = _cursor.getString(_cursorIndexOfNameAlbum);
        }
        final int _tmpDuration;
        _tmpDuration = _cursor.getInt(_cursorIndexOfDuration);
        _result = new Song(_tmpId,_tmpTitle,_tmpArtist,_tmpCoverArt,_tmpFilePath,_tmpNameAlbum,_tmpDuration);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Song> getSongByNameAlbum(final String nameAlbum) {
    final String _sql = "SELECT * FROM table_song WHERE nameAlbum = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (nameAlbum == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, nameAlbum);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(_cursor, "artist");
      final int _cursorIndexOfCoverArt = CursorUtil.getColumnIndexOrThrow(_cursor, "coverArt");
      final int _cursorIndexOfFilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "filePath");
      final int _cursorIndexOfNameAlbum = CursorUtil.getColumnIndexOrThrow(_cursor, "nameAlbum");
      final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
      final List<Song> _result = new ArrayList<Song>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Song _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        final String _tmpArtist;
        if (_cursor.isNull(_cursorIndexOfArtist)) {
          _tmpArtist = null;
        } else {
          _tmpArtist = _cursor.getString(_cursorIndexOfArtist);
        }
        final String _tmpCoverArt;
        if (_cursor.isNull(_cursorIndexOfCoverArt)) {
          _tmpCoverArt = null;
        } else {
          _tmpCoverArt = _cursor.getString(_cursorIndexOfCoverArt);
        }
        final String _tmpFilePath;
        if (_cursor.isNull(_cursorIndexOfFilePath)) {
          _tmpFilePath = null;
        } else {
          _tmpFilePath = _cursor.getString(_cursorIndexOfFilePath);
        }
        final String _tmpNameAlbum;
        if (_cursor.isNull(_cursorIndexOfNameAlbum)) {
          _tmpNameAlbum = null;
        } else {
          _tmpNameAlbum = _cursor.getString(_cursorIndexOfNameAlbum);
        }
        final int _tmpDuration;
        _tmpDuration = _cursor.getInt(_cursorIndexOfDuration);
        _item = new Song(_tmpId,_tmpTitle,_tmpArtist,_tmpCoverArt,_tmpFilePath,_tmpNameAlbum,_tmpDuration);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Flow<List<Song>> realSongByAlbum(final String nameAlbum) {
    final String _sql = "SELECT * FROM table_song WHERE nameAlbum = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (nameAlbum == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, nameAlbum);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"table_song"}, new Callable<List<Song>>() {
      @Override
      @NonNull
      public List<Song> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(_cursor, "artist");
          final int _cursorIndexOfCoverArt = CursorUtil.getColumnIndexOrThrow(_cursor, "coverArt");
          final int _cursorIndexOfFilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "filePath");
          final int _cursorIndexOfNameAlbum = CursorUtil.getColumnIndexOrThrow(_cursor, "nameAlbum");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final List<Song> _result = new ArrayList<Song>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Song _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpArtist;
            if (_cursor.isNull(_cursorIndexOfArtist)) {
              _tmpArtist = null;
            } else {
              _tmpArtist = _cursor.getString(_cursorIndexOfArtist);
            }
            final String _tmpCoverArt;
            if (_cursor.isNull(_cursorIndexOfCoverArt)) {
              _tmpCoverArt = null;
            } else {
              _tmpCoverArt = _cursor.getString(_cursorIndexOfCoverArt);
            }
            final String _tmpFilePath;
            if (_cursor.isNull(_cursorIndexOfFilePath)) {
              _tmpFilePath = null;
            } else {
              _tmpFilePath = _cursor.getString(_cursorIndexOfFilePath);
            }
            final String _tmpNameAlbum;
            if (_cursor.isNull(_cursorIndexOfNameAlbum)) {
              _tmpNameAlbum = null;
            } else {
              _tmpNameAlbum = _cursor.getString(_cursorIndexOfNameAlbum);
            }
            final int _tmpDuration;
            _tmpDuration = _cursor.getInt(_cursorIndexOfDuration);
            _item = new Song(_tmpId,_tmpTitle,_tmpArtist,_tmpCoverArt,_tmpFilePath,_tmpNameAlbum,_tmpDuration);
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
  public Flow<List<Album>> realAlbum() {
    final String _sql = "SELECT * FROM table_album";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"table_album"}, new Callable<List<Album>>() {
      @Override
      @NonNull
      public List<Album> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNameAlbum = CursorUtil.getColumnIndexOrThrow(_cursor, "nameAlbum");
          final List<Album> _result = new ArrayList<Album>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Album _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpNameAlbum;
            if (_cursor.isNull(_cursorIndexOfNameAlbum)) {
              _tmpNameAlbum = null;
            } else {
              _tmpNameAlbum = _cursor.getString(_cursorIndexOfNameAlbum);
            }
            _item = new Album(_tmpId,_tmpNameAlbum);
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
  public Album getAlbumByName(final String nameAlbum) {
    final String _sql = "SELECT * FROM table_album WHERE nameAlbum = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (nameAlbum == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, nameAlbum);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfNameAlbum = CursorUtil.getColumnIndexOrThrow(_cursor, "nameAlbum");
      final Album _result;
      if (_cursor.moveToFirst()) {
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpNameAlbum;
        if (_cursor.isNull(_cursorIndexOfNameAlbum)) {
          _tmpNameAlbum = null;
        } else {
          _tmpNameAlbum = _cursor.getString(_cursorIndexOfNameAlbum);
        }
        _result = new Album(_tmpId,_tmpNameAlbum);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Flow<List<Playlist>> realPlaylist() {
    final String _sql = "SELECT * FROM table_playlist";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"table_playlist"}, new Callable<List<Playlist>>() {
      @Override
      @NonNull
      public List<Playlist> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfListSongInPlaylist = CursorUtil.getColumnIndexOrThrow(_cursor, "listSongInPlaylist");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final List<Playlist> _result = new ArrayList<Playlist>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Playlist _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final List<Song> _tmpListSongInPlaylist;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfListSongInPlaylist)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfListSongInPlaylist);
            }
            _tmpListSongInPlaylist = __musicTypeConverter.toSongList(_tmp);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            _item = new Playlist(_tmpId,_tmpListSongInPlaylist,_tmpName);
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
  public Flow<List<Playlist>> realPlaylistBottom(final String nameRecentlyAdd,
      final String nameRecentlyPlay, final String nameMyTopTracks) {
    final String _sql = "SELECT * FROM table_playlist WHERE name NOT IN (?, ?, ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (nameRecentlyAdd == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, nameRecentlyAdd);
    }
    _argIndex = 2;
    if (nameRecentlyPlay == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, nameRecentlyPlay);
    }
    _argIndex = 3;
    if (nameMyTopTracks == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, nameMyTopTracks);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"table_playlist"}, new Callable<List<Playlist>>() {
      @Override
      @NonNull
      public List<Playlist> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfListSongInPlaylist = CursorUtil.getColumnIndexOrThrow(_cursor, "listSongInPlaylist");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final List<Playlist> _result = new ArrayList<Playlist>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Playlist _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final List<Song> _tmpListSongInPlaylist;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfListSongInPlaylist)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfListSongInPlaylist);
            }
            _tmpListSongInPlaylist = __musicTypeConverter.toSongList(_tmp);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            _item = new Playlist(_tmpId,_tmpListSongInPlaylist,_tmpName);
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
  public Object isPlaylistExists(final String namePlaylist,
      final Continuation<? super Boolean> $completion) {
    final String _sql = "SELECT EXISTS(SELECT 1 FROM table_playlist WHERE name = ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (namePlaylist == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, namePlaylist);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Boolean>() {
      @Override
      @NonNull
      public Boolean call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Boolean _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp == null ? null : _tmp != 0;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object realPlaylistByName(final String namePlaylist,
      final Continuation<? super Playlist> $completion) {
    final String _sql = "SELECT * FROM table_playlist  WHERE name = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (namePlaylist == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, namePlaylist);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Playlist>() {
      @Override
      @Nullable
      public Playlist call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfListSongInPlaylist = CursorUtil.getColumnIndexOrThrow(_cursor, "listSongInPlaylist");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final Playlist _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final List<Song> _tmpListSongInPlaylist;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfListSongInPlaylist)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfListSongInPlaylist);
            }
            _tmpListSongInPlaylist = __musicTypeConverter.toSongList(_tmp);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            _result = new Playlist(_tmpId,_tmpListSongInPlaylist,_tmpName);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object isSongQueueExists(final Song songPlay,
      final Continuation<? super Boolean> $completion) {
    final String _sql = "SELECT EXISTS(SELECT 1 FROM Song_queue WHERE songPlay = ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __musicTypeConverter.fromSong(songPlay);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Boolean>() {
      @Override
      @NonNull
      public Boolean call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Boolean _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp_1;
            if (_cursor.isNull(0)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getInt(0);
            }
            _result = _tmp_1 == null ? null : _tmp_1 != 0;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object isSongQueueExists(final int id, final Continuation<? super Boolean> $completion) {
    final String _sql = "SELECT EXISTS(SELECT 1 FROM Song_queue WHERE id = ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Boolean>() {
      @Override
      @NonNull
      public Boolean call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Boolean _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp == null ? null : _tmp != 0;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object realSongQueue(final Continuation<? super SongQueue> $completion) {
    final String _sql = "SELECT * FROM Song_queue";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<SongQueue>() {
      @Override
      @NonNull
      public SongQueue call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfListSongQueue = CursorUtil.getColumnIndexOrThrow(_cursor, "listSongQueue");
          final int _cursorIndexOfSongPlay = CursorUtil.getColumnIndexOrThrow(_cursor, "songPlay");
          final SongQueue _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final List<Song> _tmpListSongQueue;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfListSongQueue)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfListSongQueue);
            }
            _tmpListSongQueue = __musicTypeConverter.toSongList(_tmp);
            final Song _tmpSongPlay;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfSongPlay)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfSongPlay);
            }
            _tmpSongPlay = __musicTypeConverter.toSong(_tmp_1);
            _result = new SongQueue(_tmpId,_tmpListSongQueue,_tmpSongPlay);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
