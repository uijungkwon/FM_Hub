package com.example.fm_hub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Build
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.fm_hub.databinding.ActivityAuthBinding
import com.example.fm_hub.databinding.ActivityMainBinding
import com.example.fm_hub.databinding.NavigationHeaderBinding
import com.example.fm_hub.firebase.AuthActivity
import com.example.fm_hub.firebase.MyFirebaseApplication
import com.google.android.material.navigation.NavigationView
import java.util.Calendar

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    lateinit var binding: ActivityMainBinding
    lateinit var binding2:NavigationHeaderBinding
    lateinit var bindingAuth:ActivityAuthBinding

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var headerView: View
    lateinit var loginBtn: TextView
    lateinit var loginUser: TextView
    //의정주석!!
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)

        //main activity 툴바 include 해서 코드 변경!!! - uijung
        setSupportActionBar(binding.toolbar)

        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toggle.syncState()

        binding.nav.setNavigationItemSelectedListener(this)
        binding2 = NavigationHeaderBinding.inflate(layoutInflater)

        /*팝업창 띄우는 코드 -> 수요일로 수정*/
        if (isSpecificDayOfWeek(Calendar.FRIDAY)) {
            showPopupActivity()
        }

        /*네비게이션 뷰 헤더 ->  로그인 버튼 구성 -> 로그인 버튼 누르면 액티비티 이동*/
        headerView = binding.nav.getHeaderView(0)
        loginBtn = headerView.findViewById<TextView>(R.id.mainLoginBtn)
        loginUser = headerView.findViewById<TextView>(R.id.loginUser)
        loginBtn.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onStart() {
        super.onStart()
        if(!MyFirebaseApplication.checkAuth()){
            loginBtn = headerView.findViewById<TextView>(R.id.mainLoginBtn)
            loginUser = headerView.findViewById<TextView>(R.id.loginUser)
            loginBtn.visibility=View.VISIBLE
            loginBtn.setText("로그인")
            loginUser.visibility=View.GONE
        }else {
            loginBtn = headerView.findViewById<TextView>(R.id.mainLoginBtn)
            loginUser = headerView.findViewById<TextView>(R.id.loginUser)
            loginBtn.setText("로그 아웃")
            loginUser.visibility=View.VISIBLE
            loginUser.setText(MyFirebaseApplication.email+" 님 ")

        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_movie_list -> {
                val intent = Intent(this, MovieListActivity::class.java )
                startActivity(intent)
            }
            R.id.menu_item_search ->{
                val intent = Intent(this, PopUpDialog::class.java )
                startActivity(intent)
            }

            R.id.menu_daily_ranking ->{
                val intent = Intent(this, DailyRanking::class.java )
                startActivity(intent)
            }
            R.id.menu_movie_location ->{
                val intent = Intent(this, CinemaLocationView::class.java )
                startActivity(intent)
            }
        }
        return false
    }
    private fun showPopupActivity() {
        // 팝업 액티비티 띄우는 함수
        val intent = Intent(this, PopUpDialog::class.java)
        startActivity(intent)
    }

    private fun isSpecificDayOfWeek(targetDayOfWeek: Int): Boolean {
        //오늘의 요일 확인하는 함수 (수요일 팝업)
        val calendar = Calendar.getInstance()
        val currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        return currentDayOfWeek == targetDayOfWeek
    }
}