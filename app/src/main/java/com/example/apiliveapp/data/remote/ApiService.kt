package com.example.apiliveapp.data.remote

import com.example.apiliveapp.data.model.ArticleListResponse
import com.example.apiliveapp.data.model.SortCriteria
import com.example.apiliveapp.misc.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


// https://newsapi.org/v2/top-headlines?country=us&apiKey=API_KEY


const val BASE_URL = "https://newsapi.org/v2/"

val loggingInterceptor = HttpLoggingInterceptor().also {
    it.level = HttpLoggingInterceptor.Level.BODY
}

val authenticationInterceptor = Interceptor { chain ->
    val newRequest = chain.request().newBuilder()
        .addHeader(
            "X-Api-Key", Constants.apiKey
        ).build()
    chain.proceed(newRequest)
}

val client = OkHttpClient.Builder()
    .addInterceptor(authenticationInterceptor)
    .addInterceptor(loggingInterceptor)
    .build()

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface NewsApiService {


    @GET("top-headlines")
    suspend fun getTopNews(
        @Query("country") country: String = "us",
    ) : ArticleListResponse


    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchTerm: String,
        @Query("sortBy") sortedBy: SortCriteria = SortCriteria.RELEVANCY
    ) : ArticleListResponse

}

object NewsApi {
    val apiService: NewsApiService by lazy { retrofit.create(NewsApiService::class.java) }
}