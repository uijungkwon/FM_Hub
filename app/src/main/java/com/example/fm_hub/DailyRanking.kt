package com.example.fm_hub

import MyAdapter
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fm_hub.databinding.ActivityDailyRankingBinding
import com.example.fm_hub.databinding.ActivityInfoBinding
import com.example.fm_hub.model.MovieDto
import com.example.fm_hub.model.MovieResponse
import com.example.fm_hub.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class DailyRanking : AppCompatActivity() {
    private lateinit var textViewDate: TextView
    private lateinit var targetDt: String
    private lateinit var recyclerView: RecyclerView
    lateinit var binding: ActivityDailyRankingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityDailyRankingBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        textViewDate = findViewById(R.id.textView_date)  //날짜 텍스트뷰
        initializeView()    //날짜 텍스트뷰 클릭 시 달력 띄우기

        //뒤로가기 버튼 툴바
        /*
        val toolbar = findViewById<Toolbar>(R.id.toolbar) as androidx.appcompat.widget.Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        */

        // 현재 날짜의 하루 전으로 초기화
        setTargetDtToYesterday()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@DailyRanking)

        // 초기 API 호출
        makeApiCall()

        // Retrofit 통신
        val button = findViewById<View>(R.id.button1)
        button.setOnClickListener {
            showDatePickerDialog()
        }

        //back 버튼 생성
        setSupportActionBar(binding.dailyToolbar.toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.back_button)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)
        binding.dailyToolbar.topTitle.text = "일별 박스오피스 순위"

    }

    // API 호출하는 메소드
    private fun makeApiCall() {
        RetrofitBuilder.api
            .getMovieList(targetDt, "fe366da2572c8b3f9b07e12c4302d591")
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    // 통신 실패한 경우
                    Toast.makeText(this@DailyRanking, "${t.message}", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    val movieResponse = response.body()
                    val list: List<MovieDto>? = movieResponse?.boxofficeResult?.dailyBoxOfficeList
                    Log.d("MY", "$list")

                    recyclerView.adapter = MyAdapter(list)
                    recyclerView.addItemDecoration(
                        DividerItemDecoration(
                            this@DailyRanking,
                            LinearLayoutManager.VERTICAL
                        )
                    )
                }
            })
    }

    // 뒤로가기 버튼
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    // 날짜 텍스트뷰 클릭 시 달력 띄우기
    private fun initializeView() {
        textViewDate = findViewById(R.id.textView_date)
        val button = findViewById<View>(R.id.button1)
        button.setOnClickListener {
            showDatePickerDialog()
        }
    }

    // DatePickerDialog 띄우기
    private fun showDatePickerDialog() {
        val currentDate = Calendar.getInstance()
        val year = currentDate.get(Calendar.YEAR)
        val month = currentDate.get(Calendar.MONTH)
        val dayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val selectedDate = "${year}년 ${month + 1}월 ${dayOfMonth}일"
                textViewDate.text = selectedDate

                // targetDt 업데이트
                targetDt = String.format("%04d%02d%02d", year, month + 1, dayOfMonth)

                // API 호출 다시 수행
                makeApiCall()
            },
            year,
            month,
            dayOfMonth
        )

        // 어제 날짜의 timestamp 구하기
        val yesterday = Calendar.getInstance()
        yesterday.add(Calendar.DAY_OF_MONTH, -1)
        val yesterdayTimestamp = yesterday.timeInMillis

        // 최대 선택 가능한 날짜를 어제로 설정
        datePickerDialog.datePicker.maxDate = yesterdayTimestamp
        datePickerDialog.show()
    }



    // 날짜 텍스트뷰에 현재 날짜 설정
    private fun setTargetDtToYesterday() {
        val yesterday = Calendar.getInstance()
        yesterday.add(Calendar.DAY_OF_MONTH, -1)
        val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        val dateFormat2 = SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault())
        targetDt = dateFormat.format(yesterday.time)
        textViewDate.text = dateFormat2.format(yesterday.time)
    }
}

