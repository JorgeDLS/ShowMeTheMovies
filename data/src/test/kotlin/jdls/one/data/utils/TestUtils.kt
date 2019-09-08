package jdls.one.data.utils

import jdls.one.data.model.MovieSearchResults
import jdls.one.data.model.RawMovie
import jdls.one.domain.model.Movie
import jdls.one.domain.model.MovieResults

fun anyMovieSearchResults() = MovieSearchResults(1, listOf(anyRawMovie()), 40, 2)

fun anyRawMovie() = RawMovie(
  "",
  87.11,
  12,
  "",
  98.31,
  "",
  "",
  emptyList(),
  emptyList(),
  "",
  90,
  "Testeando a Forrester",
  "Testing Forrester"
)

fun anyMovieResults() = MovieResults(listOf(anyMovie()), 2)

fun anyMovie() = Movie(12, "Testeando a Forrester", 98.31, null)