package jdls.one.data.model

import com.squareup.moshi.Json

data class RawMovie(
  @Json(name = "poster_path") val posterPath: String? = null,
  val popularity: Double,
  val id: Int,
  @Json(name = "backdrop_path") val backdropPath: String? = null,
  @Json(name = "vote_average") val voteAverage: Double,
  val overview: String? = null,
  @Json(name = "first_air_date") val firstAirDate: String,
  @Json(name = "origin_country") val originCountry: List<String>,
  @Json(name = "genre_ids") val genreIds: List<Int>,
  @Json(name = "original_language") val originalLanguage: String,
  @Json(name = "vote_count") val voteCount: Int,
  val name: String,
  @Json(name = "original_name") val originalName: String
)
