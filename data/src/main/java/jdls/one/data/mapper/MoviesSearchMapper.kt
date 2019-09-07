package jdls.one.data.mapper

import jdls.one.data.model.MovieSearchResults
import jdls.one.domain.model.Movie
import javax.inject.Inject

open class MoviesSearchMapper @Inject constructor() : Mapper<MovieSearchResults, List<Movie>> {

  /**
   * Map an instance of a [MovieSearchResults] to a List of [Movie] models
   */
  override fun mapFromApi(raw: MovieSearchResults): List<Movie> {
    return if (raw.results.isNotEmpty()) {
      val movieList = mutableListOf<Movie>()
      raw.results.forEach { movieList.add(Movie(it.name, it.voteAverage, it.posterPath)) }
      movieList
    } else {
      emptyList()
    }
  }
}