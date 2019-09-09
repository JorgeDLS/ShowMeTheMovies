package jdls.one.data.source

import io.reactivex.Completable
import io.reactivex.Single
import jdls.one.data.cache.db.MovieDatabase
import jdls.one.data.mapper.CachedMovieMapper
import jdls.one.data.repository.MoviesCache
import jdls.one.domain.model.Movie
import jdls.one.domain.model.MovieResults
import javax.inject.Inject

open class MoviesCacheDataSource @Inject constructor(
  private val movieDatabase: MovieDatabase,
  private val entityMapper: CachedMovieMapper
) : MoviesCache {

  internal fun getDatabase(): MovieDatabase {
    return movieDatabase
  }

  override fun getPopularTVShows(): Single<MovieResults> {
    return Single.defer {
      Single.just(movieDatabase.cachedMovieDao().getPopularTVShows())
    }.map {
      MovieResults(it.map { cachedMovie -> entityMapper.mapFromCached(cachedMovie) }, 1)
    }
  }

  override fun saveMovie(movie: Movie): Completable {
    return Completable.defer {
      movieDatabase.cachedMovieDao().insertMovie(entityMapper.mapToCached(movie))
      Completable.complete()
    }
  }

  override fun deleteMovies(): Completable {
    return Completable.defer {
      movieDatabase.cachedMovieDao().clearMovies()
      Completable.complete()
    }
  }
}