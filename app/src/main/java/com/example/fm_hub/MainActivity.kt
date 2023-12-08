package com.example.fm_hub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Build
import android.util.Log
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

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fm_hub.model.GeoLocation
import com.example.fm_hub.model.Quad

import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.Calendar


// 코루틴 데이터 공유를 위한 뷰모델 추가~
object CinemaDataStorage {
    var geoLocationList: ArrayList<Quad<GeoLocation, String, String, String>>? = null
}
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    lateinit var binding: ActivityMainBinding
    lateinit var binding2:NavigationHeaderBinding
    lateinit var bindingAuth:ActivityAuthBinding

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var headerView: View
    lateinit var loginBtn: TextView
    lateinit var loginUser: TextView

    // 메인화면 recyclerview
    lateinit var mRecyclerView: RecyclerView
    lateinit var mRecyclerAdapter: MyRecyclerAdapter
    lateinit var mfriendItems: ArrayList<FriendItem>

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)

        //uijung : main activity 툴바 include 해서 코드 변경!!!
        setSupportActionBar(binding.toolbar)

        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toggle.syncState()

        binding.nav.setNavigationItemSelectedListener(this)
        binding2 = NavigationHeaderBinding.inflate(layoutInflater)
        //

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
        /*코루틴으로 영화관 위치정보 가져오기*/
        lifecycleScope.launch(Dispatchers.Main) {
            val helper = CinemaLocationHelper(context = this@MainActivity)
            val geoLocationList = async(Dispatchers.IO) {
                helper.getMarker("Cinema.json") as ArrayList<Quad<GeoLocation, String, String, String>>
            }.await()

            //로그, 나중에 빼시길
            Log.d("MainActivity", "geoLocationList: $geoLocationList")

            // 메인 액티비티에서 데이터 저장
            CinemaDataStorage.geoLocationList = geoLocationList
        }
        // recyclerview by yeongsinkeem
        mRecyclerView = binding.recyclerViewMovie
        mRecyclerAdapter = MyRecyclerAdapter()

        mRecyclerView.adapter = mRecyclerAdapter
        mRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        mfriendItems = ArrayList()
        for (i in 1..3) {
            mfriendItems.add(
                FriendItem(
                    R.drawable.movie1,
                    "하이큐!! 승자와 패자",
                    "예매율 87.9%",
                    "누적관객 122,176명, 당일관객 53,379명"
                )
            )
            mfriendItems.add(
                FriendItem(
                    R.drawable.movie2,
                    "Call me by your name",
                    "예매율 43.3%",
                    "누적관객 210,496명, 당일관객 23,321명"
                )
            )
            mfriendItems.add(
                FriendItem(
                    R.drawable.movie3,
                    "그대들은 어떻게 살 것인가",
                    "예매율 40.1%",
                    "누적관객 170,883명, 당일관객 10,230명"
                )
            )
        }
        mRecyclerAdapter.setFriendList(mfriendItems);
    }

    /*로그인, 로그아웃 상태에 따라 다르게 "View" 보여주기*/
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
            //var name:String = MyFirebaseApplication.email.toString().split("@")[0]
            loginUser.setText(MyFirebaseApplication.email+ " 님 ")

        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }
    /*예비로 만들었던 로그인, 회원가입 목록 삭제*/
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

