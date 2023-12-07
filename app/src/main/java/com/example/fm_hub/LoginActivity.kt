package com.example.fm_hub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fm_hub.databinding.ActivityLoginBinding
import com.example.fm_hub.databinding.ActivityBaseBinding
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.infoToolbar.toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)

        binding.infoToolbar.topTitle.text = "로그인"

        //joinbtn 버튼에 클릭 리스너 설정
        binding.joinbtn.setOnClickListener {
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
    }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


}

override fun onSupportNavigateUp(): Boolean {
    onBackPressed()
    return super.onSupportNavigateUp()
}
}