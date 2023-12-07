package com.example.fm_hub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fm_hub.databinding.ActivityJoinBinding
import com.example.fm_hub.databinding.ActivityBaseBinding
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth

class JoinActivity : AppCompatActivity() {
    lateinit var binding: ActivityJoinBinding
    // lateinit var auth: FirebaseAuth // auth 변수를 클래스 레벨로 이동
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.infoToolbar.toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)

        binding.infoToolbar.topTitle.text = "회원가입"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        /* 파이어베이스 인증 객체 얻기
        auth = FirebaseAuth.getInstance()
        // 회원가입하기
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                auth.currentUser?.sendEmailVerification()
                    ?.addOnCompleteListener { sendTask ->
                        if (sendTask.isSuccessful) {

                        }
                        else {

                        }
                    }
            }
            else {
                Log.w("ys", "createUserWithEmail:failure", task.exception)
            }
        }*/
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
    }