package com.example.fm_hub.retrofit

import com.example.fm_hub.model.MovieDetailInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService3 {
    @GET("search_json2.jsp?collection=kmdb_new2")
    fun getList(
        @Query("detail") detail: String,
        @Query("releaseDts") releaseDts: String,
        @Query("listCount") listCount: Int,
        @Query("ServiceKey") serviceKey: String?,
    ): Call<MovieDetailInfo>
}