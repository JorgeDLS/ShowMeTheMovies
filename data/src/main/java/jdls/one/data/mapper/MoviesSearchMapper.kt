package jdls.one.data.mapper

import jdls.one.data.model.MovieSearchResults
import jdls.one.domain.model.Movie
import javax.inject.Inject

open class MoviesSearchMapper @Inject constructor() : Mapper<MovieSearchResults, List<Movie>> {

  /**
   * Map an instance of a [MovieSearchResults] to a List of [Movie] models
   */
  override fun mapFromApi(raw: MovieSearchResults): List<Movie> =
    raw.results.map {
      Movie(it.id, it.name, it.voteAverage, buildBackdropCompletePath(it.backdropPath))
    }

  /**
   * Builds the complete image path.
   *
   * This should be done based on the Configuration model of themovieDB API and should be cached
   * and refreshed once a week at least, but for the sake of simplicity this will use
   * hardcoded values retrieved from that Configuration model through the API documentation.
   */
  private fun buildBackdropCompletePath(backdropPath: String): String =
    "https://image.tmdb.org/t/p/w1280$backdropPath"
}