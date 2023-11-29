package com.example.fm_hub_uijung.model
import android.provider.ContactsContract.Directory
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class MovieInfo {
    var movieNm: String? = null//영화명
    var openDt: String? = null//개봉연도
    var genreAlt: String? = null//영화장르(전체)
}
