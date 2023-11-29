package com.example.fm_hub_uijung
import android.app.Application
import com.example.fm_hub_uijung.MyApplication.Companion.BASE_URL
import com.example.fm_hub_uijung.MyApplication.Companion.networkService
import com.example.fm_hub_uijung.retrofit.NetworkService
import com.example.fm_hub_uijung.retrofit.NetworkService2
import com.example.fm_hub_uijung.retrofit.NetworkService3
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application() {

    companion object {
        //기존 movie 목록
        val KEY = "fe366da2572c8b3f9b07e12c4302d591"
        val BASE_URL = "https://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/"
        val USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36"

        //movie info 페이지
        val SERVICEKEY = "MGAU011I1DFU2A6RBI7A"
        val DETAIL_BASE_URL = "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/"

        var networkService: NetworkService

        var networkService2: NetworkService2
        //new 영화목록

        var networkService3: NetworkService3

        val retrofit: Retrofit
            get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val retrofit2: Retrofit
            get() = Retrofit.Builder()
                .baseUrl(DETAIL_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()


        init {
            networkService = retrofit.create(NetworkService::class.java)
            networkService2 = retrofit2.create(NetworkService2::class.java)
            networkService3 = retrofit2.create(NetworkService3::class.java)
        }
    }

}
