package com.example.fm_hub_uijung.retrofit

import com.example.fm_hub_uijung.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface NetworkService {
    @GET("searchMovieList.json?")
    fun getList(
        @Query("key") key: String?,
        @Query("openStartDt") openStartDt: String,
        @Query("openEndDt") openEndDt: String,
        @Query("curPage") curPage: String,
        @Query("itemPerPage") itemPerPage: String,
    ): Call<MovieResponse>
}

/*

interface NetworkService {
    @GET("/v2/everything")
    fun getList(
        @Query("q") q: String?,
        @Query("apiKey") apiKey: String?,
        @Query("page") page: Long,
        @Query("pageSize") pageSize: Int
    ): Call<MovieListModel>
}*/