package jdls.one.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import jdls.one.data.mapper.MoviesSearchMapper
import jdls.one.data.model.MovieSearchResults
import jdls.one.data.source.MoviesApiDataSource
import jdls.one.data.source.MoviesCacheDataSource
import jdls.one.domain.model.Movie
import jdls.one.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesDataRepository @Inject constructor(
  private val apiDataSource: MoviesApiDataSource,
  private val cacheDataSource: MoviesCacheDataSource,
  private val entityMapper: MoviesSearchMapper
) : MoviesRepository {

  override fun getPopularTVShows(language: String, page: Int): Single<List<Movie>> {
    return apiDataSource.getPopularTVShows(language, page)
      .map { entityMapper.mapFromApi(it) }
  }
}

interface MoviesRemote {
  fun getPopularTVShows(language: String, page: Int): Single<MovieSearchResults>
}

interface MoviesCache {
  fun getPopularTVShows(): Single<List<Movie>>

  fun saveMovie(movie: Movie): Completable

  fun deleteMovies(): Completable
}

interface MoviesDataStore {
  fun getPopularTVShows(): Single<List<Movie>>

  fun saveMovie(movie: Movie): Completable

  fun deleteMovies(): Completable
}