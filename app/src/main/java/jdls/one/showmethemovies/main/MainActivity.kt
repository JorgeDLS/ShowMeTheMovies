package jdls.one.showmethemovies.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import jdls.one.domain.model.Movie
import jdls.one.showmethemovies.R
import jdls.one.showmethemovies.ViewModelFactory
import jdls.one.showmethemovies.state.ResourceState
import jdls.one.showmethemovies.util.action
import jdls.one.showmethemovies.util.gone
import jdls.one.showmethemovies.util.showSnackbar
import jdls.one.showmethemovies.util.visible
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  private lateinit var viewModel: TVShowsListViewModel

  private val moviesAdapter = MoviesAdapter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    AndroidInjection.inject(this)

    viewModel = ViewModelProviders.of(this, viewModelFactory)
      .get(TVShowsListViewModel::class.java)

    setupRecyclerView()
  }

  override fun onStart() {
    super.onStart()
    viewModel.getPopularTVShows().observe(this, Observer {
      if (it != null) this.handleDataState(it.status, it.data, it.message)
    })
  }

  private fun handleDataState(resourceState: ResourceState, data: List<Movie>?, message: String?) {
    when (resourceState) {
      ResourceState.LOADING -> setupScreenForLoadingState()
      ResourceState.SUCCESS -> setupScreenForSuccess(data!!)
      ResourceState.ERROR -> setupScreenForError(message!!)
    }
  }

  private fun setupScreenForLoadingState() {
    progress.visible()
    recyclerView.gone()
    viewEmpty.gone()
  }

  private fun setupScreenForSuccess(data: List<Movie>) {
    progress.gone()
    if (data.isNotEmpty()) {
      moviesAdapter.movieList = data
      moviesAdapter.notifyDataSetChanged()
      recyclerView.visible()
    } else {
      viewEmpty.visible()
    }
  }

  private fun setupScreenForError(message: String) {
    Log.e("Error", message) //In a real project we should send this error to Crashlytics.
    progress.gone()
    recyclerView.gone()
    viewEmpty.gone()
    recyclerView.showSnackbar(R.string.labelErrorResult) {
      action(R.string.labelTryAgain) { viewModel.fetchPopularTVShows() }
    }
  }

  private fun setupRecyclerView() {
    recyclerView.setHasFixedSize(true)
    recyclerView.setItemViewCacheSize(20)
    recyclerView.adapter = moviesAdapter
  }
}
