// Generated by Dagger (https://dagger.dev).
package com.haidragon.musicapp.presentation.di;

import com.haidragon.musicapp.data.local.room.MusicDatabase;
import com.haidragon.musicapp.domain.repository.Repository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class DatabaseModule_ProvideRepositoryFactory implements Factory<Repository> {
  private final Provider<MusicDatabase> musicDatabaseProvider;

  public DatabaseModule_ProvideRepositoryFactory(Provider<MusicDatabase> musicDatabaseProvider) {
    this.musicDatabaseProvider = musicDatabaseProvider;
  }

  @Override
  public Repository get() {
    return provideRepository(musicDatabaseProvider.get());
  }

  public static DatabaseModule_ProvideRepositoryFactory create(
      Provider<MusicDatabase> musicDatabaseProvider) {
    return new DatabaseModule_ProvideRepositoryFactory(musicDatabaseProvider);
  }

  public static Repository provideRepository(MusicDatabase musicDatabase) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideRepository(musicDatabase));
  }
}