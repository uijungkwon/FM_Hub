package com.example.fm_hub.firebase

import androidx.multidex.MultiDexApplication
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/*앱 전역에서 파이어베이스 인증 객체를 이용하고자 application을 상속받은 클래스 생성*/
class MyFirebaseApplication: MultiDexApplication() {
    companion object {
        lateinit var auth: FirebaseAuth //파이어베이스 인증객체 생성
        var email: String? = null
        fun checkAuth(): Boolean {//인증된 사용자의 이메일 정보, 인증 상태를 파악하는 함수 선언
            var currentUser = auth.currentUser
            return currentUser?.let {
                email = currentUser.email //현재 사용자의 이메일
                currentUser.isEmailVerified//현재 사용자의 이메일이 유효한지 파악
            } ?: let {
                false
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        auth = Firebase.auth
    }
}