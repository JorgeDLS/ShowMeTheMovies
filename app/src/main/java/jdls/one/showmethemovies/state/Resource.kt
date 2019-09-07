package jdls.one.showmethemovies.state

open class Resource<out T> constructor(
  val status: ResourceState,
  val data: T?,
  val message: String?
)

enum class ResourceState {
  LOADING, SUCCESS, ERROR
} 