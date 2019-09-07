package jdls.one.cache

import io.reactivex.Completable
import io.reactivex.Single
import jdls.one.cache.db.MovieDatabase
import jdls.one.cache.mapper.MovieMapper
import jdls.one.data.repository.MoviesCache
import jdls.one.domain.model.Movie
import javax.inject.Inject

class MovieCacheImpl @Inject constructor(
  private val movieDatabase: MovieDatabase,
  private val entityMapper: MovieMapper
) : MoviesCache {

  internal fun getDatabase(): MovieDatabase {
    return movieDatabase
  }

  override fun getPopularTVShows(): Single<List<Movie>> {
    return Single.defer {
      Single.just(movieDatabase.cachedMovieDao().getPopularTVShows())
    }.map {
      it.map { entityMapper.mapFromCached(it) }
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