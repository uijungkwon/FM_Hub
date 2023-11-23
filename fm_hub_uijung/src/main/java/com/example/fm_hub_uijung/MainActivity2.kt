package com.example.fm_hub_uijung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fm_hub_uijung.databinding.ActivityMain2Binding
import com.example.fm_hub_uijung.databinding.ActivityMainBinding

data class movieItem(val movie_image:Int, val movie_title:String)

class MainActivity2 : AppCompatActivity() {
    lateinit var binding:ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //(1) 뷰 바인딩
        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.topTitle.text = "영화 목록"

        //(2) 영화 목록 임시 데이터 생성 - API 연결
        //스크롤 연동
        val movieList = ArrayList<movieItem>() //영화 포스터는 임의로 아이콘 생성
        movieList.add(movieItem(android.R.drawable.star_on, "엘리멘탈"))
        movieList.add(movieItem(android.R.drawable.ic_menu_camera, "엔칸토"))
        movieList.add(movieItem(android.R.drawable.arrow_up_float, "인생은 아름다워"))
        movieList.add(movieItem(android.R.drawable.sym_call_incoming, "인어공주"))
        movieList.add(movieItem(android.R.drawable.star_on, "겨울왕국"))
        movieList.add(movieItem(android.R.drawable.ic_menu_camera, "겟아웃"))
        movieList.add(movieItem(android.R.drawable.arrow_up_float, "7번방의 선물"))
        movieList.add(movieItem(android.R.drawable.sym_call_incoming, "독전"))
        movieList.add(movieItem(android.R.drawable.ic_menu_camera, "라라랜드"))
        movieList.add(movieItem(android.R.drawable.arrow_up_float, "레옹"))
        movieList.add(movieItem(android.R.drawable.sym_call_incoming, "아이 필 프리티"))

        //(3) 영화목록 레이아웃 배치 -  LinearLayout으로 배치
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        //(4) 영화 목록 어댑터에 생성한 데이터 연결 - 매개변수로 데이터 전송
        val adapter = MyAdapter(movieList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, 1))//구분선 출력

    }
}