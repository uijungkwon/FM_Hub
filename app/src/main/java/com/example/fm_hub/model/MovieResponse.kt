package com.example.fm_hub.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("boxOfficeResult")
    var boxofficeResult: BoxOfficeResult?
)
