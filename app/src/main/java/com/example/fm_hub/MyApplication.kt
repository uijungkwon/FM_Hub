package com.example.fm_hub
import android.app.Application
import com.example.fm_hub.retrofit.NetworkService2
import com.example.fm_hub.retrofit.NetworkService3
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application() {

    companion object {
        //movie info 페이지
        val SERVICEKEY = "MGAU011I1DFU2A6RBI7A"
        val DETAIL_BASE_URL = "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/"

        var networkService2: NetworkService2
        //new 영화목록

        var networkService3: NetworkService3

        val retrofit2: Retrofit
            get() = Retrofit.Builder()
                .baseUrl(DETAIL_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()


        init {
            networkService2 = retrofit2.create(NetworkService2::class.java)
            networkService3 = retrofit2.create(NetworkService3::class.java)
        }
    }

}
