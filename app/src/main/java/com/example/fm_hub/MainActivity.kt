package com.example.fm_hub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.lifecycleScope
import com.example.fm_hub.databinding.ActivityMainBinding
import com.example.fm_hub.model.GeoLocation
import com.example.fm_hub.model.Quad
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.Calendar


// 코루틴 데이터 공유를 위한 뷰모델 추가
object CinemaDataStorage {
    var geoLocationList: ArrayList<Quad<GeoLocation, String, String, String>>? = null
}
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle



    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)
        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        binding.nav.setNavigationItemSelectedListener(this)


        /*팝업창 띄우는 코드 -> 수요일로 수정*/
        if (isSpecificDayOfWeek(Calendar.FRIDAY)) {
            showPopupActivity()
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

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_join -> {
                val intent = Intent(this, JoinActivity::class.java )
                startActivity(intent)
            }
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
            R.id.menu_login ->{
                val intent = Intent(this, LoginActivity::class.java )
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

