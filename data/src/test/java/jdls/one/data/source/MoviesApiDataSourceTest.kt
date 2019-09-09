package jdls.one.data.source

import jdls.one.data.mapper.RemoteMapper
import jdls.one.data.service.MoviesServiceFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.HttpException
import java.util.*

@RunWith(JUnit4::class)
class MoviesApiDataSourceTest {

  private lateinit var moviesService: MoviesServiceFactory.MoviesService
  private lateinit var remoteMapper: RemoteMapper
  private lateinit var moviesApiDataSource: MoviesApiDataSource

  @Before
  fun setUp() {
    moviesService = MoviesServiceFactory.makeMoviesService("5d967c7c335764f39b1efbe9c5de9760", true)
    remoteMapper = RemoteMapper()
    moviesApiDataSource = MoviesApiDataSource(moviesService, remoteMapper)
  }

  @Test
  fun getPopularTVShowsCompletes() {
    val testObserver =
      moviesApiDataSource.getPopularTVShows(Locale.getDefault().toString(), 1).test()

    testObserver.assertComplete()
    testObserver.assertNoErrors()
    testObserver.assertValueCount(1)
    testObserver.dispose()
  }

  @Test
  fun getPopularTVShowsFailsWhenParamsAreIncorrect() {
    val testObserver =
      moviesApiDataSource.getPopularTVShows(Locale.getDefault().toString(), 18272).test()

    testObserver.assertNotComplete()
    testObserver.assertError(HttpException::class.java)
    testObserver.dispose()
  }
}