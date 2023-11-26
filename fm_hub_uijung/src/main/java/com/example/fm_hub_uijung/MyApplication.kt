package com.example.fm_hub_uijung
import android.app.Application
import com.example.fm_hub_uijung.retrofit.NetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application() {

    companion object {
/*
        val QUERY = "travel"

        val API_KEY = "079dac74a5f94ebdb990ecf61c8854b7"
        val BASE_URL = "https://newsapi.org"
        val USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36"

*/
        val KEY = "fe366da2572c8b3f9b07e12c4302d591"
        val BASE_URL = "https://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/"
        val USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36"
        //add....................................


        var networkService: NetworkService
        val retrofit: Retrofit
            get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        init {
            networkService = retrofit.create(NetworkService::class.java)
        }
    }

}