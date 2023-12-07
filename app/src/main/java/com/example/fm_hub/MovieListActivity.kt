package com.example.fm_hub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fm_hub.databinding.ActivityBaseBinding
import com.example.fm_hub.databinding.ActivityMovieListBinding

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
        getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.back_button)//흰색 백버튼
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
