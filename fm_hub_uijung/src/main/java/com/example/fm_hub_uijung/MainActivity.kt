package com.example.fm_hub_uijung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fm_hub_uijung.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    //(1) MovieList 프래그먼트 등록
    class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){
        val fragment: Fragment
        init {
            fragment = MyMovieList()
        }
        override fun getItemCount(): Int = 1
        override fun createFragment(position: Int): Fragment = fragment
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //(1) 뷰 바인딩
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setSupportActionBar(binding.toolbar) //액션바 대신 툴바 생성

        //(2) 뷰 페이저에 어댑터 적용
        binding.viewpager.adapter = MyFragmentPagerAdapter(this)//화면마다 다른 뷰를 보여줄 수 있음
    }
}