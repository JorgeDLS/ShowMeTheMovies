package jdls.one.showmethemovies.di.module

import android.app.Application
import androidx.room.Room
import com.nhaarman.mockitokotlin2.mock
import dagger.Binds
import dagger.Module
import dagger.Provides
import jdls.one.data.cache.db.MovieDatabase
import jdls.one.data.executor.JobExecutor
import jdls.one.data.repository.MoviesCache
import jdls.one.data.repository.MoviesRemote
import jdls.one.data.service.MoviesServiceFactory
import jdls.one.domain.executor.ThreadExecutor
import jdls.one.domain.repository.MoviesRepository
import javax.inject.Singleton

@Module
abstract class TestDataModule {

  @Module
  companion object {

    @Provides
    @JvmStatic
    @Singleton
    fun provideMoviesRepository(): MoviesRepository {
      return mock()
    }

    @Provides
    @JvmStatic
    @Singleton
    fun provideMoviesRemote(): MoviesRemote {
      return mock()
    }

    @Provides
    @JvmStatic
    @Singleton
    fun provideMoviesService(): MoviesServiceFactory.MoviesService {
      return mock()
    }

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

  @Binds
  abstract fun bindThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor
}
