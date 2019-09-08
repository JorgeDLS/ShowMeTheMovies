package jdls.one.showmethemovies.utils

import jdls.one.domain.model.Movie
import jdls.one.domain.model.MovieResults

fun anyMovieResults(): MovieResults = MovieResults(listOf(anyMovie()), 1)

fun anyMovie(): Movie = Movie(12, "Testeando a Forrester", 98.31, null)