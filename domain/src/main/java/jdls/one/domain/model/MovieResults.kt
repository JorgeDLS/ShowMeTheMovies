package jdls.one.domain.model

data class MovieResults(
  val movies: List<Movie>,
  val totalPages: Int
)