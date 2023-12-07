package com.example.fm_hub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Build
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fm_hub.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import java.util.Calendar
import android.widget.Button

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle

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
        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(
            this,
            binding.drawer,
            R.string.drawer_opened,
            R.string.drawer_closed
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        binding.nav.setNavigationItemSelectedListener(this)

        val loginButton = binding.nav.getHeaderView(0).findViewById<Button>(R.id.loginButton)
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