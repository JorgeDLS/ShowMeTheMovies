package jdls.one.data.mapper

import jdls.one.data.model.RawMovieSearchResults
import jdls.one.domain.model.Movie
import jdls.one.domain.model.MovieResults
import javax.inject.Inject

open class RemoteMapper @Inject constructor() : Mapper<RawMovieSearchResults, MovieResults> {
  override fun reverseMap(entity: MovieResults): RawMovieSearchResults =
    throw UnsupportedOperationException("This method cannot be called in MoviesSearchMapper.")

  /**
   * Map an instance of a [RawMovieSearchResults] to a [MovieResults] model
   */
  override fun map(raw: RawMovieSearchResults): MovieResults =
    MovieResults(
      raw.results.map {
        Movie(
          it.id,
          it.name,
          it.voteAverage,
          buildBackdropCompletePath(it.backdropPath)
        )
      },
      raw.totalPages
    )

  /**
   * Builds the complete image path.
   *
   * This should be done based on the Configuration model of themovieDB API and should be cached
   * and refreshed once a week at least, but for the sake of simplicity this will use
   * hardcoded values retrieved from that Configuration model through the API documentation.
   */
  fun buildBackdropCompletePath(backdropPath: String?): String =
    backdropPath?.let { "https://image.tmdb.org/t/p/w1280$backdropPath" } ?: ""
}