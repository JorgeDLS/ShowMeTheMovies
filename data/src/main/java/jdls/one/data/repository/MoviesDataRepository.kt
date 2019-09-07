package jdls.one.data.repository

import io.reactivex.Single
import jdls.one.data.mapper.MoviesSearchMapper
import jdls.one.data.model.MovieSearchResults
import jdls.one.data.source.MoviesApiDataSource
import jdls.one.domain.model.Movie
import jdls.one.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesDataRepository @Inject constructor(
    private val apiDataSourceApiDataSource: MoviesApiDataSource,
    private val entityMapper: MoviesSearchMapper
) : MoviesRepository {

  override fun getPopularTVShows(language: String, page: Int): Single<List<Movie>> {
    return apiDataSourceApiDataSource.getPopularTVShows(language, page).map { entityMapper.mapFromApi(it) }
  }
}

interface MoviesRemote {
  fun getPopularTVShows(language: String, page: Int): Single<MovieSearchResults>
}