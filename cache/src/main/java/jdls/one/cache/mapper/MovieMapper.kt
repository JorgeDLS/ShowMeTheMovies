package jdls.one.cache.mapper

import jdls.one.cache.model.CachedMovie
import jdls.one.domain.model.Movie
import javax.inject.Inject

/**
 * Map a [CachedMovie] instance to and from a [Movie] instance.
 */
open class MovieMapper @Inject constructor() :
  EntityMapper<CachedMovie, Movie> {

  override fun mapToCached(type: Movie): CachedMovie {
    return CachedMovie(type.id, type.title, type.voteAverage, type.backDropUrl)
  }

  override fun mapFromCached(type: CachedMovie): Movie {
    return Movie(type.id, type.title, type.voteAverage, type.backDropUrl)
  }
}