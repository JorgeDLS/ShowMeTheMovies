package jdls.one.showmethemovies.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import jdls.one.domain.interactor.GetPopularTVShows
import jdls.one.showmethemovies.main.TVShowsListViewModel.GetPopularTVShowsSubscriber
import jdls.one.showmethemovies.state.ResourceState
import jdls.one.showmethemovies.utils.anyMovie
import jdls.one.showmethemovies.utils.anyMovieResults
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor
import org.mockito.Mock
import java.util.*

@RunWith(JUnit4::class)
class TVShowsListViewModelTest {

  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()

  @Mock
  lateinit var getPopularTVShows: GetPopularTVShows

  @Captor
  private lateinit var captor: KArgumentCaptor<GetPopularTVShowsSubscriber>

  private lateinit var tvShowsListViewModel: TVShowsListViewModel

  @Before
  fun setUp() {
    captor = argumentCaptor()
    getPopularTVShows = mock()
    tvShowsListViewModel = TVShowsListViewModel(getPopularTVShows)
  }

  @Test
  fun getPopularTVShowsExecutesUseCase() {
    tvShowsListViewModel.getPopularTVShows()

    verify(getPopularTVShows, times(1)).execute(any(), anyOrNull())
  }

  @Test
  fun getPopularTVShowsReturnsDataOnSuccess() {
    tvShowsListViewModel.getPopularTVShows()

    verify(getPopularTVShows).execute(captor.capture(), eq(Pair(Locale.getDefault().toString(), 1)))
    captor.firstValue.onSuccess(anyMovieResults())

    assert(tvShowsListViewModel.getPopularTVShows().value?.data == listOf(anyMovie()))
  }

  @Test
  fun getPopularTVShowsReturnsNoMessageOnSuccess() {
    tvShowsListViewModel.getPopularTVShows()

    verify(getPopularTVShows).execute(captor.capture(), eq(Pair(Locale.getDefault().toString(), 1)))
    captor.firstValue.onSuccess(anyMovieResults())

    assert(tvShowsListViewModel.getPopularTVShows().value?.message == null)
  }

  @Test
  fun getPopularTVShowsReturnsError() {
    tvShowsListViewModel.getPopularTVShows()

    verify(getPopularTVShows).execute(captor.capture(), eq(Pair(Locale.getDefault().toString(), 1)))
    captor.firstValue.onError(RuntimeException())

    assert(tvShowsListViewModel.getPopularTVShows().value?.status == ResourceState.ERROR)
  }

  @Test
  fun getPopularTVShowsFailsAndContainsNoData() {
    tvShowsListViewModel.getPopularTVShows()

    verify(getPopularTVShows).execute(captor.capture(), eq(Pair(Locale.getDefault().toString(), 1)))
    captor.firstValue.onError(RuntimeException())

    assert(tvShowsListViewModel.getPopularTVShows().value?.data == null)
  }

  @Test
  fun getPopularTVShowsFailsAndContainsMessage() {
    val errorMessage = "Error fetching data"
    tvShowsListViewModel.getPopularTVShows()

    verify(getPopularTVShows).execute(captor.capture(), eq(Pair(Locale.getDefault().toString(), 1)))
    captor.firstValue.onError(RuntimeException(errorMessage))

    assert(tvShowsListViewModel.getPopularTVShows().value?.message == errorMessage)
  }

  @Test
  fun getPopularTVShowsReturnsLoading() {
    tvShowsListViewModel.getPopularTVShows()

    assert(tvShowsListViewModel.getPopularTVShows().value?.status == ResourceState.LOADING)
  }

  @Test
  fun getPopularTVShowsContainsNoDataWhenLoading() {
    tvShowsListViewModel.getPopularTVShows()

    assert(tvShowsListViewModel.getPopularTVShows().value?.data == null)
  }

  @Test
  fun getPopularTVShowsContainsNoMessageWhenLoading() {
    tvShowsListViewModel.getPopularTVShows()

    assert(tvShowsListViewModel.getPopularTVShows().value?.data == null)
  }

  @Test
  fun requestMoreDataIncrementsCurrentPage() {
    val currentPage = tvShowsListViewModel.currentPage
    tvShowsListViewModel.requestMoreData()

    assert(currentPage + 1 == tvShowsListViewModel.currentPage)
  }
}