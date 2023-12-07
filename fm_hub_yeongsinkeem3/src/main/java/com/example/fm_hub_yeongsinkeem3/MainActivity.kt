package com.example.fm_hub_yeongsinkeem3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fm_hub_yeongsinkeem3.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var mRecyclerView: RecyclerView
    lateinit var mRecyclerAdapter: MyRecyclerAdapter
    lateinit var mfriendItems: ArrayList<FriendItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)

        mRecyclerView = binding.recyclerViewMovie
        mRecyclerAdapter = MyRecyclerAdapter()

        mRecyclerView.adapter = mRecyclerAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        //mRecyclerView = (RecyclerView)findViewById(R.id.recyclerViewMovie);
        // initiate adapter
        //mRecyclerAdapter = new MyRecyclerAdapter();

        // initiate recyclerview
        //mRecyclerView.setAdapter(mRecyclerAdapter);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        binding.nav.setNavigationItemSelectedListener(this)

        mfriendItems = ArrayList()
        for (i in 1..3) {
            mfriendItems.add(FriendItem(R.drawable.movie1, "하이큐!! 승자와 패자", "예매율 87.9%", "누적관객 122,176명, 당일관객 53,379명"))
            mfriendItems.add(FriendItem(R.drawable.movie2, "Call me by your name", "예매율 43.3%", "누적관객 210,496명, 당일관객 23,321명"))
            mfriendItems.add(FriendItem(R.drawable.movie3, "그대들은 어떻게 살 것인가", "예매율 40.1%", "누적관객 170,883명, 당일관객 10,230명"))
        }
        mRecyclerAdapter.setFriendList(mfriendItems);
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
        }
        return false
    }
}