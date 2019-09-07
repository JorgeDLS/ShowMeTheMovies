package jdls.one.showmethemovies.main

import androidx.lifecycle.ViewModel
import jdls.one.domain.interactor.GetPopularTVShows
import javax.inject.Inject

open class TVShowsListViewModel @Inject internal constructor(
  private val getPopularTVShows: GetPopularTVShows
) : ViewModel()