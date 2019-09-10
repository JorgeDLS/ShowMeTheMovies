package jdls.one.data.source

import io.reactivex.Completable
import io.reactivex.Single
import jdls.one.data.cache.db.MovieDatabase
import jdls.one.data.mapper.CacheMapper
import jdls.one.data.repository.MoviesCache
import jdls.one.domain.model.Movie
import jdls.one.domain.model.MovieResults
import javax.inject.Inject

open class MoviesCacheDataSource @Inject constructor(
  private val movieDatabase: MovieDatabase,
  private val entityMapper: CacheMapper
) : MoviesCache {

  internal fun getDatabase(): MovieDatabase {
    return movieDatabase
  }

  override fun getPopularTVShows(): Single<MovieResults> {
    return Single.just(movieDatabase.cachedMovieDao().getPopularTVShows())
      .map {
        MovieResults(it.map { cachedMovie -> entityMapper.reverseMap(cachedMovie) }, 1)
      }
  }

  override fun saveMovie(movie: Movie): Completable {
    movieDatabase.cachedMovieDao().insertMovie(entityMapper.map(movie))
    return Completable.complete()
  }

  override fun deleteMovies(): Completable {
    movieDatabase.cachedMovieDao().clearMovies()
    return Completable.complete()
  }
}