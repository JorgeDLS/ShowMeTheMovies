package jdls.one.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import jdls.one.cache.dao.CachedMovieDao
import jdls.one.cache.model.CachedMovie
import javax.inject.Inject

@Database(entities = [CachedMovie::class], version = 1, exportSchema = false)
abstract class MovieDatabase @Inject constructor() : RoomDatabase() {

  abstract fun cachedMovieDao(): CachedMovieDao

  private var INSTANCE: MovieDatabase? = null

  private val sLock = Any()

  fun getInstance(context: Context): MovieDatabase {
    if (INSTANCE == null) {
      synchronized(sLock) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(
            context.applicationContext,
            MovieDatabase::class.java, "movies.db"
          )
            .build()
        }
        return INSTANCE!!
      }
    }
    return INSTANCE!!
  }

}