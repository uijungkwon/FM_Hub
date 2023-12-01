package com.example.fm_hub_dazzang2

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("boxOfficeResult")
    var boxofficeResult: BoxOfficeResult?
)
