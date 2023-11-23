package com.example.fm_hub_uijung

import android.R
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.fm_hub_uijung.databinding.ActivityBaseBinding
import com.example.fm_hub_uijung.databinding.ActivityInfoBinding


class InfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //(1) 뷰 바인딩
        val binding = ActivityInfoBinding.inflate(layoutInflater)
        val binding2 = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //(2)intent에서 데이터 가져오기 - 영화제목만 임시로 생성 -> 호환 성공 확인
        val title = intent.getStringExtra("title")
        binding.movieTitle.text = title

        //(3) back 버튼 생성
        setSupportActionBar(binding.infoToolbar.toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)
        //binding.infoToolbar.toolbar.setTitle("영화 상세 페이지")
        binding.infoToolbar.topTitle.text = "영화 상세 페이지" //내가 커스텀한대로 출력 가능
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    //back 기능 정의

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


}