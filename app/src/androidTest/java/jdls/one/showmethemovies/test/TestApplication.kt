package jdls.one.showmethemovies.test

import android.app.Activity
import android.app.Application
import androidx.test.core.app.ApplicationProvider
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import jdls.one.showmethemovies.di.DaggerTestApplicationComponent
import jdls.one.showmethemovies.di.TestApplicationComponent
import javax.inject.Inject

class TestApplication : Application(), HasActivityInjector {

  @Inject
  lateinit var injector: DispatchingAndroidInjector<Activity>

  private lateinit var appComponent: TestApplicationComponent

  override fun onCreate() {
    super.onCreate()
    appComponent = DaggerTestApplicationComponent.builder().application(this).build()
    appComponent.inject(this)
  }

  companion object {

    fun appComponent(): TestApplicationComponent {
      return ApplicationProvider.getApplicationContext<TestApplication>().appComponent
    }

  }

  override fun activityInjector(): AndroidInjector<Activity> {
    return injector
  }

}