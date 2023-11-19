package com.example.fm_hub_uijung;

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fm_hub_uijung.databinding.FragmentMyMovieListBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
data class movieItem(val movie_image:Int, val movie_title:String)
class MyMovieList : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyMovieListBinding.inflate(inflater, container,false)

        //(1) API 연결 전 임시데이터 생성 - 이부분은 API 호출 필요(영화사진, 영화 제목)
        val movieList = ArrayList<movieItem>() //영화 포스터는 임의로 아이콘 생성
        movieList.add(movieItem(android.R.drawable.star_on,"엘리멘탈"))
        movieList.add(movieItem(android.R.drawable.ic_menu_camera,"엔칸토"))
        movieList.add(movieItem(android.R.drawable.arrow_up_float,"인생은 아름다워"))
        movieList.add(movieItem(android.R.drawable.stat_notify_chat,"인어공주"))

        //(2) 레이아웃 배치 -  LinearLayout으로 배치
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager

        //(3) 어댑터에 생성한 데이터 연결
        val adapter = MyAdapter(movieList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(activity,1))//구분선 출력
        return binding.root
    }
}