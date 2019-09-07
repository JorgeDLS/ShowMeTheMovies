package jdls.one.data.source

import io.reactivex.Completable
import io.reactivex.Single
import jdls.one.data.repository.MoviesCache
import jdls.one.data.repository.MoviesDataStore
import jdls.one.domain.model.Movie
import javax.inject.Inject

open class MoviesCacheDataSource @Inject constructor(private val moviesCache: MoviesCache) :
  MoviesDataStore {
  override fun getPopularTVShows(): Single<List<Movie>> {
    return moviesCache.getPopularTVShows()
  }

  override fun saveMovie(movie: Movie): Completable {
    return moviesCache.saveMovie(movie)
  }

  override fun deleteMovies(): Completable {
    return moviesCache.deleteMovies()
  }
}