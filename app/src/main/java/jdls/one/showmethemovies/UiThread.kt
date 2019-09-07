package jdls.one.showmethemovies

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import jdls.one.domain.executor.PostExecutionThread
import javax.inject.Inject

class UiThread @Inject internal constructor() : PostExecutionThread {

  override val scheduler: Scheduler
    get() = AndroidSchedulers.mainThread()

}