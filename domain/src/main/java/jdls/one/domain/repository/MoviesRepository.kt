package jdls.one.domain.repository

import io.reactivex.Single
import jdls.one.domain.model.Movie

interface MoviesRepository {
  fun getPopularTVShows(language: String, page: Int): Single<List<Movie>>
}