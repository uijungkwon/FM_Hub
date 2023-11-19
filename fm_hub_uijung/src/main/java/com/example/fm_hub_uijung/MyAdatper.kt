package com.example.fm_hub_uijung;

import com.example.fm_hub_uijung.databinding.MovieItemRecyclerviewBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
//(1) 뷰 홀더 생성하기
class MyViewHolder(val binding:MovieItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {}
//(2) 어댑터 생성하기
class MyAdapter(val datas: MutableList<movieItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun getItemCount(): Int = datas.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder(MovieItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    override fun onBindViewHolder (holder: RecyclerView.ViewHolder, position: Int) {
        //(1) 뷰에 내가 생성한 영화 포스터, 영화제목 데이터 출력
        val binding = (holder as MyViewHolder).binding
        binding.itemImage.setImageResource(datas[position].movie_image) //영화 포스터
        binding.itemTitle.text = datas[position].movie_title//영화 제목
    }
}
