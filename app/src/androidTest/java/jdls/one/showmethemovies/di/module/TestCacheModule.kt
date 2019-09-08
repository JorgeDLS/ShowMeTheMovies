package jdls.one.showmethemovies.di.module

import android.app.Application
import androidx.room.Room
import com.nhaarman.mockitokotlin2.mock
import dagger.Module
import dagger.Provides
import jdls.one.cache.db.MovieDatabase
import jdls.one.data.repository.MoviesCache
import javax.inject.Singleton

@Module
object TestCacheModule {

  @Provides
  @JvmStatic
  fun provideMovieDatabase(application: Application): MovieDatabase {
    return Room.databaseBuilder(
      application.applicationContext,
      MovieDatabase::class.java, "movies.db"
    )
      .build()
  }

  @Provides
  @JvmStatic
  @Singleton
  fun provideMovieCache(): MoviesCache {
    return mock()
  }
}