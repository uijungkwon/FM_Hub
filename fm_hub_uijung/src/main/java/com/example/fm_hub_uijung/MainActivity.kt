package com.example.fm_hub_uijung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fm_hub_uijung.databinding.ActivityMainBinding
import com.example.fm_hub_uijung.databinding.MovieItemRecyclerviewBinding
//영화목록 data class 생성
data class movieItem(val movie_image:Int, val movie_title:String)

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //(1) 뷰 바인딩
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //(2) 영화 목록 임시 데이터 생성
        val movieList = ArrayList<movieItem>() //영화 포스터는 임의로 아이콘 생성
        movieList.add(movieItem(android.R.drawable.star_on, "엘리멘탈"))
        movieList.add(movieItem(android.R.drawable.ic_menu_camera, "엔칸토"))
        movieList.add(movieItem(android.R.drawable.arrow_up_float, "인생은 아름다워"))
        movieList.add(movieItem(android.R.drawable.sym_call_incoming, "인어공주"))

        //(3) 영화목록 레이아웃 배치 -  LinearLayout으로 배치
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        //(4) 영화 목록 어댑터에 생성한 데이터 연결
        val adapter = MyAdapter(movieList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, 1))//구분선 출력

    }
}