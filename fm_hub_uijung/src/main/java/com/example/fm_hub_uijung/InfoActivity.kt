package com.example.fm_hub_uijung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.ActionBarContextView
import com.example.fm_hub_uijung.databinding.ActivityInfoBinding
import com.example.fm_hub_uijung.databinding.ActivityMainBinding

class InfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //(1) 뷰 바인딩
        val binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //(2)intent에서 데이터 가져오기 - 영화제목만 임시로 생성 -> 호환 성공 확인
        val title = intent.getStringExtra("title")
        binding.movieTitle.text = title

        //(3) back 버튼 생성
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.topTitle.text = "영화 상세 정보"
        //getSupportActionBar()?.setTitle("영화 상세 정보")
    }
    //back 기능 정의

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}