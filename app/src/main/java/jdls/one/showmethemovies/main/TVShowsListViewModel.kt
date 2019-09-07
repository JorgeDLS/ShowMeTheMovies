package jdls.one.showmethemovies.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableSingleObserver
import jdls.one.domain.interactor.GetPopularTVShows
import jdls.one.domain.model.Movie
import jdls.one.showmethemovies.state.Resource
import jdls.one.showmethemovies.state.ResourceState
import javax.inject.Inject

open class TVShowsListViewModel @Inject internal constructor(
  private val getPopularTVShows: GetPopularTVShows
) : ViewModel() {

  private val moviesLiveData: MutableLiveData<Resource<List<Movie>>> =
    MutableLiveData()

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
      Pair("en_US", 1)
    ) //Hardcoded for now
  }

  inner class GetPopularTVShowsSubscriber : DisposableSingleObserver<List<Movie>>() {
    override fun onSuccess(t: List<Movie>) {
      moviesLiveData.postValue(Resource(ResourceState.SUCCESS, t, null))
    }

    override fun onError(exception: Throwable) {
      moviesLiveData.postValue(Resource(ResourceState.ERROR, null, exception.message))
    }

  }
}