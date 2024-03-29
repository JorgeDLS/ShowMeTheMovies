package jdls.one.data.mapper

import jdls.one.data.model.RawMovieSearchResults
import jdls.one.data.utils.anyMovieResults
import jdls.one.data.utils.anyMovieSearchResults
import jdls.one.domain.model.MovieResults
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@RunWith(JUnit4::class)
class RemoteMapperTest {

  private lateinit var remoteMapper: RemoteMapper

  @Before
  fun setUp() {
    remoteMapper = RemoteMapper()
  }

  @Test
  fun properRawValueMapsToValue() {
    val movieSearchResults = anyMovieSearchResults()

    val movieResults = remoteMapper.map(movieSearchResults)

    assertDataIsEqual(movieSearchResults, movieResults)
  }

  @Test
  fun reverseMapThrowsUnsupportedOperationException() {
    assertFailsWith<UnsupportedOperationException> { remoteMapper.reverseMap(anyMovieResults()) }
  }

  private fun assertDataIsEqual(
    rawMovieSearchResults: RawMovieSearchResults,
    movieResults: MovieResults
  ) {
    assertEquals(rawMovieSearchResults.totalPages, movieResults.totalPages)
    assertEquals(rawMovieSearchResults.results[0].id, movieResults.movies[0].id)
    assertEquals(rawMovieSearchResults.results[0].name, movieResults.movies[0].title)
    assertEquals(rawMovieSearchResults.results[0].voteAverage, movieResults.movies[0].voteAverage)
    assertEquals(
      remoteMapper.buildBackdropCompletePath(rawMovieSearchResults.results[0].backdropPath),
      movieResults.movies[0].backDropUrl
    )
  }

}