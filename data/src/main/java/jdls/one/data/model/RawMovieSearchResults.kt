package jdls.one.data.model

import com.squareup.moshi.Json

data class RawMovieSearchResults(
  val page: Int,
  val results: List<RawMovie>,
  @Json(name = "total_results") val totalResults: Int,
  @Json(name = "total_pages") val totalPages: Int
)