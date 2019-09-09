package jdls.one.showmethemovies.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import jdls.one.domain.executor.PostExecutionThread
import jdls.one.domain.repository.MoviesRepository
import jdls.one.showmethemovies.di.module.TestApplicationModule
import jdls.one.showmethemovies.di.module.TestDataModule
import jdls.one.showmethemovies.di.module.ViewModule
import jdls.one.showmethemovies.test.TestApplication
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    TestApplicationModule::class,
    AndroidSupportInjectionModule::class,
    TestDataModule::class,
    ViewModule::class]
)
interface TestApplicationComponent : ApplicationComponent {

  fun moviesRepository(): MoviesRepository

  fun postExecutionThread(): PostExecutionThread

  fun inject(application: TestApplication)

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    fun build(): TestApplicationComponent
  }

}