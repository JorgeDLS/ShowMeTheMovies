package jdls.one.showmethemovies.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class TestApplicationModule {

  @Binds
  abstract fun bindContext(application: Application): Context
}