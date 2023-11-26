package com.example.fm_hub_uijung

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitMovieAPI {
    @GET("kobisopenapi/webservice/rest/movie/searchMovieList.json")
    fun getMovieList(
        @Query("openStartDt") openStartDt: String?,
        @Query("key") key: String?,
        @Query("openEndDt")openEndDt : String?,
        @Query("curPage") curPage: String?,
        @Query("itemPerPage") itemPerPage: String?,

    ): Call<MOVIES>
}
// annotation : Get/Post/Delete/Put 중 하려는 작업을 선택하여 작업을 수행할 주소를 () 안에 적는다.
// Call<데이터 객체 타입> @Query("요청 매개변수") 변수
// 여기선  MOVIES 타입으로 받아옴