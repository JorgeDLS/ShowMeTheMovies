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
    SingleUseCase<List<Movie>, Void?>(threadExecutor, postExecutionThread) {

  public override fun buildUseCaseObservable(params: Void?): Single<List<Movie>> {
    return moviesRepository.getPopularTVShows()
  }
}