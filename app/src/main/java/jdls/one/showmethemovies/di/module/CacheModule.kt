package jdls.one.showmethemovies.di.module

import android.app.Application
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import jdls.one.cache.MovieCacheImpl
import jdls.one.cache.db.MovieDatabase
import jdls.one.data.repository.MoviesCache

@Module
abstract class CacheModule {

  @Module
  companion object {
    @Provides
    @JvmStatic
    fun provideMovieDatabase(application: Application): MovieDatabase {
      return Room.databaseBuilder(
        application.applicationContext,
        MovieDatabase::class.java, "movies.db"
      ).build()
    }
  }

  @Binds
  abstract fun bindRecipeCache(recipeCacheImpl: MovieCacheImpl): MoviesCache
}
