package com.example.fm_hub_uijung;

import android.annotation.SuppressLint
import android.content.Intent
import com.example.fm_hub_uijung.databinding.MovieItemRecyclerviewBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
//(1) 뷰 홀더 생성하기

class MyViewHolder(val binding:MovieItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
    init{
            // 영화 상세정보 페이지로 액티비티 전환
            binding.itemTitle.setOnClickListener {
            val intent =Intent(binding.root.context, InfoActivity::class.java)
            //API 객체 자체를 전송 -> id에 맞는 제목, 줄거리, 감독 등 정보를 출력하도록 수정하기!!

            intent.putExtra("title", binding.itemTitle.text)
            startActivity(binding.root.context, intent, null)

            //Toast.makeText(binding.root.context, binding.itemTitle.text, Toast.LENGTH_SHORT).show()
        }
    }
}


//(2) 어댑터 생성
class MyAdapter(val datas: MutableList<movieItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun getItemCount(): Int = datas.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder(MovieItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    override fun onBindViewHolder (holder: RecyclerView.ViewHolder, position: Int) {
        //(1) 뷰에 내가 생성한 영화 포스터, 영화제목 데이터 출력
        val binding = (holder as MyViewHolder).binding
        binding.itemImage.setImageResource(R.drawable.sample) //영화 포스터//sample로 모두 같은 이미지 설정
        //binding.itemImage.setImageResource(datas[position].movie_image.toInt())//각각 다른 이미지 부여 가능
        binding.itemTitle.text = datas[position].movie_title//영화 제목
        binding.itemReleaseDate.text = "개봉일: "+datas[position].movie_release//영화 개봉일
        binding.itemGenre.text = "장르: "+datas[position].movie_genre//영화 장르
    }
}


