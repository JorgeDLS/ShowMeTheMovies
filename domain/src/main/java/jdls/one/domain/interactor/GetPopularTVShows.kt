package jdls.one.domain.interactor

import io.reactivex.Single
import jdls.one.domain.executor.PostExecutionThread
import jdls.one.domain.executor.ThreadExecutor
import jdls.one.domain.model.MovieResults
import jdls.one.domain.repository.MoviesRepository
import javax.inject.Inject

open class GetPopularTVShows @Inject constructor(
  private val moviesRepository: MoviesRepository,
  threadExecutor: ThreadExecutor,
  postExecutionThread: PostExecutionThread
) :
  SingleUseCase<MovieResults, Pair<String, Int>>(threadExecutor, postExecutionThread) {

  public override fun buildUseCaseObservable(params: Pair<String, Int>): Single<MovieResults> {
    return moviesRepository.getPopularTVShows(params.first, params.second)
  }
}