package com.example.fm_hub_uijung.retrofit

import com.example.fm_hub_uijung.model.MovieDetailInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService2 {
    @GET("search_json2.jsp?collection=kmdb_new2")
    fun getList(
        @Query("detail") detail: String,
        @Query("title") title: String,
        @Query("listCount") listCount: Int,
        @Query("releaseDts") releaseDts: String,
        @Query("ServiceKey") serviceKey: String?,
    ): Call<MovieDetailInfo>
}