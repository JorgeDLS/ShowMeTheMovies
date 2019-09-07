package jdls.one.showmethemovies.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import jdls.one.domain.executor.PostExecutionThread
import jdls.one.showmethemovies.UiThread
import jdls.one.showmethemovies.ViewModelFactory
import jdls.one.showmethemovies.main.MainActivity
import jdls.one.showmethemovies.main.TVShowsListViewModel
import kotlin.reflect.KClass

/**
 * Annotation class to identify view models by classname.
 */
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModule {

  @Binds
  abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

  @Binds
  @IntoMap
  @ViewModelKey(TVShowsListViewModel::class)
  abstract fun bindTVShowsListViewModel(viewModel: TVShowsListViewModel): ViewModel

  @Binds
  abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

  @ContributesAndroidInjector
  abstract fun contributeMainActivity(): MainActivity
}