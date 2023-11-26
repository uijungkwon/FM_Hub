package com.example.fm_hub_dazzang2

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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
        val button = findViewById<View>(R.id.button)
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
