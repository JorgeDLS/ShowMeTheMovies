package jdls.one.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jdls.one.data.cache.db.constants.MovieConstants
import jdls.one.data.model.CachedMovie

@Dao
abstract class CachedMovieDao {

  @Query(MovieConstants.GET_MOVIES)
  abstract fun getPopularTVShows(): List<CachedMovie>

  @Query(MovieConstants.DELETE_ALL_MOVIES)
  abstract fun clearMovies()

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract fun insertMovie(CachedMovie: CachedMovie)

}