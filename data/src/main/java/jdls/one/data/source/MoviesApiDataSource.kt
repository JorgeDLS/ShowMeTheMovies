package jdls.one.data.source

import io.reactivex.Single
import jdls.one.data.mapper.RemoteMapper
import jdls.one.data.repository.MoviesRemote
import jdls.one.data.service.MoviesServiceFactory
import jdls.one.domain.model.MovieResults
import javax.inject.Inject

class MoviesApiDataSource @Inject constructor(
  private val moviesService: MoviesServiceFactory.MoviesService,
  private val entityMapper: RemoteMapper
) : MoviesRemote {
  override fun getPopularTVShows(language: String, page: Int): Single<MovieResults> =
    moviesService.getPopularTVShows(language, page)
      .map { entityMapper.map(it) }
}