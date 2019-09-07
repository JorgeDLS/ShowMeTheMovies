package jdls.one.showmethemovies.di.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import jdls.one.data.executor.JobExecutor
import jdls.one.data.repository.MoviesDataRepository
import jdls.one.data.service.MoviesServiceFactory
import jdls.one.domain.executor.ThreadExecutor
import jdls.one.domain.repository.MoviesRepository
import jdls.one.showmethemovies.BuildConfig

@Module
abstract class DataModule {

  @Module
  companion object {
    @Provides
    @JvmStatic
    fun provideMoviesService(): MoviesServiceFactory.MoviesService {
      return MoviesServiceFactory.makeMoviesService(BuildConfig.API_KEY, BuildConfig.DEBUG)
    }
  }

  @Binds
  abstract fun bindMoviesRepository(recipeDataRepository: MoviesDataRepository): MoviesRepository

  @Binds
  abstract fun bindThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor
}