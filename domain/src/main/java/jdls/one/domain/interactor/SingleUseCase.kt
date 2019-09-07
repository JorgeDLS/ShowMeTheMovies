package jdls.one.domain.interactor

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import jdls.one.domain.executor.PostExecutionThread
import jdls.one.domain.executor.ThreadExecutor

/**
 * Abstract class for a UseCase that returns an instance of a [Single].
 */
abstract class SingleUseCase<T, in Params> constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

  private val disposables = CompositeDisposable()

  /**
   * Builds a [Single] which will be used when the current [SingleUseCase] is executed.
   */
  protected abstract fun buildUseCaseObservable(params: Params): Single<T>

  /**
   * Executes the current use case.
   */
  open fun execute(observer: DisposableSingleObserver<T>, params: Params) {
    val observable = this.buildUseCaseObservable(params)
        .subscribeOn(Schedulers.from(threadExecutor))
        .observeOn(postExecutionThread.scheduler) as Single<T>
    addDisposable(observable.subscribeWith(observer))
  }

  /**
   * Dispose from current [CompositeDisposable].
   */
  fun dispose() {
    if (!disposables.isDisposed) disposables.dispose()
  }

  /**
   * Dispose from current [CompositeDisposable].
   */
  private fun addDisposable(disposable: Disposable) {
    disposables.add(disposable)
  }

}