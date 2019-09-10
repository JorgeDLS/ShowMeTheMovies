package jdls.one.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import jdls.one.data.source.MoviesApiDataSource
import jdls.one.data.source.MoviesCacheDataSource
import jdls.one.domain.model.Movie
import jdls.one.domain.model.MovieResults
import jdls.one.domain.repository.MoviesRepository
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class MoviesDataRepository @Inject constructor(
  private val apiDataSource: MoviesApiDataSource,
  private val cacheDataSource: MoviesCacheDataSource
) : MoviesRepository {

  override fun getPopularTVShows(language: String, page: Int): Single<MovieResults> {
    return apiDataSource.getPopularTVShows(language, page)
      .flatMap { it.movies.map { movie -> cacheDataSource.saveMovie(movie) }; Single.just(it) }
      .onErrorResumeNext {
        when (it) {
          is ConnectException, is UnknownHostException ->
            cacheDataSource.getPopularTVShows()
          else -> Single.error(it)
        }
      }
  }
}

interface MoviesRemote {
  fun getPopularTVShows(language: String, page: Int): Single<MovieResults>
}

interface MoviesCache {
  fun getPopularTVShows(): Single<MovieResults>

  fun saveMovie(movie: Movie): Completable

  fun deleteMovies(): Completable
}