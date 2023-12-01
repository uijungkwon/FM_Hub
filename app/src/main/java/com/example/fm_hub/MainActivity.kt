package com.example.fm_hub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Build
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.fm_hub.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import java.util.Calendar

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    //의정주석!!
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