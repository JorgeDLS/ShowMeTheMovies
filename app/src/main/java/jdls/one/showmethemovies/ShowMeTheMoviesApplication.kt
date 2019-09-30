package jdls.one.showmethemovies

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import jdls.one.showmethemovies.di.DaggerApplicationComponent
import javax.inject.Inject

class ShowMeTheMoviesApplication : Application(), HasAndroidInjector {

  @Inject
  lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

  override fun onCreate() {
    super.onCreate()
    DaggerApplicationComponent
      .builder()
      .application(this)
      .build()
      .inject(this)
  }

  override fun androidInjector(): AndroidInjector<Any> {
    return activityDispatchingAndroidInjector
  }

}
