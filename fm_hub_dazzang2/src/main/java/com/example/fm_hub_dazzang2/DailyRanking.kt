package com.example.fm_hub_dazzang2

import MyAdapter
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class DailyRanking : AppCompatActivity() {
    private lateinit var textViewDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_ranking)
        textViewDate = findViewById(R.id.textView_date)
        setCurrentDate()
        initializeView()

        //뒤로가기 버튼 툴바
        val toolbar = findViewById<Toolbar>(R.id.toolbar) as androidx.appcompat.widget.Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val DataList : MutableList<Datas> = mutableListOf(
            Datas("사과", R.drawable.img_1),
            Datas("배", R.drawable.img_1),
            Datas("딸기", R.drawable.img_1),
            Datas("포도", R.drawable.img_1),
            Datas("바나나", R.drawable.img_1),
            Datas("키위", R.drawable.img_1),
            Datas("망고", R.drawable.img_1),
            Datas("수박", R.drawable.img_1),
            Datas("참외", R.drawable.img_1)
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyAdapter(DataList)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

    }

    // 뒤로가기 버튼
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }


    private fun setCurrentDate() {
        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)

        textViewDate.text = formattedDate
    }

    private fun initializeView() {
        textViewDate = findViewById(R.id.textView_date)

        // 버튼 클릭 시 DatePickerDialog 띄우기
        val button = findViewById<View>(R.id.button1)
        button.setOnClickListener {
            showDatePickerDialog()
        }
    }

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
            },
            year,
            month,
            dayOfMonth
        )

        datePickerDialog.show()
    }
}
