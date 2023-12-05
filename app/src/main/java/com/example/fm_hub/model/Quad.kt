package com.example.fm_hub.model

//json 파일에서 필요한 데이터만 추출하기 위한 클래스 -다혜
data class Quad<out A, out B, out C, out D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D
)

