package com.hireteam.hireapp

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

/*
interface WikiApiService {

    @GET("api.php")
    fun hitCountCheck(@Query("action") action: String,
                      @Query("format") format: String,
                      @Query("list") list: String,
                      @Query("srsearch") srsearch: String): Observable<Model.Result>

    companion object {
        fun create(): WikiApiService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://en.wikipedia.org/w/")
                .build()

            return retrofit.create(WikiApiService::class.java)
        }
    }

}
*/

interface WikiApiService {

    // POST-запрос на добавление пользователя
    @POST(".")
    @FormUrlEncoded
    fun addUser(@Field("login") login: String,
                @Field("password") password: String,
                @Field("name") name: String,
                @Field("phone") phone: String): Observable<RegistrationResponse.Result>

    // POST-запрос на аутентификацию
    @POST(".")
    @FormUrlEncoded
    fun login(@Field("login") login: String,
                @Field("password") password: String): Observable<AuthorizationResponse.Result>

    // POST-запрос на аутентификацию
    @POST(".")
    @FormUrlEncoded
    fun createItem(@Field("title") title: String,
                   @Field("description") description: String,
                   @Field("price") price: Int,
                   @Field("date_to_end") date_to_end: String): Observable<AuthorizationResponse.Result>



    companion object {
        fun create(baseURL: String): WikiApiService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                // 192.168.12.215 - ip моей машины в сети SHIFT
                // 192.168.1.58 - ip машины в домашней сети
                .baseUrl(baseURL)
                .build()

            return retrofit.create(WikiApiService::class.java)
        }
    }

}