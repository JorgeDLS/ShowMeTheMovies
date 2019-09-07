package jdls.one.showmethemovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelFactory
@Inject constructor(private val creators: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) :
  ViewModelProvider.Factory {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    var creator: Provider<out ViewModel>? = creators[modelClass]
    if (creator == null) {
      for ((key, value) in creators.entries) {
        if (modelClass.isAssignableFrom(key)) {
          creator = value
          break
        }
      }
    }
    requireNotNull(creator) { "Unknown model class $modelClass" }
    try {
      return creator.get() as T
    } catch (e: Exception) {
      throw RuntimeException(e)
    }
  }
}