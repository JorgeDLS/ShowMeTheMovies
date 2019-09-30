package jdls.one.data.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Single
import jdls.one.data.model.RawMovieSearchResults
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


object MoviesServiceFactory {

  fun makeMoviesService(apiKey: String, isDebug: Boolean): MoviesService {
    val okHttpClient = makeOkHttpClient(
      makeApiKeyInterceptor(apiKey),
      makeLoggingInterceptor(isDebug)
    )
    return makeMoviesService(okHttpClient, makeMoshi())
  }

  private fun makeMoviesService(okHttpClient: OkHttpClient, moshi: Moshi): MoviesService {
    val retrofit = Retrofit.Builder()
      .baseUrl("https://api.themoviedb.org/3/")
      .client(okHttpClient)
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .build()
    return retrofit.create(MoviesService::class.java)
  }

  private fun makeOkHttpClient(
    apiKeyInterceptor: Interceptor,
    httpLoggingInterceptor: HttpLoggingInterceptor
  ): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(apiKeyInterceptor)
      .addInterceptor(httpLoggingInterceptor)
      .connectTimeout(30, TimeUnit.SECONDS)
      .readTimeout(30, TimeUnit.SECONDS)
      .build()
  }

  private fun makeMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

  private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = if (isDebug)
      HttpLoggingInterceptor.Level.BODY
    else
      HttpLoggingInterceptor.Level.NONE
    return logging
  }

  private fun makeApiKeyInterceptor(apiKey: String): Interceptor {
    return Interceptor { chain ->
      val originalRequest = chain.request()
      val originalHttpUrl = originalRequest.url

      val url = originalHttpUrl.newBuilder()
        .addQueryParameter("api_key", apiKey)
        .build()

      // Request customization: add request headers
      val requestBuilder = originalRequest.newBuilder()
        .url(url)

      val request = requestBuilder.build()
      chain.proceed(request)
    }
  }

  interface MoviesService {

    @GET("tv/popular")
    fun getPopularTVShows(
      @Query("language") language: String,
      @Query("page") page: Int
    ): Single<RawMovieSearchResults>

  }
}