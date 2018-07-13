package com.gmail.martsulgp.qulixsystemstestapp.data

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface GiphyApi {

//    @POST("users/login")
//    fun logInUser(@Body profile: LogInRequest): Observable<UserInfoResponse>
//
//    @POST("users/register")
//    fun regUser(@Body profile: RegisterRequest): Observable<UserInfoResponse>
//
    @GET("v1/gifs/trending")
    fun getTrending(@Path("limit") param: String): Observable<Boolean>

    companion object {
        fun create(): GiphyApi {
            val host = "https://api.giphy.com/v2R98Gyr0eIEnH1bimQORQF1XLqa2nl0/"
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