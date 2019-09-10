package jdls.one.data.source

import androidx.room.Room
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.verify
import jdls.one.data.cache.db.MovieDatabase
import jdls.one.data.mapper.CacheMapper
import jdls.one.data.utils.anyMovie
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
  private var entityMapper = CacheMapper()

  private var moviesCacheDataSource = MoviesCacheDataSource(movieDatabase, entityMapper)

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
    entityMapper = spy(entityMapper)
    moviesCacheDataSource = MoviesCacheDataSource(movieDatabase, entityMapper)

    moviesCacheDataSource.saveMovie(anyMovie())
    val testObserver = moviesCacheDataSource.getPopularTVShows().test()

    testObserver.assertComplete()
    testObserver.dispose()
    verify(entityMapper, atLeastOnce()).reverseMap(any())
  }

  @Test
  fun getDatabaseReturnsTheDatabase() {
    assertEquals(movieDatabase, moviesCacheDataSource.getDatabase())
  }
}