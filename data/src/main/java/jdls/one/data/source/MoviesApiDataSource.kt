package jdls.one.data.source

import io.reactivex.Single
import jdls.one.data.model.MovieSearchResults
import jdls.one.data.repository.MoviesRemote
import jdls.one.data.service.MoviesServiceFactory
import javax.inject.Inject

class MoviesApiDataSource @Inject constructor(
    private val moviesService: MoviesServiceFactory.MoviesService
) : MoviesRemote {
  override fun getPopularTVShows(language: String, page: Int): Single<MovieSearchResults> =
      moviesService.getPopularTVShows(language = language, page = page)
}