package jdls.one.showmethemovies.test.utils

import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import jdls.one.domain.model.Movie
import jdls.one.domain.model.MovieResults
import org.hamcrest.Matcher


fun anyMovieResults(): MovieResults = MovieResults(listOf(anyMovie()), 2)

fun aLotOfMovieResults(): MovieResults = MovieResults((1..20).map { anyMovie() }.toList(), 2)

fun anyMovie(): Movie = Movie(12, anyMovieTitle(), 98.31, null)

fun anyMovieTitle() = "Testeando a Forrester"

fun atPosition(position: Int, @NonNull itemMatcher: Matcher<View>): Matcher<View> {
  return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
    override fun describeTo(description: org.hamcrest.Description?) {
      description?.appendText("has item at position $position: ")
      itemMatcher.describeTo(description)
    }

    override fun matchesSafely(view: RecyclerView): Boolean {
      val viewHolder = view.findViewHolderForAdapterPosition(position)
        ?: return false
      return itemMatcher.matches(viewHolder.itemView)
    }
  }
}