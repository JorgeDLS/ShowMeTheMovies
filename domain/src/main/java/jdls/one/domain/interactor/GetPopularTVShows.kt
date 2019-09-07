package jdls.one.domain.interactor

import io.reactivex.Single
import jdls.one.domain.executor.PostExecutionThread
import jdls.one.domain.executor.ThreadExecutor
import jdls.one.domain.model.Movie
import jdls.one.domain.repository.MoviesRepository
import javax.inject.Inject

open class GetPopularTVShows @Inject constructor(
    private val moviesRepository: MoviesRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) :
    SingleUseCase<List<Movie>, Pair<String, Int>>(threadExecutor, postExecutionThread) {

  public override fun buildUseCaseObservable(params: Pair<String, Int>): Single<List<Movie>> {
    return moviesRepository.getPopularTVShows(params.first, params.second)
  }
}