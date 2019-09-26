package jdls.one.data.source

import androidx.room.Room
import com.nhaarman.mockitokotlin2.*
import jdls.one.data.cache.db.MovieDatabase
import jdls.one.data.mapper.CacheMapper
import jdls.one.data.utils.anyCachedMovie
import jdls.one.data.utils.anyMovie
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [23], manifest = Config.NONE)
class MoviesCacheDataSourceTest {

  private var movieDatabase =
    Room.inMemoryDatabaseBuilder(RuntimeEnvironment.application, MovieDatabase::class.java)
      .allowMainThreadQueries().build()
  private var cacheMapper: CacheMapper = mock()

  private var moviesCacheDataSource = MoviesCacheDataSource(movieDatabase, cacheMapper)

  @Before
  fun setUp() {
    whenever(cacheMapper.map(any())).doReturn(anyCachedMovie())
    whenever(cacheMapper.reverseMap(any())).doReturn(anyMovie())
  }

  @Test
  fun saveMovieCompletes() {
    val movie = anyMovie()

    val testObserver = moviesCacheDataSource.saveMovie(movie).test()

    testObserver.assertComplete()
    testObserver.dispose()
  }

  @Test
  fun deleteMoviesCompletes() {
    val testObserver = moviesCacheDataSource.deleteMovies().test()

    testObserver.assertComplete()
    testObserver.dispose()
  }

  @Test
  fun getPopularTVShowsCompletes() {
    val testObserver = moviesCacheDataSource.getPopularTVShows().test()

    testObserver.assertComplete()
    testObserver.dispose()
  }

  @Test
  fun getPopularTVShowsCallsEntityMapperReverseMap() {
    moviesCacheDataSource.saveMovie(anyMovie())
    val testObserver = moviesCacheDataSource.getPopularTVShows().test()

    testObserver.assertComplete()
    testObserver.dispose()
    verify(cacheMapper, atLeastOnce()).reverseMap(any())
  }

  @Test
  fun getDatabaseReturnsTheDatabase() {
    assertEquals(movieDatabase, moviesCacheDataSource.getDatabase())
  }
}