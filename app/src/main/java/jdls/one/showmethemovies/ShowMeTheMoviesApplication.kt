package jdls.one.showmethemovies

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import jdls.one.showmethemovies.di.DaggerApplicationComponent
import javax.inject.Inject

class ShowMeTheMoviesApplication : Application(), HasActivityInjector {

  @Inject
  lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    super.onCreate()
    DaggerApplicationComponent
      .builder()
      .application(this)
      .build()
      .inject(this)
  }

  override fun activityInjector(): AndroidInjector<Activity> {
    return activityDispatchingAndroidInjector
  }

}
