package jdls.one.showmethemovies.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import jdls.one.domain.model.Movie
import jdls.one.domain.model.MovieResults

fun anyMovieResults(): MovieResults = MovieResults(listOf(anyMovie()), 1)

fun anyMovie(): Movie = Movie(12, "Testeando a Forrester", 98.31, null)

/**
 * Will create new [ViewModelStore], add view model into it using [ViewModelProvider]
 * and then call [ViewModelStore.clear], that will cause [ViewModel.onCleared] to be called
 */
fun ViewModel.callOnCleared() {
  val viewModelStore = ViewModelStore()
  val viewModelProvider = ViewModelProvider(viewModelStore, object : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = this@callOnCleared as T
  })
  viewModelProvider.get(this@callOnCleared::class.java)

  //Run 2
  viewModelStore.clear()//To call clear() in ViewModel
}