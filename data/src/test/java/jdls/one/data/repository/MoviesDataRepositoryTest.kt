package jdls.one.data.repository

import com.nhaarman.mockitokotlin2.*
import io.reactivex.Observable
import io.reactivex.Single
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

  private lateinit var moviesRemote: MoviesRemote
  private lateinit var moviesCache: MoviesCache
  private lateinit var moviesDataRepository: MoviesDataRepository


  @Before
  fun setUp() {
    moviesRemote = mock()
    moviesCache = mock()
    moviesDataRepository = MoviesDataRepository(moviesRemote, moviesCache)
  }

  @Test
  fun getPopularTVShowsCompletesUsingRemote() {
    whenever(moviesRemote.getPopularTVShows(Locale.getDefault().toString(), 1))
      .doReturn(Single.just(anyMovieResults()))

    val testObserver =
      moviesDataRepository.getPopularTVShows(Locale.getDefault().toString(), 1).test()

    testObserver.assertComplete()
    testObserver.assertNoErrors()
    testObserver.assertValueCount(1)
    testObserver.dispose()
    verify(moviesRemote, atLeastOnce()).getPopularTVShows(Locale.getDefault().toString(), 1)
  }

  @Test
  fun getPopularTVShowsCompletesUsingCache() {
    val errorSingle = Observable.error<MovieResults>(ConnectException()).singleOrError()
    whenever(moviesRemote.getPopularTVShows(Locale.getDefault().toString(), 1))
      .doReturn(errorSingle)
    whenever(moviesCache.getPopularTVShows()).doReturn(Single.just(anyMovieResults()))

    val testObserver =
      moviesDataRepository.getPopularTVShows(Locale.getDefault().toString(), 1).test()

    testObserver.assertResult(anyMovieResults())
    testObserver.dispose()
    verify(moviesCache, atLeastOnce()).getPopularTVShows()
  }

  @Test
  fun getPopularTVShowsReturnsErrorWhenExceptionIsNotConnectExceptionNorUnknownHostException() {
    val errorSingle = Observable.error<MovieResults>(RuntimeException()).singleOrError()
    whenever(moviesRemote.getPopularTVShows(Locale.getDefault().toString(), 1))
      .doReturn(errorSingle)

    val testObserver =
      moviesDataRepository.getPopularTVShows(Locale.getDefault().toString(), 1).test()

    testObserver.assertError(RuntimeException::class.java)
    testObserver.dispose()
    verify(moviesCache, never()).getPopularTVShows()
  }
}