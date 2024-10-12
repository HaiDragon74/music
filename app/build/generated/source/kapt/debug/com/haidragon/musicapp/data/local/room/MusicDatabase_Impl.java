package com.haidragon.musicapp.data.local.room;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.haidragon.musicapp.data.local.room.dao.MusicDao;
import com.haidragon.musicapp.data.local.room.dao.MusicDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MusicDatabase_Impl extends MusicDatabase {
  private volatile MusicDao _musicDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `table_song` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `artist` TEXT NOT NULL, `coverArt` TEXT NOT NULL, `filePath` TEXT NOT NULL, `nameAlbum` TEXT NOT NULL, `duration` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `table_album` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nameAlbum` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `table_playlist` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `listSongInPlaylist` TEXT NOT NULL, `name` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `Song_queue` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `listSongQueue` TEXT NOT NULL, `songPlay` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '12d942e422c99cedf7365e64c65192ea')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `table_song`");
        db.execSQL("DROP TABLE IF EXISTS `table_album`");
        db.execSQL("DROP TABLE IF EXISTS `table_playlist`");
        db.execSQL("DROP TABLE IF EXISTS `Song_queue`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsTableSong = new HashMap<String, TableInfo.Column>(7);
        _columnsTableSong.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableSong.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableSong.put("artist", new TableInfo.Column("artist", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableSong.put("coverArt", new TableInfo.Column("coverArt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableSong.put("filePath", new TableInfo.Column("filePath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableSong.put("nameAlbum", new TableInfo.Column("nameAlbum", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableSong.put("duration", new TableInfo.Column("duration", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTableSong = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTableSong = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTableSong = new TableInfo("table_song", _columnsTableSong, _foreignKeysTableSong, _indicesTableSong);
        final TableInfo _existingTableSong = TableInfo.read(db, "table_song");
        if (!_infoTableSong.equals(_existingTableSong)) {
          return new RoomOpenHelper.ValidationResult(false, "table_song(com.haidragon.musicapp.domain.model.Song).\n"
                  + " Expected:\n" + _infoTableSong + "\n"
                  + " Found:\n" + _existingTableSong);
        }
        final HashMap<String, TableInfo.Column> _columnsTableAlbum = new HashMap<String, TableInfo.Column>(2);
        _columnsTableAlbum.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableAlbum.put("nameAlbum", new TableInfo.Column("nameAlbum", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTableAlbum = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTableAlbum = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTableAlbum = new TableInfo("table_album", _columnsTableAlbum, _foreignKeysTableAlbum, _indicesTableAlbum);
        final TableInfo _existingTableAlbum = TableInfo.read(db, "table_album");
        if (!_infoTableAlbum.equals(_existingTableAlbum)) {
          return new RoomOpenHelper.ValidationResult(false, "table_album(com.haidragon.musicapp.domain.model.Album).\n"
                  + " Expected:\n" + _infoTableAlbum + "\n"
                  + " Found:\n" + _existingTableAlbum);
        }
        final HashMap<String, TableInfo.Column> _columnsTablePlaylist = new HashMap<String, TableInfo.Column>(3);
        _columnsTablePlaylist.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTablePlaylist.put("listSongInPlaylist", new TableInfo.Column("listSongInPlaylist", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTablePlaylist.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTablePlaylist = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTablePlaylist = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTablePlaylist = new TableInfo("table_playlist", _columnsTablePlaylist, _foreignKeysTablePlaylist, _indicesTablePlaylist);
        final TableInfo _existingTablePlaylist = TableInfo.read(db, "table_playlist");
        if (!_infoTablePlaylist.equals(_existingTablePlaylist)) {
          return new RoomOpenHelper.ValidationResult(false, "table_playlist(com.haidragon.musicapp.domain.model.Playlist).\n"
                  + " Expected:\n" + _infoTablePlaylist + "\n"
                  + " Found:\n" + _existingTablePlaylist);
        }
        final HashMap<String, TableInfo.Column> _columnsSongQueue = new HashMap<String, TableInfo.Column>(3);
        _columnsSongQueue.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongQueue.put("listSongQueue", new TableInfo.Column("listSongQueue", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongQueue.put("songPlay", new TableInfo.Column("songPlay", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSongQueue = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSongQueue = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSongQueue = new TableInfo("Song_queue", _columnsSongQueue, _foreignKeysSongQueue, _indicesSongQueue);
        final TableInfo _existingSongQueue = TableInfo.read(db, "Song_queue");
        if (!_infoSongQueue.equals(_existingSongQueue)) {
          return new RoomOpenHelper.ValidationResult(false, "Song_queue(com.haidragon.musicapp.domain.model.SongQueue).\n"
                  + " Expected:\n" + _infoSongQueue + "\n"
                  + " Found:\n" + _existingSongQueue);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "12d942e422c99cedf7365e64c65192ea", "ec262a21a725b3d2a3b98e1cd88db335");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "table_song","table_album","table_playlist","Song_queue");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `table_song`");
      _db.execSQL("DELETE FROM `table_album`");
      _db.execSQL("DELETE FROM `table_playlist`");
      _db.execSQL("DELETE FROM `Song_queue`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(MusicDao.class, MusicDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public MusicDao dao() {
    if (_musicDao != null) {
      return _musicDao;
    } else {
      synchronized(this) {
        if(_musicDao == null) {
          _musicDao = new MusicDao_Impl(this);
        }
        return _musicDao;
      }
    }
  }
}
