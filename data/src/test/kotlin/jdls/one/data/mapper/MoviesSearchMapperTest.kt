package jdls.one.data.mapper

import jdls.one.data.model.MovieSearchResults
import jdls.one.data.utils.anyMovieSearchResults
import jdls.one.domain.model.MovieResults
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class MoviesSearchMapperTest {

  private lateinit var moviesSearchMapper: MoviesSearchMapper

  @Before
  fun setUp() {
    moviesSearchMapper = MoviesSearchMapper()
  }

  @Test
  fun properRawValueMapsToValue() {
    val movieSearchResults = anyMovieSearchResults()

    val movieResults = moviesSearchMapper.mapFromApi(movieSearchResults)

    assertDataIsEqual(movieSearchResults, movieResults)
  }

  private fun assertDataIsEqual(
    movieSearchResults: MovieSearchResults,
    movieResults: MovieResults
  ) {
    assertEquals(movieSearchResults.totalPages, movieResults.totalPages)
    assertEquals(movieSearchResults.results[0].id, movieResults.movies[0].id)
    assertEquals(movieSearchResults.results[0].name, movieResults.movies[0].title)
    assertEquals(movieSearchResults.results[0].voteAverage, movieResults.movies[0].voteAverage)
    assertEquals(
      moviesSearchMapper.buildBackdropCompletePath(movieSearchResults.results[0].backdropPath),
      movieResults.movies[0].backDropUrl
    )
  }

}