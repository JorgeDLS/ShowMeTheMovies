package jdls.one.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import jdls.one.data.cache.db.constants.MovieConstants

@Entity(tableName = MovieConstants.TABLE_NAME)
data class CachedMovie(

  @PrimaryKey
  var id: Int,
  var title: String,
  val voteAverage: Double,
  val backDropUrl: String?
)