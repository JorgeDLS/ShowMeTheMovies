package jdls.one.domain.repository

import io.reactivex.Single
import jdls.one.domain.model.Movie
import jdls.one.domain.model.MovieResults

interface MoviesRepository {
  fun getPopularTVShows(language: String, page: Int): Single<MovieResults>
}