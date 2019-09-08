package jdls.one.domain.interactor

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import jdls.one.domain.executor.PostExecutionThread
import jdls.one.domain.executor.ThreadExecutor
import jdls.one.domain.model.MovieResults
import jdls.one.domain.repository.MoviesRepository
import jdls.one.domain.utils.anyMovieResults
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetPopularTVShowsTest {

  private lateinit var getPopularTVShows: GetPopularTVShows
  private lateinit var moviesRepository: MoviesRepository
  private lateinit var threadExecutor: ThreadExecutor
  private lateinit var postExecutionThread: PostExecutionThread

  @Before
  fun setUp() {
    moviesRepository = mock()
    threadExecutor = mock()
    postExecutionThread = mock()
    getPopularTVShows = GetPopularTVShows(moviesRepository, threadExecutor, postExecutionThread)
  }

  @Test
  fun buildSingleUseCaseCallsRepository() {
    getPopularTVShows.buildUseCaseObservable(Pair("en_US", 1))

    verify(moviesRepository).getPopularTVShows("en_US", 1)
  }

  @Test
  fun buildUseCaseObservableCompletes() {
    stubMoviesRepositoryGetPopularTVShows(Single.just(anyMovieResults()))

    val testObserver = getPopularTVShows.buildUseCaseObservable(Pair("en_US", 1)).test()

    testObserver.assertComplete()
    testObserver.dispose()
  }

  @Test
  fun buildUseCaseObservableReturnsData() {
    stubMoviesRepositoryGetPopularTVShows(Single.just(anyMovieResults()))

    val testObserver = getPopularTVShows.buildUseCaseObservable(Pair("en_US", 1)).test()

    testObserver.assertValue(anyMovieResults())
    testObserver.dispose()
  }

  private fun stubMoviesRepositoryGetPopularTVShows(single: Single<MovieResults>) {
    whenever(moviesRepository.getPopularTVShows("en_US", 1)).thenReturn(single)
  }
}