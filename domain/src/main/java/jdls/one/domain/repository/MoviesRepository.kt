package jdls.one.domain.repository

import io.reactivex.Single
import jdls.one.domain.model.Movie

interface MoviesRepository {
  fun getPopularTVShows(): Single<List<Movie>>
}