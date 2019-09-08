package jdls.one.showmethemovies.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableSingleObserver
import jdls.one.domain.interactor.GetPopularTVShows
import jdls.one.domain.model.Movie
import jdls.one.domain.model.MovieResults
import jdls.one.showmethemovies.state.Resource
import jdls.one.showmethemovies.state.ResourceState
import java.util.*
import javax.inject.Inject

open class TVShowsListViewModel @Inject internal constructor(
  private val getPopularTVShows: GetPopularTVShows
) : ViewModel() {

  private val moviesLiveData: MutableLiveData<Resource<List<Movie>>> =
    MutableLiveData()
  private var currentPage: Int = 1
  private var totalPages: Int = 1

  init {
    fetchPopularTVShows()
  }

  override fun onCleared() {
    getPopularTVShows.dispose()
    super.onCleared()
  }

  fun getPopularTVShows(): LiveData<Resource<List<Movie>>> {
    return moviesLiveData
  }

  fun fetchPopularTVShows() {
    moviesLiveData.postValue(Resource(ResourceState.LOADING, null, null))
    return getPopularTVShows.execute(
      GetPopularTVShowsSubscriber(),
      Pair(Locale.getDefault().toString(), currentPage)
    )
  }

  fun requestMoreData() {
    currentPage++
    if (currentPage <= totalPages) fetchPopularTVShows()
  }

  inner class GetPopularTVShowsSubscriber : DisposableSingleObserver<MovieResults>() {
    override fun onSuccess(movieResults: MovieResults) {
      totalPages = movieResults.totalPages
      moviesLiveData.postValue(Resource(ResourceState.SUCCESS, movieResults.movies, null))
    }

    override fun onError(exception: Throwable) {
      moviesLiveData.postValue(Resource(ResourceState.ERROR, null, exception.message))
    }

  }
}