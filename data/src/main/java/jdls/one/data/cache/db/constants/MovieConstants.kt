package jdls.one.data.cache.db.constants

object MovieConstants {

  const val TABLE_NAME = "movies"

  const val GET_MOVIES = "SELECT * FROM $TABLE_NAME"

  const val DELETE_ALL_MOVIES = "DELETE FROM $TABLE_NAME"

}