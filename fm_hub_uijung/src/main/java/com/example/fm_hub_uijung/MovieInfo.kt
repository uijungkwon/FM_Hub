package com.example.fm_hub_uijung
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//(1) API 영화 목록 정보를 가져올 데이터 클래스 생성
data class MOVIES(val movieListResult: MOVIEITEMRESULT)
/// MovieListResult 클래스의 "boxofficeType, showRange"는 "응답 구조"에 속함
///@SerializedName의 value객체는 직렬화 및 역직렬화 할 때 이름으로 사용되고, 값이 null일 경우 json으로 만들 필드를 자동 생략한다.
data class MOVIEITEMRESULT(
    //@SerializedName("genreAlt") @Expose val genreAlt: String,//영화 장르별로 출력하기 위한 코드
    @SerializedName("allMovieList") @Expose val allMovieList: List<MOVIEINFO>//새로 생성한 변수(영화 상세정보를 List로 표현하기 위해서)
)
//(2) 응답 구조
data class MOVIEINFO(
    @SerializedName("movieCd") @Expose var movieCd: String,//영화코드
    @SerializedName("movieNm") @Expose var movieNm: String,//영화명
    @SerializedName("movieNmEn") @Expose var movieNmEn: String,//영화명(영문)
    @SerializedName("prdtYear") @Expose var prdtYear: String,//제작연도
    @SerializedName("openDt") @Expose var openDt: String,//개봉연도
    @SerializedName("typeNm") @Expose var typeNm: String,//영화유형
    @SerializedName("prdtStatNm") @Expose var prdtStatNm: String,//제작상태
    @SerializedName("nationAlt") @Expose var nationAlt: String,//제작국가(전체)
    @SerializedName("genreAlt") @Expose var genreAlt: String,//영화장르(전체)
    @SerializedName("repNationNm") @Expose var repNationNm: String,//대표 제작국가명
    @SerializedName("repGenreNm") @Expose var repGenreNm: String,//대표 장르명
    @SerializedName("directors") @Expose var directors: String,//감독명
    @SerializedName("companyCd") @Expose var companyCd: String,//제작사 코드
    @SerializedName("companyNm") @Expose var companyNm: String,//제작사명
)