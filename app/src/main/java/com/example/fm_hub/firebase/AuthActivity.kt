package com.example.fm_hub.firebase

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.fm_hub.MyApplication
import com.example.fm_hub.R
import com.example.fm_hub.databinding.ActivityAuthBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

class AuthActivity : AppCompatActivity() {
    lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*툴바 생성*/
        binding.toolbar.topTitle.text = "로그인"
        setSupportActionBar(binding.toolbar.toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.back_button)//흰색 백버튼
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (MyFirebaseApplication.checkAuth()) {
            changeVisibility("login")
        } else {
            changeVisibility("logout")
        }

        val requestLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        )
        {
            //구글 로그인 결과 처리...........................
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                MyFirebaseApplication.auth.signInWithCredential(credential)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            MyFirebaseApplication.email = account.email
                            changeVisibility("login")
                        } else {
                            changeVisibility("logout")
                        }
                    }
            } catch (e: ApiException) {
                changeVisibility("logout")
            }
        }
        //로그아웃
        binding.logoutBtn.setOnClickListener {
            MyFirebaseApplication.auth.signOut()
            MyFirebaseApplication.email = null
            changeVisibility("logout")
        }
        //회원가입
        binding.goSignInBtn.setOnClickListener {
            changeVisibility("signin")
        }
        //구글 로그인
        binding.googleLoginBtn.setOnClickListener {
            //구글 로그인....................
            val gso = GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            val signInIntent = GoogleSignIn.getClient(this, gso).signInIntent
            requestLauncher.launch(signInIntent)
        }
        //"가입" 버튼 눌렀을 때,
        binding.signBtn.setOnClickListener {
            //이메일,비밀번호 회원가입

            val email = binding.authEmailEditView.text.toString()
            val password = binding.authPasswordEditView.text.toString()
            MyFirebaseApplication.auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    binding.authEmailEditView.text.clear()
                    binding.authPasswordEditView.text.clear()
                    if (task.isSuccessful) {
                        MyFirebaseApplication.auth.currentUser?.sendEmailVerification()
                            ?.addOnCompleteListener { sendTask ->
                                if (sendTask.isSuccessful) {
                                    Toast.makeText(
                                        baseContext, "회원가입에서 성공, 전송된 메일을 확인해 주세요",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    changeVisibility("logout")
                                } else {
                                    Toast.makeText(baseContext, "메일 발송 실패", Toast.LENGTH_SHORT)
                                        .show()
                                    changeVisibility("logout")
                                }
                            }
                    } else {
                        Toast.makeText(baseContext, "회원가입 실패", Toast.LENGTH_SHORT).show()
                        changeVisibility("logout")
                    }
                }

        }
        //로그인 버튼
        binding.loginBtn.setOnClickListener {
            //이메일, 비밀번호 로그인.......................
            val email = binding.authEmailEditView.text.toString()
            val password = binding.authPasswordEditView.text.toString()
            Log.d("kkang", "email:$email, password:$password")
            MyFirebaseApplication.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    binding.authEmailEditView.text.clear()
                    binding.authPasswordEditView.text.clear()
                    if (task.isSuccessful) {
                        if (MyFirebaseApplication.checkAuth()) {
                            MyFirebaseApplication.email = email
                            changeVisibility("login") //로그인 모드로 변경
                        } else {
                            Toast.makeText(
                                baseContext,
                                "전송된 메일로 이메일 인증이 되지 않았습니다.",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    } else {
                        Toast.makeText(baseContext, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
    //모드 변경 함수
    fun changeVisibility(mode: String) {
        if (mode === "login") { //로그인이 된 상태의 화면, ~반갑습니다, 로그아웃 버튼 생성
            binding.run {
                authMainTextView.text = "${MyFirebaseApplication.email} 님 로그아웃 하시겠습니까?"
                logoutBtn.visibility = View.VISIBLE
                goSignInBtn.visibility = View.GONE
                googleLoginBtn.visibility = View.GONE
                authEmailEditView.visibility = View.GONE
                authPasswordEditView.visibility = View.GONE
                signBtn.visibility = View.GONE
                loginBtn.visibility = View.GONE
                binding.toolbar.topTitle.text = "로그 아웃"
            }

        } else if (mode === "logout") { //로그아웃 모드 일 때, 모든 버튼 활성화 해서 회원가입 하거나? 로그인 하도록함
            binding.run {
                //authMainTextView.text = "로그인 하거나 회원가입 해주세요."
                logoutBtn.visibility = View.GONE
                goSignInBtn.visibility = View.VISIBLE
                googleLoginBtn.visibility = View.GONE //일단 구글인증은 안보이게!
                authEmailEditView.visibility = View.VISIBLE
                authPasswordEditView.visibility = View.VISIBLE
                signBtn.visibility = View.GONE
                loginBtn.visibility = View.VISIBLE
                binding.toolbar.topTitle.text = "로그인"
            }
        } else if (mode === "signin") {//회원가입 버튼을 눌렀을 때, 회원가입 할수 있는 모드로 변경, "가입" 버튼 활성화
            binding.run {
                logoutBtn.visibility = View.GONE
                goSignInBtn.visibility = View.GONE
                googleLoginBtn.visibility = View.GONE //구글인증은 안보이게!
                authEmailEditView.visibility = View.VISIBLE
                authPasswordEditView.visibility = View.VISIBLE
                signBtn.visibility = View.VISIBLE
                loginBtn.visibility = View.GONE
                binding.toolbar.topTitle.text = "회원 가입"
            }
        }
    }
}