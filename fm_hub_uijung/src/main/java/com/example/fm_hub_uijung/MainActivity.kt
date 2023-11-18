package com.example.fm_hub_uijung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

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
        setContentView(R.layout.activity_main)
    }
}