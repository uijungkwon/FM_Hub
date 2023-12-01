package com.example.fm_hub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.fm_hub.databinding.ActivityBaseBinding
import com.example.fm_hub.model.MovieDetailInfo
import com.example.fm_hub.databinding.ActivityInfoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //(1) 뷰 바인딩
        val binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //(2)intent에서 데이터 가져오기 - "영화제목만 임시로 생성" -> 호환 성공 확인
        val title  = intent.getStringExtra("title")
        binding.movieTitle.text = title

        //(3) back 버튼 생성
        setSupportActionBar(binding.infoToolbar.toolbar)///
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)
        //binding.infoToolbar.toolbar.setTitle("영화 상세 페이지")

        binding.infoToolbar.topTitle.text = "영화 상세 페이지" //내가 커스텀한대로 출력 가능
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //(4)버튼 클릭-> 영화 찾기 페이지로 이동

        binding.findBtn.setOnClickListener {
            val intent = Intent(this, CinemaLocationView::class.java)
            startActivity(intent)
        }

        val call: Call<MovieDetailInfo> = MyApplication.networkService2
            .getList(
                "Y",
                title.toString(),
                1,
                "202301",
                MyApplication.SERVICEKEY,
            )
        call?.enqueue(object: Callback<MovieDetailInfo> {
            override fun onResponse(call: Call<MovieDetailInfo>, response: Response<MovieDetailInfo>) {
                if(response.isSuccessful){
                    var splitUrl:String = response.body()?.Data?.get(0)?.Result?.get(0)?.posters.toString().split("|")[0]
                    Toast.makeText(this@InfoActivity,response.body()?.Data?.get(0)?.Result?.get(0)?.posters, Toast.LENGTH_SHORT).show()
                    //테스트 결과 null객체로 받아와짐 -> 수정

                    if(splitUrl.isEmpty()){
                    }
                    else{
                        Glide.with(this@InfoActivity)
                            .load(splitUrl)
                            .into(binding.movieImage)
                    }

                    binding.movieGenre.text = "장르"+response.body()?.Data?.get(0)?.Result?.get(0)?.genre
                    binding.movieDirector.text = "감독: "+response.body()?.Data?.get(0)?.Result?.get(0)?.directors?.director?.get(0)?.directorNm //첫번째 감독만 출력(감독이 여러명일 경우
                    binding.movieActor.text = "배우: "+response.body()?.Data?.get(0)?.Result?.get(0)?.actors?.actor?.get(0)?.actorNm//첫번째 주연배우만 출력
                    binding.movieStoryline.text = response.body()?.Data?.get(0)?.Result?.get(0)?.plots?.plot?.get(0)?.plotText
                    binding.movieAge.text = "관람가: "+response.body()?.Data?.get(0)?.Result?.get(0)?.rating
                    binding.movieTime.text = "상영시간: "+response.body()?.Data?.get(0)?.Result?.get(0)?.runtime+"분"
                    binding.movieComeOut.text = "개봉일: "+response.body()?.Data?.get(0)?.Result?.get(0)?.repRlsDate

                }
            }
            override fun onFailure(call: Call<MovieDetailInfo>, t: Throwable) {
                Toast.makeText(this@InfoActivity, "실패", Toast.LENGTH_SHORT).show()
                Log.d("kwonkwon", "error.......")
            }
        })
        ////////////////
    }
    //back 기능 정의
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


}