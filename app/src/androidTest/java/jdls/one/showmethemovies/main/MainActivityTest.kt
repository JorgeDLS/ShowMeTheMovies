package jdls.one.showmethemovies.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import jdls.one.domain.model.MovieResults
import jdls.one.showmethemovies.R
import jdls.one.showmethemovies.test.TestApplication
import jdls.one.showmethemovies.test.utils.anyMovieResults
import jdls.one.showmethemovies.test.utils.anyMovieTitle
import jdls.one.showmethemovies.test.utils.atPosition
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@Suppress("ANNOTATION_TARGETS_NON_EXISTENT_ACCESSOR")
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

  @get:Rule
  @JvmField
  val activityTestRule: ActivityTestRule<MainActivity> =
    ActivityTestRule(MainActivity::class.java, false, false)

  @Test
  fun activityLaunches() {
    stubMoviesRepositoryGetPopularTVShows(Single.just(anyMovieResults()))

    activityTestRule.launchActivity(null)
  }

  @Test
  fun recyclerViewIsShownIfThereAreItems() {
    stubMoviesRepositoryGetPopularTVShows(Single.just(anyMovieResults()))

    activityTestRule.launchActivity(null)

    onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
  }

  @Test
  fun recyclerViewIsFilledWithItems() {
    stubMoviesRepositoryGetPopularTVShows(Single.just(anyMovieResults()))

    activityTestRule.launchActivity(null)

    onView(withId(R.id.recyclerView)).check(
      matches(
        atPosition(
          0,
          hasDescendant(withText(anyMovieTitle()))
        )
      )
    )
  }

  private fun stubMoviesRepositoryGetPopularTVShows(single: Single<MovieResults>) {
    whenever(
      TestApplication.appComponent().moviesRepository().getPopularTVShows(
        Locale.getDefault().toString(),
        1
      )
    ).doReturn(single)
  }
}