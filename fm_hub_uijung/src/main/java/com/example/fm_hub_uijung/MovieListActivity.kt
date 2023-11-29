package com.example.fm_hub_uijung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fm_hub_uijung.databinding.ActivityMovieListBinding

/*
data class movieItem(val movie_image:String, val movie_title:String, val movie_release:String, val movie_genre:String) //아이콘: int, 사진: String

class MainActivity2 : AppCompatActivity() {
    lateinit var binding:ActivityMain2Binding
    //lateinit var list: List<MOVIEINFO>
    override fun onCreate(savedInstanceState: Bundle?) {
        val bundle = intent.extras
        //list = bundle?.getSerializable("movieList") as List<MOVIEINFO>

        super.onCreate(savedInstanceState)
        //(1) 뷰 바인딩
        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.topTitle.text = "영화 목록"
        //(2) 영화 목록 임시 데이터 생성 - API 연결


        val movieList = ArrayList<movieItem>() //영화 포스터는 임의로 아이콘 생성
        movieList.add(movieItem("@drawable/sample.jpeg", "엘리멘탈", "2021.08.11", "로맨틱 코미디"))
        movieList.add(movieItem("@drawable/sample.jpeg", "엔칸토", "2021.08.11", "로맨틱 코미디"))
        movieList.add(movieItem("@drawable/sample.jpeg", "인생은 아름다워", "2021.08.11", "로맨틱 코미디"))
        movieList.add(movieItem("@drawable/sample.jpeg", "인어공주", "2021.08.11", "로맨틱 코미디" ))
        movieList.add(movieItem("@drawable/sample.jpeg", "겨울왕국", "2021.08.11", "로맨틱 코미디"))
        movieList.add(movieItem("@drawable/sample.jpeg", "겟아웃", "2021.08.11", "로맨틱 코미디"))
        movieList.add(movieItem("@drawable/sample.jpeg", "7번방의 선물", "2021.08.11", "로맨틱 코미디"))
        movieList.add(movieItem("@drawable/sample.jpeg", "독전", "2021.08.11", "로맨틱 코미디"))
        movieList.add(movieItem("@drawable/sample.jpeg", "라라랜드", "2021.08.11", "로맨틱 코미디"))
        movieList.add(movieItem("@drawable/sample.jpeg", "레옹", "2021.08.11", "로맨틱 코미디"))
        movieList.add(movieItem("@drawable/sample.jpeg", "아이 필 프리티", "2021.08.11", "로맨틱 코미디"))

        //(3) 영화목록 레이아웃 배치 -  LinearLayout으로 배치
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        //(4) 영화 목록 어댑터에 생성한 데이터 연결 - 매개변수로 데이터 전송
        //val adapter = MyAdapter(movieList)
        //binding.recyclerView.adapter = adapter
        //binding.recyclerView.addItemDecoration(DividerItemDecoration(this, 1))//구분선 출력

    }


}*/
class MovieListActivity : AppCompatActivity() {
    lateinit var binding: ActivityMovieListBinding
    lateinit var retrofitFragment: RetrofitFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //(1) 뷰 바인딩
        val binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.topTitle.text = "영화 목록"

        setSupportActionBar(binding.toolbar.toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)
        //binding.infoToolbar.toolbar.setTitle("영화 상세 페이지")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        //(2) RetrofitFragment로 화면 전환
        retrofitFragment = RetrofitFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.movie_list_activity, retrofitFragment) //원래 화면을 fragment 화면으로 전환
            .commit()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}
