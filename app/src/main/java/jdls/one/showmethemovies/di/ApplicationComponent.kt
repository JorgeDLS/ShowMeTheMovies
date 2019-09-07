package jdls.one.showmethemovies.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import jdls.one.showmethemovies.ShowMeTheMoviesApplication
import jdls.one.showmethemovies.di.module.ApplicationModule
import jdls.one.showmethemovies.di.module.CacheModule
import jdls.one.showmethemovies.di.module.DataModule
import jdls.one.showmethemovies.di.module.ViewModule
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    ApplicationModule::class,
    AndroidSupportInjectionModule::class,
    DataModule::class,
    CacheModule::class,
    ViewModule::class]
)
interface ApplicationComponent {

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    fun build(): ApplicationComponent
  }

  fun inject(app: ShowMeTheMoviesApplication)

}
