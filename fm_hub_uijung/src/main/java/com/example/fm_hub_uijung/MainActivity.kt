package com.example.fm_hub_uijung

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.fm_hub_uijung.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener  {
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var retrofitFragment: RetrofitFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofitFragment= RetrofitFragment()

        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened,
            R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        // 네비게이션 메뉴의 아이템들에게 클릭 속성을 부여.
        binding.nav.setNavigationItemSelectedListener(this)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){ //토글 버튼을 눌렀을 때 drawer 열림
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.movie_item-> {
                val intent = Intent(this, MovieListActivity::class.java)
                startActivity(intent)
            }
        }
        return false
    }
}