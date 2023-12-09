package com.example.fm_hub.model

class MovieDetailInfo{
    var Data:MutableList<Result>? = null
}
class Result{
    var Result:MutableList<DetailInfo>? = null
}
class DetailInfo{
    var title: String? = null//영화명
    var runtime:String? = null//상영시간
    var rating:String? = null//관람등급
    var plots:plot? = null//줄거리
    var genre:String? = null //장르
    var posters:String? = null// 영화 포스터
    var repRlsDate:String? = null//개봉연도
    var directors:Directors? = null//감독
    var actors:Actors? = null//배우
}
class Actor{
    var actorNm:String? = null//배우이름
}
class Actors{
    var actor:MutableList<Actor>? = null//배우이름
}
class Director{
    var directorNm:String? = null//감독이름
}
class Directors{
    var director:MutableList<Director>? = null//감독이름
}
class plot{
    var plot:MutableList<allplot>? = null//줄거리
}
class allplot{
    var plotText:String? = null//줄거리
}