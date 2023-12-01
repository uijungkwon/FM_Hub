package com.example.fm_hub_yeonsinkeem2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fm_hub_yeonsinkeem2.databinding.ActivityMovieListBinding
class DailyMListActivity : AppCompatActivity() {
    lateinit var binding: ActivityMovieListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //(1) 뷰 바인딩
        val binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.toolbar.topTitle.text = "영화 목록"
    }
}