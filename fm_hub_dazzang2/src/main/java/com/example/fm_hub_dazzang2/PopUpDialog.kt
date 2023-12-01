package com.example.fm_hub_dazzang2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class PopUpDialog : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop_up_dialog)

        val closeButton = findViewById<View>(R.id.btn_shutdown)
        closeButton.setOnClickListener {
            finish() // 버튼 클릭 시 팝업 제거
        }
    }
    
}