package jdls.one.data.mapper

import jdls.one.data.model.CachedMovie
import jdls.one.domain.model.Movie
import javax.inject.Inject

/**
 * Map a [Movie] instance to and from a [CachedMovie] instance.
 */
open class CacheMapper @Inject constructor() : Mapper<Movie, CachedMovie> {
  override fun map(raw: Movie): CachedMovie {
    return CachedMovie(raw.id, raw.title, raw.voteAverage, raw.backDropUrl)
  }

  override fun reverseMap(entity: CachedMovie): Movie {
    return Movie(entity.id, entity.title, entity.voteAverage, entity.backDropUrl)
  }
}