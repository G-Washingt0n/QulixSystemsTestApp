package com.gmail.martsulgp.qulixsystemstestapp.data

import com.gmail.martsulgp.qulixsystemstestapp.model.response.GifsDataResponse
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface GiphyApi {

    @GET("v1/gifs/trending")
    fun getTrending(
            @Query("api_key") api_key: String,
            @Query("limit") limit: Int,
            @Query("offset") offset: Int
    ): Observable<GifsDataResponse>

    @GET("v1/gifs/search")
    fun searchGifs(
            @Query("api_key") api_key: String,
            @Query("q") q: String,
            @Query("limit") limit: Int,
            @Query("offset") offset: Int
    ): Observable<GifsDataResponse>

    companion object {
        fun create(): GiphyApi {
            val host = "https://api.giphy.com/"
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                    .baseUrl(host)
                    .client(OkHttpClient.Builder()
                            .readTimeout(20, TimeUnit.SECONDS)
                            .connectTimeout(20, TimeUnit.SECONDS)
                            .addInterceptor(
                                    HttpLoggingInterceptor()
                                            .setLevel(HttpLoggingInterceptor.Level.BODY))
                            .build())
                    .build()
            return retrofit.create(GiphyApi::class.java)
        }
    }
}