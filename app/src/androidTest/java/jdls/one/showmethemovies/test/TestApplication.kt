package jdls.one.showmethemovies.test

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import jdls.one.showmethemovies.di.DaggerTestApplicationComponent
import jdls.one.showmethemovies.di.TestApplicationComponent
import javax.inject.Inject

class TestApplication : Application(), HasAndroidInjector {

  @Inject
  lateinit var injector: DispatchingAndroidInjector<Any>

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

  override fun androidInjector(): AndroidInjector<Any> {
    return injector
  }

}