package jdls.one.showmethemovies.di.module

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import jdls.one.domain.executor.PostExecutionThread
import jdls.one.showmethemovies.UiThread
import jdls.one.showmethemovies.main.MainActivity

@Module
abstract class ViewModule {

  @Binds
  abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

  @ContributesAndroidInjector
  abstract fun contributeMainActivity(): MainActivity
}