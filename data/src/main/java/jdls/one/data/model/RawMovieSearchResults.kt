package jdls.one.data.model

data class RawMovieSearchResults(
    val page: Int,
    val results: List<RawMovie>,
    val totalResults: Int,
    val totalPages: Int
)