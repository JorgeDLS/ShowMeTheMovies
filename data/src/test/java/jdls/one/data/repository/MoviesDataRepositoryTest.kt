package jdls.one.data.repository

import com.nhaarman.mockitokotlin2.*
import io.reactivex.Observable
import io.reactivex.Single
import jdls.one.data.source.MoviesApiDataSource
import jdls.one.data.source.MoviesCacheDataSource
import jdls.one.data.utils.anyMovieResults
import jdls.one.domain.model.MovieResults
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.net.ConnectException
import java.util.*

@RunWith(JUnit4::class)
class MoviesDataRepositoryTest {

  private lateinit var apiDataSource: MoviesApiDataSource
  private lateinit var cacheDataSource: MoviesCacheDataSource
  private lateinit var moviesDataRepository: MoviesDataRepository


  @Before
  fun setUp() {
    apiDataSource = mock()
    cacheDataSource = mock()
    moviesDataRepository = MoviesDataRepository(apiDataSource, cacheDataSource)
  }

  @Test
  fun getPopularTVShowsCompletesUsingApiDataSource() {
    whenever(apiDataSource.getPopularTVShows(Locale.getDefault().toString(), 1))
      .doReturn(Single.just(anyMovieResults()))

    val testObserver =
      moviesDataRepository.getPopularTVShows(Locale.getDefault().toString(), 1).test()

    testObserver.assertComplete()
    testObserver.assertNoErrors()
    testObserver.assertValueCount(1)
    testObserver.dispose()
    verify(apiDataSource, atLeastOnce()).getPopularTVShows(Locale.getDefault().toString(), 1)
  }

  @Test
  fun getPopularTVShowsCompletesUsingCacheDataSource() {
    val errorSingle = Observable.error<MovieResults>(ConnectException()).singleOrError()
    whenever(apiDataSource.getPopularTVShows(Locale.getDefault().toString(), 1))
      .doReturn(errorSingle)
    whenever(cacheDataSource.getPopularTVShows()).doReturn(Single.just(anyMovieResults()))

    val testObserver =
      moviesDataRepository.getPopularTVShows(Locale.getDefault().toString(), 1).test()

    testObserver.assertResult(anyMovieResults())
    testObserver.dispose()
    verify(cacheDataSource, atLeastOnce()).getPopularTVShows()
  }

  @Test
  fun getPopularTVShowsReturnsErrorWhenExceptionIsNotConnectExceptionNorUnknownHostException() {
    val errorSingle = Observable.error<MovieResults>(RuntimeException()).singleOrError()
    whenever(apiDataSource.getPopularTVShows(Locale.getDefault().toString(), 1))
      .doReturn(errorSingle)

    val testObserver =
      moviesDataRepository.getPopularTVShows(Locale.getDefault().toString(), 1).test()

    testObserver.assertError(RuntimeException::class.java)
    testObserver.dispose()
    verify(cacheDataSource, never()).getPopularTVShows()
  }
}